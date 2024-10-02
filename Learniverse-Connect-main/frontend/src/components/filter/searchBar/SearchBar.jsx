import React, { useState } from 'react';
import HelpPopUp from '../../popUps/modalBox/HelpPopUp';

function SearchBar({ searchQuery, setSearchQuery }) {
  const handleSubmit = (e) => {
    e.preventDefault(); // Prevent the default form submission behavior
    // You can add additional logic here if needed
  };

  return (
    <form className="search-container" role="search" onSubmit={handleSubmit}>
      <label htmlFor="searchInput" className="visually-hidden"></label>
      <input
        id="searchInput"
        className="search-input"
        type="search"
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
        placeholder="Search courses..."
        aria-label="Search courses"
      />
      <div className="info-popup">
        <HelpPopUp />
      </div>
    </form>
  );
}

export default SearchBar;
