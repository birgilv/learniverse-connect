import {
  sendApiPostRequest,
  sendApiGetRequest,
  sendApiPutRequest,
  sendApiDeleteRequest,
} from "./api-requests";

const PROFILE_URL = "/users";

/**
 * Retrieves the user from the server.
 *
 * @returns The outcome of the get request.
 */
export function getUsersFromServer() {
  return sendApiGetRequest(PROFILE_URL);
}

/**
 * Retrieves a user from the server by ID.
 *
 * @param {int} userId The ID of the user to be retrieved.
 * @returns The outcome of the get request.
 */
export async function getUserById(userId) {
  return sendApiGetRequest(PROFILE_URL + "/" + userId);
}

/**
 * Retrieves a user from the server by Email.
 *
 * @param {String} email The email of the user to be retrieved.
 * @returns The outcome of the get request.
 */
export async function getUserByEmail(email) {
  return sendApiGetRequest(PROFILE_URL + "/email/" + email);
}

/**
 * Deletes a user from the server.
 *
 * @param {int} userId The ID of the user to be deleted.
 * @returns The outcome of the delete request.
 */
export function deleteUserOnServer(userId) {
  return sendApiDeleteRequest(PROFILE_URL + "/" + userId);
}

/**
 * Updates a user on the server.
 *
 * @param {int} userID The ID of the user to be updated.
 * @param {FormData} user The updated user to be sent.
 * @returns The outcome of the put request.
 */
export function updateUserOnServer(userId, user) {
  console.log("userId" + userId);
  console.log("user" + JSON.stringify(user));
  return sendApiPutRequest(PROFILE_URL + "/" + userId, user);
}

/**
 * Adds a user to the server.
 *
 * @param {FormData} user The user to be added.
 * @returns The outcome of the post request.
 */
export function addUserToServer(user) {
  return sendApiPostRequest(PROFILE_URL, user);
}

const REGISTER_URL = "/signup";

/**
 *
 *
 * @param {FormData} data
 * @returns
 */
export function postSignupToServer(data) {
  return sendApiPostRequest(REGISTER_URL, data);
}

const AUTH_URL = "/authenticate";

/**
 *
 *
 * @param {FormData} data
 * @returns
 */
export function postAuthToServer(data) {
  return sendApiPostRequest(AUTH_URL, data);
}
