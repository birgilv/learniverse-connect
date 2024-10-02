import axios from "axios";

/**
 * The base url for the website.
 */
export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

/**
 * CRUD REST-API Request methods.
 */
const apiRequestMethods = Object.freeze({
  POST: "post",
  GET: "get",
  PUT: "put",
  DELETE: "delete",
});

/**
 * Sends an HTTP POST REST-API request to the backend.
 *
 * @param {string} url The URL that will be used for the request.
 * @param {FormData} data The data to be sent with the request.
 * @returns The response from the request.
 */
export async function sendApiPostRequest(url, data) {
  return sendApiRequest(apiRequestMethods.POST, url, data);
}

/**
 * Sends an HTTP GET REST-API request to the backend.
 *
 * @param {string} url The URL that will be used for the request.
 * @returns The response from the request.
 */
export async function sendApiGetRequest(url) {
  return sendApiRequest(apiRequestMethods.GET, url);
}

/**
 * Sends an HTTP PUT REST-API request to the backend.
 *
 * @param {string} url The URL that will be used for the request.
 * @param {FormData} data The data to be sent with the request.
 * @returns The response from the request.
 */
export async function sendApiPutRequest(url, data) {
  return sendApiRequest(apiRequestMethods.PUT, url, data);
}

/**
 * Sends an HTTP DELETE REST-API request to the backend.
 *
 * @param {string} url The URL that will be used for the request.
 * @returns The response from the request.
 */
export async function sendApiDeleteRequest(url) {
  return sendApiRequest(apiRequestMethods.DELETE, url);
}

/**
 * Sends a REST-API request to the backend, generic function.
 *
 * @param {string} method The request method to be used when making the request. Methods to be used: POST, GET, PUT, DELETE.
 * @param {string} url The URL that will be used for the request.
 * @param {FormData} data The data to be sent with the request.
 * @returns The response from the request.
 */
async function sendApiRequest(method, url, data) {
  try {
    const token = localStorage.getItem("token");
    const config = {
      method: method,
      baseURL: API_BASE_URL,
      url: url,
      data:
        method === apiRequestMethods.POST || apiRequestMethods.PUT
          ? data
          : null,
      headers: url.includes("images")
        ? {
            "Content-Type": "multipart/form-data",
            Authorization: "Bearer " + token,
          }
        : {
            "Content-Type": "application/json",
            Authorization: "Bearer " + token,
          },
    };

    const response = await axios(config);
    return response;
  } catch (error) {
    console.error("Error in sendApiRequest:", error.response);
    localStorage.setItem("ApiRequestError", error.response.data);
    return error.response;
  }
}
