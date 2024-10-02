import { sendApiPostRequest, sendApiGetRequest, sendApiPutRequest, sendApiDeleteRequest } from "./api-requests";

const PROVIDER_URL = "/providers"

/**
 * Adds a provider to the server.
 *
 * @param {FormData} provider The provider to be added.
 * @returns The outcome of the post request.
 */
export function addProviderToServer(provider) {
  return sendApiPostRequest(PROVIDER_URL, provider);
}

/**
 * Retrieves the providers from the server.
 *
 * @returns The outcome of the get request.
 */
export function getProvidersFromServer() {
  return sendApiGetRequest(PROVIDER_URL);
}

/**
 * Retrieves a provider from the server.
 *
 * @param {int} providerId The ID of the provider to be retrieved.
 * @returns The outcome of the get request.
 */
export function getOneProviderFromServer(providerId) {
  return sendApiGetRequest(PROVIDER_URL + "/" + providerId);
}

/**
 * Updates a provider on the server.
 *
 * @param {int} providerId The ID of the provider to be updated.
 * @param {FormData} provider The updates provider to be sent.
 * @returns The outcome of the put request.
 */
export function updateProviderOnServer(providerId, provider) {
  return sendApiPutRequest(PROVIDER_URL + "/" + providerId, provider);
}

/**
 * Deletes a provider from the server.
 *
 * @param {int} providerId The ID of the provider to be deleted.
 * @returns The outcome of the delete request.
 */
export function deleteProviderOnServer(providerId) {
  return sendApiDeleteRequest(PROVIDER_URL + "/" + providerId);
}