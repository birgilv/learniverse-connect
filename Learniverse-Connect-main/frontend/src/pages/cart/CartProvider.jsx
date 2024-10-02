import React, { useState, createContext, useEffect } from 'react';

export const CartContext = createContext();

const CartProvider = ({ children }) => {
  const [cart, setCart] = useState(() => {
    try {
      const localData = localStorage.getItem('cart');
      return localData ? JSON.parse(localData) : [];
    } catch (error) {
      console.error("Could not load cart data from localStorage:", error);
      return [];
    }
  });

  const [cartPopped, setCartPopped] = useState(false); // State for cart animation

  useEffect(() => {
    try {
      const data = JSON.stringify(cart);
      localStorage.setItem('cart', data);
    } catch (error) {
      console.error("Could not save cart data to localStorage:", error);
    }
  }, [cart]);

  const addToCart = (course, provider) => {
    setCart(currentCart => [...currentCart, { course, provider }]);
    setCartPopped(true); // Trigger the cart animation
    setTimeout(() => {
      setCartPopped(false);
    }, 300); // Adjust timing to match CSS animation duration
  };

  const removeFromCart = (id) => {
    setCart(prevCart => prevCart.filter(item => item.course.course.id !== id));
  };

  const clearCart = () => {
    setCart([]);
  };

  return (
    <CartContext.Provider value={{ cart, addToCart, removeFromCart, clearCart, cartPopped }}>
      {children}
    </CartContext.Provider>
  );
};

export default CartProvider;
