import { sendApiPostRequest, sendApiGetRequest, sendApiPutRequest, sendApiDeleteRequest } from "./api-requests";

const COURSE_URL = "/courses";

/**
 * Adds a course to the server.
 *
 * @param {FormData} course The course to be added.
 * @returns The outcome of the post request.
 */
export function addCourseToServer(course) {
  return sendApiPostRequest(COURSE_URL, course);
}

/**
 * Retrieves the courses from the server.
 *
 * @returns The outcome of the get request.
 */
export function getCoursesFromServer() {
  return sendApiGetRequest(COURSE_URL);
}

/**
 * Retrives a course from the server.
 *
 * @param {int} courseId The ID of the course to be retrieved.
 * @returns The outcome of the get request.
 */
export function getOneCourseFromServer(courseId) {
  return sendApiGetRequest(COURSE_URL + "/" + courseId);
}

/**
 * Updates a course on the server.
 *
 * @param {int} courseID The ID of the course to be updated.
 * @param {FormData} course The updated course to be sent.
 * @returns The outcome of the put request.
 */
export function updateCourseOnServer(courseId, course) {
  return sendApiPutRequest(COURSE_URL + "/" + courseId, course);
}

/**
 * Deletes a course from the server.
 *
 * @param {int} courseId The ID of the course to be deleted.
 * @returns The outcome of the delete request.
 */
export function deleteCourseOnServer(courseId) {
  return sendApiDeleteRequest(COURSE_URL + "/" + courseId);
}