// CurrencyLogic.js
import { useState } from 'react';

export function useCurrencyLogic(defaultCurrency = 'NOK') {
  const [targetCurrency, setTargetCurrency] = useState(defaultCurrency);

  const handleCurrencyChange = (currency) => {
    setTargetCurrency(currency);
  };

  return {
    targetCurrency,
    handleCurrencyChange,
  };
}
