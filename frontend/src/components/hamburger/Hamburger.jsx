import './Hamburger.css';
import React, { useState, useEffect, useRef } from 'react';
import Hamburger from 'hamburger-react';
import NavList from './NavList';

export default function HamburgerMenu() {
  const [isOpen, setOpen] = useState(false);
  const menuRef = useRef(null);

  useEffect(() => {
    function handleClickOutside(event) {
      if (menuRef.current && !menuRef.current.contains(event.target)) {
        setOpen(false);
      }
    }

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [menuRef]);

  const toggleMenu = () => {
    setOpen(!isOpen);
  };

  return (
    <div role="navigation" aria-label="Main Menu" className={'hamburger-menu' + (isOpen ? ' open' : '')} ref={menuRef}>
      <Hamburger direction='right' toggled={isOpen} onToggle={toggleMenu} color='#ffffff'/>
      <nav className={'menu' + (isOpen ? ' open' : '')}>
        <hr className='line' />
        <NavList />
      </nav>
    </div >
  );
}
