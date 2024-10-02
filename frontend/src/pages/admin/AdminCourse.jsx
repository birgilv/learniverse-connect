import React, { useContext, useEffect, useState } from 'react';
import { getCoursesFromServer, updateCourseOnServer } from "../../services/course-service";
import "./Admin.css";
import { Link } from "react-router-dom";
import { getAllProvidersForACourse } from '../../services/course-provider';

function AdminCourse() {
    const [courses, setCourses] = useState([]);
    const [loading, setLoading] = useState(true);

    const [expandedDescription, setExpandedDescription] = useState({});
    const [hiddenCourses, setHiddenCourses] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const courseData = await getCoursesFromServer();
                console.log('course data: ' + courseData.data);
                const coursesWithProviders = await Promise.all(courseData.data.map(async (course) => {
                    const response = await getAllProvidersForACourse(course.id, 'NOK');
                    const providers = response.data || [];
                    
                    return { ...course, providers: providers };
                }));
                setCourses(coursesWithProviders);
                console.log(coursesWithProviders);
                setLoading(false); // Update coursesLoading after fetching data
                const hiddenCourseIds = courseData.data.filter(course => course.hidden).map(course => course.id);
                setHiddenCourses(hiddenCourseIds);
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        };
    
        fetchData();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    const toggleDescription = (courseId) => {
        setExpandedDescription(prevState => ({
            ...prevState,
            [courseId]: !prevState[courseId]
        }));
    }

    const toggleCourseVisibility = async (courseId) => {
        try {
            const updatedCourses = [...courses];
            const courseIndex = updatedCourses.findIndex(course => course.id === courseId);
            const course = updatedCourses[courseIndex];

            const isHidden = hiddenCourses.includes(courseId);
            await updateCourseOnServer(courseId, {
                ...course,
                hidden: !isHidden // Toggle hidden status
            });

            if (isHidden) {
                setHiddenCourses(hiddenCourses.filter(id => id !== courseId));
            } else {
                setHiddenCourses([...hiddenCourses, courseId]);
            }

            setCourses(updatedCourses);
        } catch (error) {
            console.error("Error toggling course visibility:", error);
        }
    }

    // If user is admin, show admin page content
    return (
        <div>
            <div>
                <Link to={"/admin"}>
                <button className='button'>← Go back</button>
                </Link>   
            </div>
            <h1>COURSES</h1>
            <p>This is all the current courses availabe in Learniverse Connect. All visible and hidden courses are listed in the table below. 
                Hidden courses is marked with a purple background and has a "unhide" button to make it visible. The visible courses has a "hide"
                button to hide the specific course. You can easily change whether you want to hide or unhide a each induvidual course.
                To read full description of a course, click on the description section. </p>
            
            {/* Admin page content */}
        
            {/* Courses Table */}
            {loading ? (
                <div>Loading courses...</div>
            ) : (
                <table className='courseTable'>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Providers</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {courses.map(course => (
                            <tr key={course.id} className={hiddenCourses.includes(course.id) ? 'hidden' : ''}>
                                <td>{course.id}</td>
                                <td>{course.title}</td>
                                <td 
                                    className={`description-cell ${expandedDescription[course.id] ? 'expanded' : ''}`} 
                                    onClick={() => toggleDescription(course.id)}
                                >
                                    {course.description}
                                </td>
                                <td>
                                {(course.providers || []).map(provider => ( // Ensure providers is an array
                                    <p key={provider.courseId + provider.providerName}>{provider.providerName}: {provider.price} {provider.currency}</p>
                                ))}
                                </td>
                                <td>
                                    {/* Add action buttons like Edit, Delete, etc. */}
                                    <div className="button-container">
                                        <div className='delete-course-button'>
                                            <Link to={`/admin/course/deleteCourse/${course.id}`}>
                                            <button className="button">Delete</button>
                                            </Link>
                                        </div>
                                        <div className='visibility-course-button'>
                                            <button 
                                                className='button' 
                                                onClick={() => toggleCourseVisibility(course.id)}
                                            >
                                                {hiddenCourses.includes(course.id) ? 'Unhide' : 'Hide'}
                                            </button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default AdminCourse;
