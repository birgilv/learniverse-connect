import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { getUserById, deleteUserOnServer } from '../../../../services/user-request';

export default function DeleteUser() {

  const { id } = useParams();
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await getUserById(id);
        ;
        setUser(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching user:', error);
      }
    };
    fetchUser();
  }, [id]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await deleteUserOnServer(id);
      navigate('/admin/user');
      alert('User deleted successfully');
    } catch (error) {
      console.error('Error deleting user:', error);
    }
  };

  if (loading) {
    return <div>Loading...</div>;
  }


  return (
    <div className='form'>
      <div>
        <Link to={"/admin/user"}>
          <button className='button'>← Go back</button>
        </Link>   
      </div>
      <h1>Delete the user "{user.username}"</h1>

      <form onSubmit={handleSubmit}>
      <p>{user.username}</p>
      <button type='submit'>Delete</button>
      </form>
    </div>
  );
}