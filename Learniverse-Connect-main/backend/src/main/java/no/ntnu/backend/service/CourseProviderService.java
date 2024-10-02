package no.ntnu.backend.service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import no.ntnu.backend.dto.CourseByEachProviderDTO;
import no.ntnu.backend.model.CourseProvider;
import no.ntnu.backend.model.CurrencyConversionResponse;
import no.ntnu.backend.model.Provider;
import no.ntnu.backend.repository.CourseProviderRepository;
import no.ntnu.backend.repository.ProviderRepository;

/**
 * Service class for managing CourseProvider entities and handling currency conversion.
 * 
 * @version 23.05.2024
 * @author Group 01
 */
@Service
public class CourseProviderService {

  @Value("${currency.api.key}")
  private String apiKey;

  @Value("${currency.api.url}")
  private String apiUrl;

  @Autowired
  private CourseProviderRepository courseProviderRepository;

  @Autowired
  private ProviderRepository providerRepository;

  private List<CourseProvider> listOfCourseProviders = new ArrayList<>();

  private Map<String, Map<String, Double>> conversionRatesMap = new HashMap<>();

  private Map<String, Long> lastUpdatedMap = new HashMap<>();

  /**
   * Retrieves providers for a given course and converts the prices to the target currency.
   *
   * @param courseId the ID of the course
   * @param targetCurrency the target currency for conversion
   * @return a list of CourseByEachProviderDTO with converted prices and provider names
   */
  public List<CourseByEachProviderDTO> getProvidersForCourse(Long courseId, String targetCurrency) {
    List<CourseProvider> convertedCoursePrices = getConvertedCoursePrices(targetCurrency);
    List<CourseByEachProviderDTO> providersDTO = new ArrayList<>();

    for (CourseProvider courseProvider : convertedCoursePrices) {
      if (courseProvider.getCourseId().equals(courseId)) {
        CourseByEachProviderDTO dto = new CourseByEachProviderDTO();
        dto.setCourseId(courseProvider.getCourseId());
        dto.setPrice(courseProvider.getPrice());
        dto.setCurrency(courseProvider.getCurrency());

        Provider provider = providerRepository.findById(courseProvider.getProviderId()).orElse(null);
        if (provider != null) {
          dto.setProviderName(provider.getName());
        } else {
          dto.setProviderName("Unknown Provider");
        }

        providersDTO.add(dto);
      }
    }

    return providersDTO;
  }

  /**
   * Retrieves the minimum converted price for each course.
   *
   * @param targetCurrency the target currency for conversion
   * @return a list of CourseProvider with minimum prices for each course
   */
  public List<CourseProvider> getMinimumConvertedPriceForEachCourse(String targetCurrency) {
    List<CourseProvider> allCoursePrices = getConvertedCoursePrices(targetCurrency);
    Map<Long, Double> minimumPricesForEachCourse = new HashMap<>();

    // Find minimum price for each course
    for (CourseProvider courseProvider : allCoursePrices) {
      long courseId = courseProvider.getCourseId();
      double price = courseProvider.getPrice();

      if (!minimumPricesForEachCourse.containsKey(courseId) || price < minimumPricesForEachCourse.get(courseId)) {
        minimumPricesForEachCourse.put(courseId, price);
      }
    }

    // Create CourseProvider objects for minimum prices
    List<CourseProvider> minimumPricesList = minimumPricesForEachCourse.entrySet().stream()
        .map(entry -> new CourseProvider(entry.getKey(), -1, entry.getValue(), targetCurrency))
        .collect(Collectors.toList());

    return minimumPricesList;
  }

  /**
   * Retrieves the maximum converted price for each course.
   *
   * @param targetCurrency the target currency for conversion
   * @return a list of CourseProvider with maximum prices for each course
   */
  public List<CourseProvider> getMaximumConvertedPriceForEachCourse(String targetCurrency) {
    List<CourseProvider> allCoursePrices = getConvertedCoursePrices(targetCurrency);
    Map<Long, Double> maximumPricesForEachCourse = new HashMap<>();

    // Find maximum price for each course
    for (CourseProvider courseProvider : allCoursePrices) {
      long courseId = courseProvider.getCourseId();
      double price = courseProvider.getPrice();

      if (!maximumPricesForEachCourse.containsKey(courseId) || price > maximumPricesForEachCourse.get(courseId)) {
        maximumPricesForEachCourse.put(courseId, price);
      }
    }

    // Create CourseProvider objects for maximum prices
    List<CourseProvider> maximumPricesList = maximumPricesForEachCourse.entrySet().stream()
        .map(entry -> new CourseProvider(entry.getKey(), -1, entry.getValue(), targetCurrency))
        .collect(Collectors.toList());

    return maximumPricesList;
  }

  /**
   * Retrieves all CourseProvider entities and converts their prices to the target currency.
   *
   * @param targetCurrency the target currency for conversion
   * @return a list of CourseProvider with converted prices
   */
  public List<CourseProvider> getConvertedCoursePrices(String targetCurrency) {
    List<CourseProvider> allCoursePrices = courseProviderRepository.findAll();
    List<CourseProvider> convertedCoursePrices = new ArrayList<>();

    for (CourseProvider courseProvider : allCoursePrices) {
      String baseCurrency = courseProvider.getCurrency(); // Fetch base currency from the database
      double conversionRateToTargetCurrency = getConversionRate(baseCurrency, targetCurrency);
      if (conversionRateToTargetCurrency == -1) {
        return allCoursePrices;
      }

      double convertedPrice = courseProvider.getPrice() * conversionRateToTargetCurrency;
      courseProvider.setPrice(convertedPrice);
      courseProvider.setCurrency(targetCurrency);
      convertedCoursePrices.add(courseProvider);
      listOfCourseProviders.add(courseProvider);
    }

    return convertedCoursePrices;
  }

  /**
   * Fetches the conversion rate between two currencies, updating the cache if necessary.
   *
   * @param baseCurrency the base currency
   * @param targetCurrency the target currency
   * @return the conversion rate from baseCurrency to targetCurrency
   */
  private double getConversionRate(String baseCurrency, String targetCurrency) {
    Map<String, Double> conversionRates = conversionRatesMap.get(baseCurrency);
    Long lastUpdated = lastUpdatedMap.getOrDefault(baseCurrency, 0L);

    if (conversionRates == null || System.currentTimeMillis() - lastUpdated > 3600000) {
      // Fetch conversion rates if cache is empty or rates are outdated (1 hour in
      // this example)
      try {
        
        String completeApiUrl = this.apiUrl + this.apiKey + "&base_currency=" + baseCurrency;
        RestTemplate restTemplate = new RestTemplate();
        CurrencyConversionResponse response = restTemplate.getForObject(completeApiUrl, CurrencyConversionResponse.class);

        conversionRates = response.getData();
        lastUpdated = System.currentTimeMillis();

        // Update cache
        conversionRatesMap.put(baseCurrency, conversionRates);
        lastUpdatedMap.put(baseCurrency, lastUpdated);
      } catch (Exception e) {
        System.err.println("Error fetching conversion rates: " + e.getMessage());
        e.printStackTrace();
        return -1;
      }
    }
    if (conversionRates.containsKey(targetCurrency)) {
      return conversionRates.get(targetCurrency);
    } else {
      System.err.println("Currency not found in conversion rates: " + targetCurrency);
      return -1;
    }
  }
}