import {sendApiGetRequest } from "./api-requests";

const TAGS_URL = "/course-tags"

/**
 * Retrieves the tags from the server.
 *
 * @returns The outcome of the get request.
 */
export function getCourseTagsFromServer() {
  return sendApiGetRequest(TAGS_URL);
}
