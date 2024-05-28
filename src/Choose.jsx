import React, { useState } from 'react';
import './style.css';

// import LoginPage from './LoginPage';


const ChooseSignup = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    password: ''
  });

  const [error, setError] = useState('');


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setError("Please choose your role.")
    // try {
    // //   await axios.post('http://localhost:8009/admin/signup', formData);
    //   // Redirect or show success message
    //   handleSuccess();
    // //   navigate('/admin/home');
    // } catch (error) {
    //   console.error('Error signing up:', error);
    //   setError("Password must contain at least 8 characters, including at least one digit, one lowercase and uppercase," +
    //   "letter, one special character, and no whitespace.")
    // }
  };

  return (
    <div className='form-container'>
      <div className='select'>
        <a href='/admin/login' id='admin'>Admin</a>
        <a href='/student/login' id='stu'>Student</a>
      </div>
      <div className='formm'>
        <div className='span'>
          <div className='left'>
            <h2>Sign in</h2>
          </div>
          <div className='right'>
            <h3> <a href='/'>Create Account</a></h3>
          </div>
        
        </div>
        <form onSubmit={handleSubmit}>


          <div className='input-field'>
              <label>Email</label>
              <input type="email" name="email" placeholder="Your Email"   onChange={handleChange} />
              
          </div>


          <div className='input-field'>
              <label>Password</label>
              <input type="password" name="password" placeholder="Enter password"  onChange={handleChange} />    
          </div>

          <button type="submit">Login</button>
          {error && <span className='error'>{error}</span>} 
            <div className='Log'>
              <a href="/">Not have an account? </a>
            </div>
          </form>
        </div>      
    </div>
  );
};

export default ChooseSignup;