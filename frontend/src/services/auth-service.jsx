import { sendApiPostRequest } from "./api-requests";

const AUTH_URL = "/authenticate";

export async function addUserToServer(userData) {
    return sendApiPostRequest(AUTH_URL + "/signup", userData);
}

export function authenticateUser(credentials) {
    return sendApiPostRequest(AUTH_URL, credentials);
}

  


