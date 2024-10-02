import { sendApiPostRequest, sendApiGetRequest, sendApiPutRequest, sendApiDeleteRequest } from "./api-requests";

const CATEGORY_URL = "/categories";

/**
 * Adds a category to the server.
 *
 * @param {FormData} category The category to be added.
 * @returns The outcome of the post request.
 */
export function addCategoryToServer(category) {
  return sendApiPostRequest(CATEGORY_URL, category);
}

/**
 * Retrieves the categories from the server.
 *
 * @returns The outcome of the get request.
 */
export function getCategoriesFromServer() {
  return sendApiGetRequest(CATEGORY_URL);
}

/**
 * Retrieves a category from the server.
 *
 * @param {int} categoryId The ID of the category to be retrieved.
 * @returns The outcome of the get request.
 */
export function getOneCategoryFromServer(categoryId) {
  return sendApiGetRequest(CATEGORY_URL + "/" + categoryId);
}

/**
 * Updates a category on the server.
 *
 * @param {int} categoryId The ID of the category to be updated.
 * @param {FormData} category The updated category to be sent.
 * @returns The outcome of the put request.
 */
export function updateCategoryOnServer(categoryId, category) {
  return sendApiPutRequest(CATEGORY_URL + "/" + categoryId, category);
}

/**
 * Deletes a category from the server.
 *
 * @param {int} categoryId The ID of the category to be deleted.
 * @returns The outcome of the delete request.
 */
export function deleteCategoryOnserver(categoryId) {
  return sendApiDeleteRequest(CATEGORY_URL + "/" + categoryId);
}