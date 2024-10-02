import React, { useContext } from 'react';
import "./Home.css";
import frontImage from '/home/front_image.png'
import findACourse from '/icons/home/find-a-course.png'
import createAnAccount from '/icons/home/create-an-account.png'
import readMoreAbout from '/icons/home/read-more-about.png'
import Button from '../../components/button/Button';
import { AuthContext } from '../../pages/admin/AuthProvider';
import iconAdmin from "/icons/home/admin-icon.png";

export default function Home() {
  const auth = useContext(AuthContext);

  const user = auth.user;

  return (
    <main>
      <section className="home-container">
        <div className='front-image'>
          <img className='image' src={frontImage} alt="A group smiling at each other whilst working together" />
        </div>
        <section className='home-content'>
          <h1>Welcome to Learniverse Connect</h1>
          <p>Start Learning Today!</p>
          <div className='button-row'>
            <Button src={'/courses'} text={'Find a course →'} className={'home-row-button'} linkName={'button-link'} img={findACourse} imageName={'button-image'} alt={'White magnifying glass'} />
            <Button src={'/register'} text={'Create your account →'} className={'home-row-button'} linkName={'button-link'} img={createAnAccount} imageName={'button-image'} alt={'White upper-part of a peprson'} />
            <Button src={'/about'} text={'Read more about us →'} className={'home-row-button'} linkName={'button-link'} img={readMoreAbout} imageName={'button-image'} alt={'White open book'} />
            <Button src={'/admin'} text={'Admin Controller →'} className={'home-row-button' + ((user) ? ((user.roles[0].authority == 'ROLE_ADMIN') ? ('') : ('-none')) : ('-none'))} linkName={'button-link'} img={iconAdmin} imageName={'button-image'} alt={'White shield with a check mark'}/>
          </div>
        </section>
      </section>
    </main>
  );
}
