import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';

// Wrap the App component with CartProvider
ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
      <App />
  </React.StrictMode>
);
