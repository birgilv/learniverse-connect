import React, { createContext, useState, useEffect, useContext } from 'react';

const CurrencyContext = createContext();

export const useCurrencyContext = () => useContext(CurrencyContext);

export const CurrencyProvider = ({ children }) => {
  const initialCurrency = localStorage.getItem('targetCurrency') || 'NOK';
  const [targetCurrency, setTargetCurrency] = useState(initialCurrency);

  const handleCurrencyChange = (currency) => {
    setTargetCurrency(currency);
  };

  useEffect(() => {
    localStorage.setItem('targetCurrency', targetCurrency);
  }, [targetCurrency]);

  return (
    <CurrencyContext.Provider value={{ targetCurrency, handleCurrencyChange }}>
      {children}
    </CurrencyContext.Provider>
  );
};