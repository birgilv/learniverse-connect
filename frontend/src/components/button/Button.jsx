import React from 'react';

export default function Button({ text, src, img, alt, imageName, linkName, className }) {

  return (
    <a href={src} className={linkName}>
      <div className={className}>
        <img src={img} alt={alt} className={imageName} /> {text}
      </div>
    </a>
  )
}