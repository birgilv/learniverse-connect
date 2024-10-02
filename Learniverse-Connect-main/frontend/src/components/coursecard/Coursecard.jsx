import React, { useState, useEffect } from "react";
import "./Coursecard.css";
import CourseCardSkeleton from "./CourseCardSkeleton";
import GetImage from "../crudTest/post/image/GetImage";

export default function Coursecard({ course, favorited, onFavoriteToggle, showPrice = true }) {
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(false);
  }, []);

  const handleToggleFavorite = () => {
    onFavoriteToggle(course.id); // Call the parent function to toggle favorite status
  };

  if (loading) {
    return <CourseCardSkeleton />;
  }

  return (
    <article className="course-card" role="article">
      <div
        className="favorite-icon"
        onClick={handleToggleFavorite}
        aria-label={favorited ? "Remove from favorites" : "Add to favorites"}
        aria-pressed={favorited}
        role="button"
      >
        {favorited ? "★" : "☆"}
      </div>
      <figure className="image-section">
        <GetImage imageId={course.imageId} alt={course.title} />
      </figure>
      <div className="info-section" role="region" aria-label="Course Information">
        <h2>{course.title}</h2>
        {/* <p>Start Date: {course.startDate}</p> */}
        <div className={'price-section' + (showPrice ? '' : ' close')} role="group" aria-label="Price Information">
          {Math.ceil(course.cheapestPrice) === Math.ceil(course.mostExpensivePrice) ? (
            <p>
              Price: {Math.ceil(course.cheapestPrice)} {course.cheapestCurrency}
            </p>
          ) : (
            <p>
              Price Range: {Math.ceil(course.cheapestPrice)} {course.cheapestCurrency} - {Math.ceil(course.mostExpensivePrice)} {course.cheapestCurrency}
            </p>
          )}
          <p>Credits: {course.credit}
          </p>
        </div>
      </div>
    </article>
  );
}
