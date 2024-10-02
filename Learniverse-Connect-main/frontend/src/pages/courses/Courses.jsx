import React, { useState, useEffect } from "react";
import './Courses.css';
import '../../index.css';
import Coursecard from "../../components/coursecard/Coursecard.jsx";
import CourseCardSkeleton from "../../components/coursecard/CourseCardSkeleton.jsx";

function Courses({ courses }) {
  const [favoritedCourses, setFavoritedCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [visibleCourses, setVisibleCourses] = useState([]);
  const [anyVisibleCourses, setAnyVisibleCourses] = useState(false);
  const favorites = localStorage.getItem("favorites");

  const toggleFavorite = (courseId) => {
    if (favoritedCourses.includes(courseId)) {
      setFavoritedCourses(favoritedCourses.filter(id => id !== courseId));
    } else {
      setFavoritedCourses([...favoritedCourses, courseId]);
    }
  };

  useEffect(() => {
    const filteredCourses = courses.filter(course => !course.hidden);
    setVisibleCourses(filteredCourses);
    setLoading(true);

    if (filteredCourses.length > 0) {
      setLoading(false);
      setAnyVisibleCourses(true);
    } else {
      setAnyVisibleCourses(false);
    }
  }, [courses]);

  return (
    <div className="courses" role="list" aria-live="polite">
      {loading && !anyVisibleCourses ? (
        Array(6).fill().map((_, index) => (
          <div className="coursecards-skeleton" key={index} role="listitem">
            <CourseCardSkeleton />
          </div>
        ))
      ) : (
        visibleCourses.map((course) => (
          <div className="coursecards" key={course.id} role="listitem">
            <a href={`/course/${course.id}`} aria-label={`Course: ${course.title}`}>
              <Coursecard
                course={course}
                favorited={favorites ? favorites.includes(course.id) : false}
                onFavoriteToggle={() => toggleFavorite(course.id)}
              />
            </a>
          </div>
        ))
      )}
    </div>
  );
}

export default Courses;
