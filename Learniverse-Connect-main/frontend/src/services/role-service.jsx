import { sendApiPostRequest, sendApiGetRequest, sendApiPutRequest, sendApiDeleteRequest } from "./api-requests";

const ROLE_URL = "/roles";

/**
 * Adds a role to the server.
 *
 * @param {FormData} role The role to be added.
 * @returns The outcome of the post request.
 */
export function addRoleToserver(role) {
  return sendApiPostRequest(ROLE_URL, role);
}

/**
 * Retrieves the roles from the server.
 *
 * @returns The outcome of the get request.
 */
export function getRolesFromServer() {
  return sendApiGetRequest(ROLE_URL);
}

/**
 * Retrieves a role from the server.
 *
 * @param {int} roleId The ID of the role to be retrieved.
 * @returns The outcome of the get request.
 */
export function getOneRoleFromServer(roleId) {
  return sendApiGetRequest(ROLE_URL + "/" + roleId);
}

/**
 * Updates a role on the server.
 *
 * @param {int} roleId The ID of the role to be updated.
 * @param {FormData} role The updated role to be sent.
 * @returns The outcome of the out request.
 */
export function updateRoleOnServer(roleId, role) {
  return sendApiPutRequest(ROLE_URL + "/" + roleId, role);
}

/**
 * Deletes a role from the server.
 *
 * @param {int} roleId The ID of the role to be deleted.
 * @returns The outcome of the delete request.
 */
export function deleteRoleOnServer(roleId) {
  return sendApiDeleteRequest(ROLE_URL + "/" + roleId);
}