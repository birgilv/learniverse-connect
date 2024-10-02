import { sendApiPostRequest, sendApiGetRequest, sendApiDeleteRequest } from "./api-requests";

const FAVORITECOURSE_URL = "/favorite-courses";

/**
 * Retrieves the favorite courses of a specific user.
 *
 * @param {int} userId The ID of the user.
 * @returns The outcome of the get request.
 */
export function getFavoriteCoursesFromAUser(userId) {
  return sendApiGetRequest(FAVORITECOURSE_URL + "/user/" + userId);
}

/**
 * Adds a course to the user's favorites.
 *
 * @param {int} userId The ID of the user.
 * @param {int} courseId The ID of the course to be added.
 * @returns The outcome of the post request.
 */
export function addFavoriteCourseToServer(userId, courseId) {
  return sendApiPostRequest(FAVORITECOURSE_URL + "/user/" + userId + "/course/" + courseId);
}

/**
 * Deletes a favorite course from the user's favorites.
 *
 * @param {int} userId The ID of the user.
 * @param {int} courseId The ID of the course to be deleted.
 * @returns The outcome of the delete request.
 */
export function deleteFavoriteCourseOnServer(userId, courseId) {
  return sendApiDeleteRequest(FAVORITECOURSE_URL + "/user/" + userId + "/course/" + courseId);
}
