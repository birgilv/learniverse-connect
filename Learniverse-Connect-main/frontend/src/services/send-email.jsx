import { sendApiPostRequest } from "./api-requests";

const EMAIL_URL = "/purchased";

/**
 * Sends an email.
 *
 * @param {string} toEmail The email address of the recipient.
 * @param {string} subject The subject of the email.
 * @param {string} text The content of the email.
 * @returns The outcome of the post request.
 */
export function sendEmail(toEmail, subject, items) {
  const text = items.map(item => `${item.name}: ${Math.ceil(item.price)} ${item.currency} ${item.provider}`).join('\n');
  return sendApiPostRequest(EMAIL_URL, { toEmail, subject, text });
}

