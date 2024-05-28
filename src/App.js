import React from "react";


import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

import ChooseSignup from "./Choose";

import AdminSignupPage from "./AdminComponents/AdminSignup";
import AdminLoginPage from "./AdminComponents/AdminLogin";
import AdminHomePage from "./AdminComponents/AdminDashboard";

import StudentSignupPage from "./StudentComponents/StudentSignup";
import StudentLoginPage from "./StudentComponents/StudentLogin";
import StudentHomePage from "./StudentComponents/StudentDashboard";

import FecthBooks from "./check";


const App = () =>{
    return(
        <Router>
            <div>
                <Routes>
                    <Route path='/' element={<ChooseSignup/>}/>

                    {/* <Route path='/' element={<FecthBooks/>}/> */}

                    <Route path='/admin/signup' element={<AdminSignupPage/>}/>
                    <Route path="/admin/login" element={<AdminLoginPage/>}/>
                    <Route path="/admin/home" element={<AdminHomePage/>}/>

                    <Route path="/student/signup" element={<StudentSignupPage/>}/>
                    <Route path="/student/login" element={<StudentLoginPage/>}/>
                    <Route path="/studnet/home" element={<StudentHomePage/>}/>
                </Routes>
                
            </div>
        </Router>
    );
}

export default App;