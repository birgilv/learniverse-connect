// package no.ntnu.backend.service;

// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;
// import java.util.stream.Collectors;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;
// import no.ntnu.backend.model.CourseProvider;
// import no.ntnu.backend.model.CurrencyConversionResponse;
// import no.ntnu.backend.model.Provider;
// import no.ntnu.backend.repository.CourseProviderRepository;
// import no.ntnu.backend.repository.ProviderRepository;
// import no.ntnu.backend.dto.CourseByEachProviderDTO;

// @Service
// public class CourseProviderServiceDTO {

//     @Autowired
//     private CourseProviderRepository courseProviderRepository;

//     @Autowired
//     private ProviderRepository providerRepository;

//     private Map<String, Double> conversionRates; // Cache for conversion rates
//     private long lastUpdated; // Timestamp of the last update

//     public List<CourseProvider> getCheapestCoursePrices(String targetCurrency) {
//         List<CourseProvider> allCourseProviders = courseProviderRepository.findAll();
//         List<CourseProvider> cheapestPrices = new ArrayList<>();
    
//         // Group the course providers by course ID
//         Map<Integer, List<CourseProvider>> courseGroups = allCourseProviders.stream()
//                 .collect(Collectors.groupingBy(CourseProvider::getCourseId));
    
//         // Iterate over each group to find the cheapest price per course
//         for (List<CourseProvider> courseProviders : courseGroups.values()) {
//             Optional<CourseProvider> cheapestProvider = courseProviders.stream()
//                     .min(Comparator.comparingDouble(CourseProvider::getPrice));
    
//             // If a cheapest provider is found, add it to the result list
//             cheapestProvider.ifPresent(cp -> {
//                 CourseProvider cheapest = new CourseProvider();
//                 cheapest.setPrice(convertToCurrency(cp.getPrice(), cp.getCurrency(), getConversionRate(targetCurrency), targetCurrency));
//                 cheapest.setCurrency(targetCurrency);
//                 cheapest.setCourseId(cp.getCourseId());
//                 cheapest.setProviderId(cp.getProviderId());
//                 cheapestPrices.add(cheapest);
//             });
//         }
    
//         return cheapestPrices;
//     }

//     public List<CourseByEachProviderDTO> getAllCourseProvidersWithProviderNames(String targetCurrency) {
//         List<CourseProvider> courseProviders = courseProviderRepository.findAll();
//         List<CourseByEachProviderDTO> courseByEachProviders = new ArrayList<>();

//         double conversionRate = getConversionRate(targetCurrency);
//         if (conversionRate == -1) {
//             return courseByEachProviders;
//         }

//         for (CourseProvider cp : courseProviders) {
//             CourseByEachProviderDTO dto = new CourseByEachProviderDTO();
//             dto.setPrice(convertToCurrency(cp.getPrice(), cp.getCurrency(), conversionRate, targetCurrency));
//             dto.setCurrency(targetCurrency);
//             dto.setCourseId(cp.getCourseId());

//             Provider provider = providerRepository.findById(cp.getProviderId()).orElse(null);
//             if (provider != null) {
//                 dto.setProviderName(provider.getName());
//             }

//             courseByEachProviders.add(dto);
//         }

//         return courseByEachProviders;
//     }

//     private double convertToCurrency(double price, String sourceCurrency, double conversionRate, String targetCurrency) {
//         if (!targetCurrency.equals(sourceCurrency)) {
//             return price * conversionRate;
//         }
//         return price;
//     }

//     private double getConversionRate(String targetCurrency) {
//         if (conversionRates == null || System.currentTimeMillis() - lastUpdated > 3600000) {
//             try {
//                 String apiKey = "fca_live_g7qLJhzahQOmCMlAPGQZZTfLIAfLccPFjRrqS4mu";
//                 String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=" + apiKey + "&base_currency=USD";
//                 RestTemplate restTemplate = new RestTemplate();
//                 CurrencyConversionResponse response = restTemplate.getForObject(apiUrl, CurrencyConversionResponse.class);

//                 conversionRates = response.getData();
//                 lastUpdated = System.currentTimeMillis();
//             } catch (Exception e) {
//                 System.err.println("Error fetching conversion rates: " + e.getMessage());
//                 e.printStackTrace();
//                 return -1;
//             }
//         }
//         return conversionRates.getOrDefault(targetCurrency, -1.0);
//     }
// }
