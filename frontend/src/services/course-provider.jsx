import {sendApiGetRequest} from "./api-requests";

/**
 * Retrieves the cheapest course prices.
 *
 * @param {string} targetCurrency The target currency for the prices.
 * @returns The outcome of the get request.
 */
export function getCheapestPriceForEachCourse(targetCurrency) {
    return sendApiGetRequest(`/cheapest-course-prices?targetCurrency=${targetCurrency}`);
  }


  /**
 * Retrieves the most expensive course prices.
 *
 * @param {string} targetCurrency The target currency for the prices.
 * @returns The outcome of the get request.
 */
export function getMostExpensivePriceForEachCourse(targetCurrency) {
  return sendApiGetRequest(`/most-expensive-course-prices?targetCurrency=${targetCurrency}`);
}
  
  /**
   * Retrieves the providers for a particular course.
   *
   * @param {int} courseId The ID of the course.
   * @param {string} targetCurrency The target currency for the prices.
   * @returns The outcome of the get request.
   */
  export function getAllProvidersForACourse(courseId, targetCurrency) {
    return sendApiGetRequest(`course/providers/${courseId}?targetCurrency=${targetCurrency}`);
  }