import React, { useContext } from 'react';
import Button from '../button/Button';
import './navList.css';
import iconHome from "/icons/home/home-icon.png";
import iconAbout from "/icons/home/read-more-about.png";
import iconCourses from "/icons/home/find-a-course.png";
import iconAdmin from "/icons/home/admin-icon.png";
import iconCart from '/cart/whiteCart.png';
import { CartContext } from '../../pages/cart/CartProvider';
import { AuthContext } from '../../pages/admin/AuthProvider';

export default function NavList() {
  const { cart } = useContext(CartContext);
  const auth = useContext(AuthContext);

  const user = auth.user;
  // console.log(user.roles[0].authority);  
  // console.log(user.role.title);

  return (
    <nav>
      <ul className="navList">
        <li>
          <Button text={'Home'} src={'/'} img={iconHome} alt={'White house'} imageName={'button-image'} className={'home-header-link'} />
        </li>
        <li>
          <Button text={'Courses'} src={'/courses'} img={iconCourses} alt={'White magnifying glass'} imageName={'button-image'} className={'course-header-link'} />
        </li>
        <li>
          <Button text={'About'} src={'/about'} img={iconAbout} alt={'White open book'} imageName={'button-image'} className={'about-header-link'} />
        </li>
        <li>
          <Button text={'Admin'} src={'/admin'} img={iconAdmin} alt={'White shield with a check mark'} imageName={'button-image'} className={'admin-header-link' + ((user) ? ((user.roles[0].authority == 'ROLE_ADMIN') ? ('-admin') : ('')) : (''))} />
        </li>
        <li>
          <Button text={'Cart (' + (cart.length) + ')'} src={'/cart'} img={iconCart} alt={'White shopping cart'} imageName={'button-image'} className={'cart-header-link'} linkName={'cart-link'} />
        </li>
      </ul>
    </nav>
  );
}