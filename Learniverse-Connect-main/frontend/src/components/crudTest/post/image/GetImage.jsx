import { React, useEffect, useState } from "react";
import { generateImageUrl, getImageDataFromServer } from "../../../../services/image-service";

/**
 * 
 *
 * @param {int} imageId 
 * @returns 
 */
export default function GetImage({imageId, width}) {
  const [altText, setAltText] = useState('');

  /**
   * Gets alt-text from database
   */
  function getAltText() {
    getImageDataFromServer(imageId)
    .then(response => {
      const alt = response.data.alt;
      setAltText(alt);
    }).catch(error => {
      console.error("Error fetching alt data:", error);
    });
  }

  useEffect(() => {
    getAltText();
  }, [imageId]);

  return (
    <div className="image">
      <img src={altText ? generateImageUrl(imageId) : null} width={width} alt={altText}/>
    </div>
  )
}