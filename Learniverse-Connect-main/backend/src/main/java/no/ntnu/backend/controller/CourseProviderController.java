package no.ntnu.backend.controller;

import java.util.List;

import no.ntnu.backend.dto.CourseByEachProviderDTO;
import no.ntnu.backend.model.CourseProvider;
import no.ntnu.backend.service.CourseProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for managing operations related to course providers.
 * Handles HTTP requests/responses for course provider-related endpoints.
 *
 * @author Group 01
 * @version 23.05.2024
 */
@RestController
@CrossOrigin
public class CourseProviderController {

    private final CourseProviderService courseProviderService;

    @Autowired
    public CourseProviderController(CourseProviderService courseProviderService) {
        this.courseProviderService = courseProviderService;
    }

    /**
     * Retrieves providers offering a particular course.
     *
     * @param courseId       The ID of the course
     * @param targetCurrency The target currency for conversion
     * @return List of CourseByEachProviderDTO containing details of providers offering the course
     */
    @Operation(summary = "Retrieves providers offering a particular course", description = "Retrieves providers offering a particular course.")
    @GetMapping("/api/course/providers/{courseId}")
    public List<CourseByEachProviderDTO> getProvidersForCourse(@PathVariable Long courseId,
                                                               @RequestParam String targetCurrency) {
        return courseProviderService.getProvidersForCourse(courseId, targetCurrency);
    }

    /**
     * Retrieves the cheapest course prices.
     *
     * @param targetCurrency The target currency for conversion
     * @return List of CourseProvider containing details of the cheapest course prices
     */
    @Operation(summary = "Retrieves the cheapest course prices", description = "Retrieves the cheapest course prices.")
    @GetMapping("/api/cheapest-course-prices")
    public List<CourseProvider> getCheapestCoursePrices(@RequestParam String targetCurrency) {
        return courseProviderService.getMinimumConvertedPriceForEachCourse(targetCurrency);
    }

    /**
     * Retrieves the most expensive course prices.
     *
     * @param targetCurrency The target currency for conversion
     * @return List of CourseProvider containing details of the most expensive course prices
     */
    @Operation(summary = "Retrieves the most expensive course prices", description = "Retrieves the most expensive course prices.")
    @GetMapping("/api/most-expensive-course-prices")
    public List<CourseProvider> getMostExpensiveCoursePrices(@RequestParam String targetCurrency) {
        return courseProviderService.getMaximumConvertedPriceForEachCourse(targetCurrency);
    }
}
