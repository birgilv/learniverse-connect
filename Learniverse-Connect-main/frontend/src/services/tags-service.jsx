import { sendApiPostRequest, sendApiGetRequest, sendApiPutRequest, sendApiDeleteRequest } from "./api-requests";

const TAGS_URL = "/tags"

/**
 * Adds a tag to the server.
 *
 * @param {FormData} tag The tag to be added.
 * @returns The outcome of the post request.
 */
export function addTagToServer(tag) {
  return sendApiPostRequest(TAGS_URL, tag);
}

/**
 * Retrieves the tags from the server.
 *
 * @returns The outcome of the get request.
 */
export function getTagsFromServer() {
  return sendApiGetRequest(TAGS_URL);
}

/**
 * retrieves a tag from the server.
 *
 * @param {int} tagId The ID of the tag to be retrieved.
 * @returns The outcome of the get request.
 */
export function getOneTagFromServer(tagId) {
  return sendApiGetRequest(TAGS_URL + "/" + tagId);
}

/**
 * Updates a tag on the server.
 *
 * @param {int} tagId The ID of the tag to be updated.
 * @param {FormData} tag The updated tag to be sent.
 * @returns The outcome of the put request.
 */
export function updateTagOnServer(tagId, tag) {
  return sendApiPutRequest(TAGS_URL + "/" + tagId, tag)
}

/**
 * Deletes a tag from the server.
 *
 * @param {int} tagId The ID of the tag to be deleted.
 * @returns The outcome of the delete request.
 */
export function deleteTagOnServer(tagId) {
  return sendApiDeleteRequest(TAGS_URL + "/" + tagId);
}