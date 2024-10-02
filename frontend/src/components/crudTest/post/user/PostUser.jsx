import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Select from 'react-select';
import { useNavigate, Link } from "react-router-dom";
import { addUserToServer } from '../../../../services/user-request';
import { getRolesFromServer } from '../../../../services/role-service';


export default function PostUser() {
  const [formData, setFormData] = useState({
    id: '',
    username: '',
    startDate: '',
    email: '',
    password: '', 
    roleId: ''
  });

  
  const navigate = useNavigate();
  const [roles, setRoles] = useState([]);
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const value = e.target.value;
    setFormData({
      ...formData,
      [e.target.id]: value
    });
  };


  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const rolesResponse = await getRolesFromServer();
        setRoles(rolesResponse.data);

      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    };
    fetchUserData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault(); 

    try {
      const userData = {
        id: formData.id,
        username: formData.username,
        startDate: formData.startDate,
        email: formData.email,
        password: formData.password,
        role: formData.roleId
      };

      await addUserToServer(userData);
      navigate('/admin/user');
      alert('User added successfully');
    } catch (error) {
      console.error('Error:', error);
      setError('An error occurred while submitting the form. Please try again.');

    };
  };

  return (
    <div className='form'>
    <div>
        <Link to={"/admin/user"}>
          <button className='button'>← Go back</button>
        </Link>
            
      </div>
      <h1>Create new user</h1>
      <p>Here you can create a user</p>

      <form onSubmit={handleSubmit}>

        <div className="form-group">
          <label htmlFor='username'>Username</label>
          <input id='username' placeholder="Enter username" value={formData.username} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label htmlFor='startDate'>Start date</label>
          <input type='date' id='startDate' placeholder="Enter start date" value={formData.startDate} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label htmlFor='email'>Email</label>
          <input type='email' id='email' placeholder="Enter email" value={formData.email} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label htmlFor='password'>Password</label>
          <input type='password' id='password' placeholder="Enter password" value={formData.password} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label htmlFor="roleId">Role</label>
          <select id="roleId" value={formData.roleId} onChange={handleChange} required>
            <option value="" disabled>Select role</option>
            {roles.map((role) => (
              <option key={role.id} value={role.id}>{role.title}</option>
            ))}
          </select>
        </div>
        
        <button type='submit'>Submit</button>
      </form>
    </div>
  );
}
