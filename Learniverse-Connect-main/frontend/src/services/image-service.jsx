import { API_BASE_URL, sendApiPostRequest, sendApiGetRequest, sendApiPutRequest, sendApiDeleteRequest } from "./api-requests";

const IMAGE_URL = "/images";

/**
 * Deletes an image from the server.
 *
 * @param {int} imageId The ID of the image to be deleted.
 * @returns The outcome of the delete request.
 */
export function deleteImageOnServer(imageId) {
  return sendApiDeleteRequest(IMAGE_URL + "/" + imageId);
}

/**
 * Updates an image on the server.
 *
 * @param {int} imageId The ID of the image to be updated.
 * @param {FormData} image The updated image data to be sent.
 * @returns The outcome of the put request.
 */
export function updateImageOnServer(imageId, image) {
  return sendApiPutRequest(IMAGE_URL + "/" + imageId, image);
}

/**
 * Uploads an image to the server.
 *
 * @param {FormData} image The image data to be uploaded.
 * @returns The outcome of the post request.
 */
export function uploadImageToServer(image) {
  return sendApiPostRequest(IMAGE_URL, image);
}

/**
 * Retrieves image data from the server.
 *
 * @param {int} imageId The ID of the image to be retrieved.
 * @returns The outcome of the get request.
 */
export function getImageDataFromServer(imageId) {
  return sendApiGetRequest(IMAGE_URL + "/" + imageId);
}

/**
 * Retrieves imagees from the server.
 *
 * @returns The outcome of the get request.
 */
export function getImagesFromServer() {
  return sendApiGetRequest(IMAGE_URL);
}

/**
 * Generates the URL for retrieving an image from the server.
 *
 * @param {int} imageId The ID of the image to generate the URL for.
 * @returns The generated image URL, or null if ID is not positive.
 */
export function generateImageUrl(imageId) {
  if (imageId > 0) {
    return API_BASE_URL + IMAGE_URL + "/" + imageId + "/data";
  } else {
    return null;
  }
}