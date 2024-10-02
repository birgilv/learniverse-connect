import React, { useContext, useState } from 'react';
import { CartContext } from './CartProvider';
import './cartPage.css'; 
import '../../index.css';
import Coursecard from '../../components/coursecard/Coursecard';
import ConfirmationModal from '../../components/popUps/modalBox/ConfirmationModalBox';
import { useNavigate } from 'react-router-dom';
import useEmailLogic from '../purchased/EmailLogic';
import Spinner from '../../components/spinner/Spinner';

function CartPage() {
  const { cart, removeFromCart, clearCart } = useContext(CartContext);
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [confirmationType, setConfirmationType] = useState(null);
  const [purchaseItems, setPurchaseItems] = useState([]);
  const [totalPurchasePrice, setTotalPurchasePrice] = useState(0);
  const [loading, setLoading] = useState(false); // State for loading spinner
  const navigate = useNavigate();
  const { sendPurchaseEmail } = useEmailLogic();

  const handleClearCart = () => {
    setConfirmationType("clearCart");
    setShowConfirmation(true);
  };


  const handlePurchase = () => {
    setPurchaseItems([...cart]);
    const totalPrice = cart.reduce((total, { course }) => total + course.selectedProvider.price, 0);
    setTotalPurchasePrice(totalPrice);
    setConfirmationType("purchase");
    setShowConfirmation(true);
  };

  const confirmPurchase = async () => {
    setLoading(true); // Show spinner
    try {
      await sendPurchaseEmail(cart); // Send email after confirmation
      clearCart();
      navigate('/purchased', { state: { emailConfirmationSent: true } }); // Redirect to purchase page with state indicating email confirmation
    } catch (error) {
      console.error('Error sending email:', error);
      alert('Failed to send email');
    } finally {
      setLoading(false); // Hide spinner regardless of email result
    }
  };

  const cancelPurchase = () => {
    setShowConfirmation(false);
  };

  const confirmClearCart = () => {
    clearCart();
    setShowConfirmation(false);
  };

  const cancelClearCart = () => {
    setShowConfirmation(false);
  };

  const totalPrice = cart.reduce((total, { course }) => total + course.selectedProvider.price, 0);
  const currency = cart.length > 0 ? cart[0].course.selectedProvider.currency : "";
  return (
    <div className="cart-page">
      {loading && <div className="spinner"><Spinner /></div>}
      <div className="cart-header">
        <h1>Shopping Cart</h1>
      </div>
      
      {cart.length > 0 && (
        <button className="clear-cart-btn" onClick={handleClearCart}>Clear Cart</button>
      )}
      {cart.length === 0 ? (
        <div className="empty-cart-message">
          <p>Your cart is empty.</p>
        </div>
      ) : (
        <div className="cart-items">
          {cart.map(({ course }) => (
            console.log("course", course),
            <div key={course.id} className="cart-item">
              <Coursecard course={course.course} showPrice={false} />
              <div className="details">
                <p className="provider">Provider: {course.selectedProvider.providerName}</p>
                <p className="price">Price: {course.selectedProvider.currency} {Math.ceil(course.selectedProvider.price)}</p>
              </div>
              <button className="remove-btn" onClick={() => removeFromCart(course.course.id)}>Remove</button>
            </div>
          ))}
        </div>
      )}
      {(cart.length > 0) && (
        <div className="cart-summary">
          <div className="price-summary">
            <p>Total Price: {currency} {Math.ceil(totalPrice)}</p>
          </div>
          <div className="action-buttons">
            <button onClick={handlePurchase} className={cart.length === 0 ? "disabled" : ""}>Purchase</button>
          </div>
        </div>
      )}

      {showConfirmation && confirmationType === "clearCart" && (
        <ConfirmationModal
          message="Are you sure you want to clear your cart?"
          onConfirm={confirmClearCart}
          onCancel={cancelClearCart}
        />
      )}

      {showConfirmation && confirmationType === "purchase" && (
        <ConfirmationModal
          items={purchaseItems}
          totalPrice={totalPurchasePrice}
          currency={currency}
          onConfirm={confirmPurchase}
          onCancel={cancelPurchase}
        />
      )}
    </div>
  );
}  

export default CartPage;
