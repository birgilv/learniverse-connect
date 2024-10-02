// emailLogic.js
import { sendEmail } from '../../services/send-email';
import { AuthContext } from '../admin/AuthProvider';
import { useContext } from 'react';

const useEmailLogic = () => {
  const { user } = useContext(AuthContext);

  const sendPurchaseEmail = async (cart) => {
    const emailData = {
      email: user.sub,
      name: user.name,
      items: cart.map(({ course }) => {
        return {
          name: course.course.title,
          price: course.selectedProvider.price,
          currency: course.selectedProvider.currency,
          provider: course.selectedProvider.providerName,
        };
      }),
    };


    try {
      const response = await sendEmail(emailData.email, "Purchase Confirmation", emailData.items);
      console.log("response", response);
    } catch (error) {
      console.error('Error sending email:', error);
      alert('Failed to send email');
    }
  };

  return { sendPurchaseEmail };
};

export default useEmailLogic;
