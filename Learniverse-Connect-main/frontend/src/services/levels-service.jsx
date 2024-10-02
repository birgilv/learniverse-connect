import { sendApiPostRequest, sendApiGetRequest, sendApiPutRequest, sendApiDeleteRequest } from "./api-requests";

const LEVEL_URL = "/levels";

/**
 * Adds a level to the server.
 *
 * @param {FormData} level The level to be added.
 * @returns The outcome of the post request.
 */
export function addLevelToServer(level) {
  return sendApiPostRequest(LEVEL_URL, level);
}

/**
 * Retrieves the levels from the server.
 *
 * @returns The outcome of the get request.
 */
export function getLevelsFromServer() {
  return sendApiGetRequest(LEVEL_URL);
}

/**
 * Retrieves a level from the server.
 *
 * @param {int} levelId The ID of the level to be retrieved.
 * @returns The outcome of the get request.
 */
export function getOneLevelFromServer(levelId) {
  return sendApiGetRequest(LEVEL_URL + "/" + levelId);
}

/**
 * Updates a level on the server.
 *
 * @param {int} levelId the ID of the level to be updated.
 * @param {formData} level The updated level to be sent.
 * @returns The outcome of the put request.
 */
export function updateLevelOnServer(levelId, level) {
  return sendApiPutRequest(LEVEL_URL + "/" + levelId, level);
}

/**
 * Deletes a level from the server.
 *
 * @param {int} levelId The ID of the level to be deleted.
 * @returns The outcome of the delete request.
 */
export function deleteLevelOnServer(levelId) {
  return sendApiDeleteRequest(LEVEL_URL + "/" + levelId);
}