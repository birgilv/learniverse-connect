import React, { useState, useEffect } from "react";
import { getFavoriteCoursesFromAUser } from "../../../../services/favorite-course";
import { Link } from "react-router-dom";
import Coursecard from "../../../coursecard/Coursecard";
import useCoursesPageLogic from "../../../../pages/courses/coursesPageLogic";

export default function GetFavoriteCourses({ userId }) {
  const { courses } = useCoursesPageLogic();
  const [favoriteCourses, setFavoriteCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  async function fetchFavoriteCourses() {
    try {
      const response = await getFavoriteCoursesFromAUser(userId);
      setFavoriteCourses(response.data);
    } catch (error) {
      setError(error);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    fetchFavoriteCourses();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  // Ensure that courses are available before rendering
  if (!courses || courses.length === 0) {
    return <div>No courses available.</div>;
  }

  return (
    <div className="favorite-courses">
      <h2>Your Favorite Courses</h2>
      {favoriteCourses.map((favoriteCourse) => {
        // Find the corresponding course from 'courses' using its id
        const course = courses.find(course => course.id === favoriteCourse.course.id);
        if (!course) {
          // Handle the case where the course is not found
          return <div key={favoriteCourse.course.id}>Course not found</div>;
        }
        return (
          <Link
            to={`/course/${favoriteCourse.course.id}`}
            key={favoriteCourse.course.id}
          >
            <Coursecard course={course} />
          </Link>
        );
      })}
    </div>
  );
}
