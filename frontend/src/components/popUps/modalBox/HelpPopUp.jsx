import React, { useState } from "react";
import "./helpPopUp.css";

const HelpPopUp = () => {
  const [showPopup, setShowPopup] = useState(false);
  const handleHover = () => {
    setTimeout(() => {
      setShowPopup(true);
    }, 500);
  };

  const handleMouseLeave = () => {
    setShowPopup(false);
  };

  return (
    <div className="info-popup" onMouseEnter={handleHover} onMouseLeave={handleMouseLeave}>
      <button className="help-button"></button>
      {showPopup && (
        <div className="popup-content">
          <p>Search for title of course or tags<br /><br />Change currency in profile page</p> {/* Content to display in the popup */}
        </div>
      )}
    </div>
  );
};

export default HelpPopUp;
