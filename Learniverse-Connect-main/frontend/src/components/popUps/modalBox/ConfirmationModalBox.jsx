import React from 'react';
import './confirmationModal.css';

function ConfirmationModal({ message, items, totalPrice, currency, onConfirm, onCancel }) {
  return (
    <section className="modal-overlay" role="dialog" aria-labelledby="confirmation-modal-title">
      <div className="modal" role="document">
        <h2 id="confirmation-modal-title">Confirmation</h2>
        <p>{message}</p>
        {items && (
          <div>
            <p className="total-items">Items:</p>
            <ul aria-label="List of items">
              {items.map(({ course }) => (
                <li key={course.id}>
                  {course.course.title} - {course.selectedProvider.providerName} - {course.selectedProvider.currency} {Math.ceil(course.selectedProvider.price)}
                </li>
              ))}
            </ul>
            <p className="total-price">Total Price: {currency} {Math.ceil(totalPrice)}</p>
          </div>
        )}
        <div className="modal-buttons">
          <button onClick={onConfirm} aria-label="Confirm">Confirm</button>
          <button onClick={onCancel} aria-label="Cancel">Cancel</button>
        </div>
      </div>
    </section>
  );
}

export default ConfirmationModal;
