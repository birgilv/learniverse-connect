// import React from "react";

// import GetImage from "../../components/crudTest/post/image/GetImage";

//  const handleAddToCart = () => {
//     if (!selectedProvider) {
//       setShowWarning(true);
//       setShowSuccessMessage(false);
//     } else if (!courseAdded) {
//       addToCart({ course, selectedProvider });
//       setShowSuccessMessage(true);
//       setCourseAdded(true);
//     }
//   };


// const CourseDetails = ({ course, providers, selectedProvider, handleProviderSelection, handleKeyPress, handleToggleFavorite, favorited, showWarning, showSuccessMessage, courseAdded, addToCart, addToCartButtonRef, toggleDescription, isDescriptionExpanded }) => {
//   return (
//     <div className="CourseDetails">
//       <div className="title-image-favorite">
//         <div className="title-image-section">
//           <h2 className="title">{course.title}</h2>
//           <div className="course-image-section">
//             <GetImage imageId={course.id} />
//           </div>
//         </div>
//         <button
//           className={`favoriteButton ${favorited ? "favorited" : ""}`}
//           onClick={handleToggleFavorite}
//           onKeyDown={(e) => handleKeyPress(e, selectedProvider)}
//           tabIndex={0}
//           aria-label={favorited ? "Remove from Favorites" : "Add to Favorites"}
//         >
//           {favorited ? "★ Remove from Favorites" : "☆ Add to Favorites"}
//         </button>
//       </div>
//       <p>
//         {course.description.slice(
//           0,
//           isDescriptionExpanded ? course.description.length : 150
//         )}
//         {!isDescriptionExpanded && (
//           <button
//             className="expand-button"
//             onClick={toggleDescription}
//             tabIndex={0}
//             aria-expanded={isDescriptionExpanded}
//           >
//             {isDescriptionExpanded ? "Read Less" : "Read More"}
//           </button>
//         )}
//       </p>
//       <p>Start Date: {course.startDate}</p>
//       <p>Related Certification: {course.relatedCertification}</p>
//       <h3>Providers:</h3>
//       <ul>
//         {providers.map((provider) => (
//           <li key={provider.providerId}>
//             <label
//               className={`providerLabel ${courseAdded ? "disabled" : ""}`}
//               tabIndex={0}
//               onKeyDown={(e) => handleKeyPress(e, provider)}
//             >
//               <input
//                 type="radio"
//                 name="provider"
//                 checked={selectedProvider === provider}
//                 onChange={() => handleProviderSelection(provider)}
//                 disabled={courseAdded}
//               />
//               {provider.providerName} - Price: {Math.ceil(provider.price)}{" "}
//               {provider.currency}
//             </label>
//           </li>
//         ))}
//       </ul>
//       {showWarning && (
//         <div className="warning" role="alert">
//           Please select a provider before adding to cart.
//         </div>
//       )}
//       {showSuccessMessage && (
//         <div className="success-message" role="alert">
//           Course successfully added to cart!
//         </div>
//       )}
//       <button
//         className="addToCartButton"
//         onClick={handleAddToCart}
//         disabled={!selectedProvider || courseAdded}
//         onKeyDown={(e) => handleKeyPress(e, selectedProvider)}
//         tabIndex={0}
//         ref={addToCartButtonRef}
//       >
//         {courseAdded ? "Already Added to Cart" : "Add to Cart"}
//       </button>
//     </div>
//   );
// };

// export default CourseDetails;
