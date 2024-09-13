import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Axios from '../../configurations/Axios';
import logo from "../../images/MainLogo.png";
import tablet from "../../images/tablet.png";
import plus from "../../images/plus.png";
import symbol from "../../images/symbol.png";
import sethescope from "../../images/sethescope.png";
import emailjs from '@emailjs/browser';
import CustomAlert from '../../Components/UIElements/CustomAlertMsg'; // Import CustomAlert
import "./signup.css";
import { toast } from 'react-toastify';

// Validation functions
const validateUsername = (username) => /^[a-zA-Z0-9_]{3,15}$/.test(username);
const validateEmail = (email) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
const validatePassword = (password) => /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(password); // At least 8 characters, one letter and one number

const Signup = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [role, setRole] = useState('');
  const [error, setError] = useState('');
  const [formErrors, setFormErrors] = useState({});
  const [otp, setOtp] = useState('');
  const [generatedOtp, setGeneratedOtp] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [alertMessage, setAlertMessage] = useState('');
  const [alertType, setAlertType] = useState(''); 

  const generateOTP = () => Math.floor(1000 + Math.random() * 9000);

  const handleVerification = async (e) => {
    e.preventDefault();
    let errors = {};
    setFormErrors({});
    setOtp("");
    if (!validateUsername(username)) errors.username = 'Invalid username. Must be 3-15 alphanumeric characters or underscores.';
    if (!validateEmail(email)) errors.email = 'Invalid email address.';
    if (!validatePassword(password)) errors.password = 'Password must be at least 8 characters long, including at least one letter and one number.';
    if (password !== confirmPassword) errors.confirmPassword = 'Passwords do not match.';

    if (Object.keys(errors).length > 0) {
      setFormErrors(errors);
      return;
    }
    const otp = generateOTP();
    setGeneratedOtp(otp);

    const serviceId = "service_o53pvus";
    const templateId = "template_oq1towg";
    const publicKey = "qKvuzAl9rgZ3CirqF";
    const templateParams = {
      reply_to: email,
      otp: otp
    };

    emailjs.send(serviceId, templateId, templateParams, publicKey)
      .then(() => {
        setIsModalOpen(true);
      })
      .catch((error) => {
        setAlertMessage('Failed to send OTP. Please try again.');
        setAlertType('error');
        console.log(error);
      });
  };

  const handleOtpSubmit = (e) => {
    e.preventDefault();
    if (otp === generatedOtp.toString()) {
       handleSubmit();
       setOtp("")
    } else {
      setAlertMessage('OTP does not match.');
      setAlertType('error');
      setOtp("")
    }
  };

  const handleSubmit = async (e) => {
   if(e) e.preventDefault();


    try {
      if (role === 'CENTER_ADMIN') {
        await Axios.post('/request', { username, password, email, approved: "false" });
        setAlertMessage('Request has been sent to Admin, Please wait.');
        navigate("/");
        toast.success("Request has been sent to Admin, Please wait and login after sometime")
        setAlertType('success');
      } else {
        await Axios.post('/auth/signup', { username, password, email, role });
        setAlertMessage('Successfully Registered. Redirecting to login...');
        setAlertType('success');
        setTimeout(() => navigate('/login'), 1000); // Redirect after 1 second
      }
    } catch (err) {
      setError(err.response?.data || 'An error occurred. Please try again.');
      setAlertMessage('An error occurred during registration.');
      setAlertType('error');
      console.log(err);
    }
  };

  const handleCloseAlert = () => {
    setAlertMessage('');
    setAlertType('');
  };

  return (
    <div className='flex items-center h-screen auth relative overflow-hidden'>
      <button onClick={() => navigate('/')} className='rounded-2xl px-4 py-2 bg-[#343434] text-white text-md m-2 absolute top-0'>
        <i className="fa-regular fa-hand-point-left mr-2"></i>Back to home
      </button>
      <div className="w-full max-w-md p-6 text-white ml-[15%] signup_form">
        <img src={logo} className='w-20 ml-[38%] mb-4' alt="Logo" />
        <h3 className="text-center mb-4 font-bold">Create Account</h3>
        <p className='mt-2 text-md text-center mb-4'>Already a user? <a href="/login" className='text-blue-800 font-semibold text-decoration-none'>Login</a></p>
        {error && <p className="text-red-500 text-center mb-4">{error}</p>}
        <form id="signupForm" onSubmit={handleVerification}>
          <div className="mb-3">
            <input
              type="text"
              className={`form-control ${formErrors.username ? 'border-red-500' : ''} text-white`}
              id="username"
              name="username"
              pattern="[a-zA-Z0-9_]{3,15}"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            {formErrors.username && <p className="text-red-500 text-sm">{formErrors.username}</p>}
          </div>
          <div className="mb-3">
            <input
              type="email"
              className={`form-control ${formErrors.email ? 'border-red-500' : ''} text-white`}
              id="email"
              name="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {formErrors.email && <p className="text-red-500 text-sm">{formErrors.email}</p>}
          </div>
          <div className="mb-3">
            <input
              type="password"
              className={`form-control ${formErrors.password ? 'border-red-500' : ''} text-white`}
              id="password"
              name="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {formErrors.password && <p className="text-red-500 text-sm">{formErrors.password}</p>}
          </div>
          <div className="mb-3">
            <input
              type="password"
              className={`form-control ${formErrors.confirmPassword ? 'border-red-500' : ''} text-white`}
              id="confirmPassword"
              name="confirmPassword"
              placeholder="Confirm your password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
            />
            {formErrors.confirmPassword && <p className="text-red-500 text-sm">{formErrors.confirmPassword}</p>}
          </div>
          <div className="mb-3">
            <select
              className={`form-control ${formErrors.role ? 'border-red-500' : ''} text-white`}
              id="role"
              name="role"
              value={role}
              onChange={(e) => setRole(e.target.value)}
            >
              <option value="" disabled>Select your role</option>
              <option value="USER">User</option>
              <option value="CENTER_ADMIN">Center Admin</option>
            </select>
            {formErrors.role && <p className="text-red-500 text-sm">{formErrors.role}</p>}
          </div>
          <button type="submit" className="bg-orange-500 w-full">Verify  &  Signup</button>
        </form>
      </div>

      {isModalOpen && (
        <div className="w-[200px] z-[1000] modals-overlay">
          <div className="modals-content">
            <h4>Enter OTP</h4>
            <form onSubmit={handleOtpSubmit}>
              <input
                type="text"
                value={otp}
                onChange={(e) => setOtp(e.target.value)}
                placeholder="Enter 4-digit OTP"
                maxLength="4"
                required
              />
              <button type="submit">Submit</button>
              <button type="button" onClick={() => setIsModalOpen(false)}>Close</button>
            </form>
          </div>
        </div>
      )}

      {alertMessage && (
        <CustomAlert message={alertMessage} onClose={handleCloseAlert} type={alertType} />
      )}

      {/* Background images */}
      <div className='auth-divs absolute w-[350px] h-[400px] -top-24 right-[22%] rotate-6'>
        <div className='bg-[#FFCC00]'>
          <img src={sethescope} className='w-[300px] h-[250px] position-relative left-2 top-24 opacity-5' alt="" />
        </div>
      </div>
      <div className='auth-divs absolute w-[300px] h-[500px] -top-12 -right-[6%] rotate-3'>
        <div className='bg-[#FF3366]'>
          <img src={symbol} className='opacity-5 w-[250px] h-[440px] rounded-[40px] position-relative left-[px] top-8' alt="" />
        </div>
      </div>
      <div className='auth-divs absolute w-[350px] h-[400px] -bottom-[20%] right-[20%] -rotate-6'>
        <div className='bg-[#22CB88]'>
          <img src={plus} className='opacity-10 rounded-[50px]' alt="" />
        </div>
      </div>
      <div className='auth-divs absolute w-[350px] h-[400px] -bottom-72 -right-36'>
        <div className='bg-[#009AFE]'>
          <img src={tablet} className='opacity-10 w-[170px] h-[100px] rounded-3xl position-relative left-1 top-1' alt="" />
        </div>
      </div>
    </div>
  );
};

export default Signup;
