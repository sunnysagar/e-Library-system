import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import axios from 'axios';
import '../style.css';
import 'react-toastify/dist/ReactToastify.css';

import { useNavigate } from 'react-router-dom';
// import LoginPage from './LoginPage';


const AdminLoginPage = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const [error, setError] = useState('');

  const handleSuccess = () => {
    toast.success('You have logged in successfully!');
  }
  
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8009/admin/login', formData);
      handleSuccess();
      navigate('/admin/home');
    } catch (error) {
      console.error('Error signing up:', error);
      setError('Invalid username or password');
      // Handle error (e.g., show error message)
    }
  };

  return (
    <div className='form-container'>
      {/* <div className='select'>
        <a href='/admin/signup' id='admin'>Admin</a>
        <a href='/login' id='stu'>Student</a>
      </div> */}
      <div className='formm'>
        <div className='span'>
          <div className='left'>
            <h2>Sign in</h2>
          </div>
          <div className='right'>
            <h3> <a href='/admin/signup'>Create Account</a></h3>
          </div>
        
        </div>
        <form onSubmit={handleSubmit}>

          <div className='input-field'>
              <label>Email</label>
              <input type="email" name="email" placeholder="Your Email" required="true" onChange={handleChange} />
              
          </div>

          <div className='input-field'>
              <label>Password</label>
              <input type="password" name="password" placeholder="Enter password" required="true" onChange={handleChange} />   
          </div>

          <button type="submit">Login</button>
          {error && <span className='error'>{error}</span>} 
            {/* <div className='Log'>
                <button onClick={handleSuccess}>Show Success Toast</button>
                <button onClick={handleError}>Show Error Toast</button>
                <ToastContainer/>
            </div> */}
             <ToastContainer/>
          </form>
         
        </div>      
    </div>
  );
};

export default AdminLoginPage;