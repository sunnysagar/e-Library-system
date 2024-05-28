import React, { useState } from 'react';
import axios from 'axios';
import '../style.css';

import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
// import LoginPage from './LoginPage';


const AdminSignupPage = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    password: ''
  });

  const [error, setError] = useState('');

  const handleSuccess = () => {
    toast.success('You have created an account successfully!');
  }
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8009/admin/signup', formData);
      // Redirect or show success message
      handleSuccess();
      navigate('/admin/home');
    } catch (error) {
      console.error('Error signing up:', error);
      setError("Password must contain at least 8 characters, including at least one digit, one lowercase and uppercase," +
      "letter, one special character, and no whitespace.")
    }
  };

  return (
    <div className='form-container'>
      {/* <div className='select'>
        <a href='/admin/signup' id='admin'>Admin</a>
        <a href='/student/login' id='stu'>Student</a>
      </div> */}
      <div className='formm'>
        <div className='span'>
          <div className='left'>
            <h2>Create Account</h2>
          </div>
          <div className='right'>
            <h3> <a href='/admin/login'>Sign in</a></h3>
          </div>
        
        </div>
        <form onSubmit={handleSubmit}>

          <div className='input-field'>
              <label>Name</label>
              <input type="text" name="name" placeholder="Your Name" required="true" onChange={handleChange} />
            
          </div>

          <div className='input-field'>
              <label>Email</label>
              <input type="email" name="email" placeholder="Your Email"  required="true" onChange={handleChange} />
              
          </div>

          <div className='input-field'>
              <label>Phone</label>
              <input type="text" name="phone" placeholder="Your Mobile number" required="true" onChange={handleChange} />    
          </div>

          <div className='input-field'>
              <label>Password</label>
              <input type="password" name="password" placeholder="Enter password" required="true" onChange={handleChange} />    
          </div>

          <button type="submit">Sign Up</button>
          {error && <span className='error'>{error}</span>} 
            <div className='Log'>
              <a href="/admin/login">Already have an account? </a>
            </div>
            <ToastContainer/>
          </form>
        </div>      
    </div>
  );
};

export default AdminSignupPage;