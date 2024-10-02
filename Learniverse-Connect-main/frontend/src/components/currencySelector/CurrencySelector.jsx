import React, { useState, useContext } from 'react';

import ConfirmationModal from '../popUps/modalBox/ConfirmationModalBox.jsx';
import { CartContext } from '../../pages/cart/CartProvider.jsx';
import { useCurrencyContext } from './CurrencyContext.jsx';

const CurrencySelector = ({ currencies }) => {
  const { targetCurrency, handleCurrencyChange } = useCurrencyContext();
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [selectedCurrency, setSelectedCurrency] = useState();
  
    const { clearCart } = useContext(CartContext);

  const handleCurrencyChangeWithConfirmation = (newCurrency) => {
    if (targetCurrency !== newCurrency) {
      setShowConfirmationModal(true);
      setSelectedCurrency(newCurrency);
    }
  };

  const confirmCurrencyChange = () => {
    clearCart(); // Call clearCart from CartContext
    handleCurrencyChange(selectedCurrency);
    setShowConfirmationModal(false);
  };

  const cancelCurrencyChange = () => {
    setShowConfirmationModal(false);
  };

  return (
    <div>
      <label htmlFor="currencySelect">Select Preferred Currency:</label>
      <select
        id="currencySelect"
        value={targetCurrency}
        onChange={(e) => handleCurrencyChangeWithConfirmation(e.target.value)}
        aria-label="Select Preferred Currency"
        aria-labelledby="currencySelect"
      >
        {currencies.map((currency) => (
          <option key={currency} value={currency}>
            {currency}
          </option>
        ))}
      </select>
      {showConfirmationModal && (
        <ConfirmationModal
          message="Changing currency will remove items from your cart. Are you sure you want to proceed?"
          onConfirm={confirmCurrencyChange}
          onCancel={cancelCurrencyChange}
        />
      )}
    </div>
  );
};

export default CurrencySelector;





