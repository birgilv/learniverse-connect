import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Coursecard from "../../../coursecard/Coursecard";
import { useParams, useNavigate, Link } from 'react-router-dom';
import { getOneCourseFromServer, deleteCourseOnServer } from '../../../../services/course-service';


export default function DeleteCourse() {

  const { id } = useParams();
  const navigate = useNavigate();
  const [course, setCourse] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCourse = async () => {
      try {
        const response = await getOneCourseFromServer(id);
        setCourse(response.data);
        console.log(response.data)
        setLoading(false);
      } catch (error) {
        console.error('Error fetching course:', error);
      }
    };
    fetchCourse();
  }, [id]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await deleteCourseOnServer(id);
      console.log(deleteCourseOnServer(id))
      navigate('/admin/course');
      alert('Course deleted successfully');
    } catch (error) {
      console.error('Error deleting course:', error);
    }
  };

  if (loading) {
    return <div>Loading...</div>;
  }


  return (
    <div className='form'>
      <div>
        <Link to={"/admin/course"}>
          <button className='button'>← Go back</button>
        </Link>   
      </div>
      <h1>Delete the course "{course.title}"</h1>
      <p>Under is a preveiw of the coursecard to the course to be deleted. The deletion is permanent and cannot be reversed.</p>
      <form onSubmit={handleSubmit}>
      <div className="coursecard-container">
        {course && <Coursecard course={course} />}
      </div>
      <button type='submit'>Delete</button>
      </form>
    </div>
  );
}