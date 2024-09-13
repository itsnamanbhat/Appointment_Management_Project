import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import logo from "../../images/MainLogo.png";
import tablet from "../../images/tablet.png";
import plus from "../../images/plus.png";
import symbol from "../../images/symbol.png";
import sethescope from "../../images/sethescope.png";
import { useUser } from '../../UserContext';
import CustomAlert from '../../Components/UIElements/CustomAlertMsg';
import emailjs from '@emailjs/browser'; 
import "./login.css";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [alertMessage, setAlertMessage] = useState('');
  const [alertType, setAlertType] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [isOtpMode, setIsOtpMode] = useState(false);
  const [otp, setOtp] = useState('');
  const [generatedOtp, setGeneratedOtp] = useState('');
  const [emailForReset, setEmailForReset] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [showPasswordResetModal, setShowPasswordResetModal] = useState(false);
  const { login } = useUser();

  const validatePassword = (password) => /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(password);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setAlertMessage('');
    setAlertType('');

    try {
      localStorage.removeItem('token');
      const response = await axios.post('http://localhost:8089/api/v1/auth/login', { email, password });
      console.log(response);
      if (response.data === "") {
        throw new Error("Email Id not exists");
      }
      const loggedUser = response.data.user;
      const role = loggedUser.role;
      login(loggedUser);
      localStorage.setItem('token', response.data.token);
      toast.success("successfully logged in")
      setTimeout(() => {
        role === 'USER' ? navigate('/home') : navigate('/dashboard');
      }, 1000);
    } catch (err) {
      console.log(err);
      setError(err.toString() !== "Error: Email Id not exists" ? "Password Mismatch" : (err.toString()));
    }
  };

  const handleForgotPassword = () => {
    setShowModal(true);
    setIsOtpMode(false);
  };

  const handleEmailSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(`http://localhost:8089/api/v1/auth/forgot-password/${emailForReset}`);
      console.log(email);
      sendEmail();
    } catch (err) {
      toast.error('Email not found, failed to send OTP')
    }
  };

  const sendEmail = () => {
    const otp = generateOTP();
    setGeneratedOtp(otp);
    const serviceId = "service_o53pvus";
    const templateId = "template_n3lf71y";
    const publicKey = "qKvuzAl9rgZ3CirqF";
    const templateParams = {
      reply_to: emailForReset,
      otp: otp
    };
    emailjs.send(serviceId, templateId, templateParams, publicKey)
      .then(() => {
        setIsOtpMode(true);
      })
      .catch((error) => {
       toast.error('Failed to send OTP. Please try again.')
      });
  };

  const generateOTP = () => Math.floor(1000 + Math.random() * 9000);

  const handleOtpSubmit = async (e) => {
    e.preventDefault();
    if (otp === generatedOtp.toString()) {
      setShowPasswordResetModal(true);
      setShowModal(false);
      setIsOtpMode(false);
    } else {
      toast.error('OTP does not match.')
    }
  };

  const handlePasswordReset = async (e) => {
    e.preventDefault();
    
    // Check if passwords match
    if (newPassword !== confirmPassword) {
      toast.error("Passwords do not match.")
      return;
    }

    // Validate the new password
    if (!validatePassword(newPassword)) {
      toast.info('Password must be at least 8 characters long, and include at least one letter and one number.')
      return;
    }

    try {
      console.log(emailForReset, newPassword);
      await axios.post(`http://localhost:8089/api/v1/forgot/${emailForReset}/${newPassword}`);
      setAlertMessage('Password successfully updated.');
      setAlertType('success');
      handleCloseModal();
    } catch (err) {
      toast.error('Failed to reset password.')
    }
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setShowPasswordResetModal(false);
    setIsOtpMode(false);
    setEmailForReset('');
    setOtp('');
    setNewPassword('');
    setConfirmPassword('');
  };

  return (
    <div className='flex items-center h-screen auth relative overflow-hidden'>
      <button onClick={() => { navigate('/') }} className='rounded-2xl px-4 py-2 bg-[#343434] text-white text-md m-4 absolute top-0 right-0'>
        <i className="fa-regular fa-hand-point-left mr-2"></i>Back to home
      </button>

      <div className="w-full max-w-md p-6 text-white ml-[55%] login_form">
        <img src={logo} className='w-20 ml-[40%] mb-4' alt="" />
        <h2 className="text-center mb-4 font-bold">Login</h2>
        <p className='mt-2 text-md text-center mb-4'>Don't have an account? <a href="/signup" className='text-blue-800 font-semibold text-lg text-decoration-none'>Sign Up</a></p>
        {error && <p className="text-red-500 text-center mb-4">{error}</p>}
        <form id="signupForm" onSubmit={handleSubmit}>
          <div className="mb-3">
            <input
              type="text"
              className={`form-control text-white`}
              id="email"
              name="email"
              placeholder="Email"
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <input
              type="password"
              className={`form-control text-white`}
              id="password"
              name="password"
              placeholder="Password"
              value={password}
              required
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button type="submit" className="w-full">Login</button>
        </form>
        <button className='login_forgotpass' onClick={handleForgotPassword}>Forgot password</button>
      </div>

      {/* Custom Alerts */}
      {alertMessage && (
        <CustomAlert message={alertMessage} onClose={() => setAlertMessage('')} type={alertType} />
      )}

      {/* Forgot Password Modal */}
      {showModal && (
        <div className="z-[1000] login_modal-overlay">
          <div className="login_modal-content">
            {!isOtpMode ? (
              <>
                <h4>Forgot Password</h4>
                <form onSubmit={handleEmailSubmit}>
                  <input
                    type="email"
                    value={emailForReset}
                    onChange={(e) => setEmailForReset(e.target.value)}
                    placeholder="Enter your email"
                    required
                  />
                  <button type="submit">Send OTP</button>
                  <button type="button" onClick={handleCloseModal}>Close</button>
                </form>
              </>
            ) : (
              <>
                <h4>Verify OTP</h4>
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
                  <button type="button" onClick={handleCloseModal}>Close</button>
                </form>
              </>
            )}
          </div>
        </div>
      )}

      {/* Password Reset Modal */}
      {showPasswordResetModal && (
        <div className="z-[1000] login_modal-overlay">
          <div className="login_modal-content">
            <h4>Reset Password</h4>
            <form onSubmit={handlePasswordReset}>
              <input
                type="password"
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
                placeholder="New Password"
                required
              />
              <input
                type="password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                placeholder="Confirm Password"
                required
              />
              <button type="submit">Submit</button>
              <button type="button" onClick={handleCloseModal}>Close</button>
            </form>
            <p className='text-gray-400 text-sm mt-2'>
              Password must be at least 8 characters long, including at least one letter and one number.
            </p>
          </div>
        </div>
      )}

      {/* Background images */}
      <div className='auth-divs absolute w-[350px] h-[400px] -top-24 left-[20%] -rotate-6'>
        <div className='bg-[#FFCC00]'>
          <img src={sethescope} className='w-[400px] h-[250px] position-relative top-24 opacity-5 ' alt="" />
        </div>
      </div>
      <div className='auth-divs absolute w-[260px] h-[500px] -top-12 -left-[6%] -rotate-3'>
        <div className='bg-[#FF3366]'>
          <img src={symbol} className='opacity-5 w-[250px] h-[440px] rounded-[40px] position-relative left-[px] top-8' alt="" />
        </div>
      </div>
      <div className='auth-divs absolute w-[350px] h-[400px] -bottom-[20%] left-[20%] rotate-6'>
        <div className='bg-[#22CB88]'>
          <img src={plus} className='opacity-5 rounded-[50px]' alt="" />
        </div>
      </div>
      <div className='auth-divs absolute w-[350px] h-[400px] -bottom-72 -left-36'>
        <div className='bg-[#009AFE]'>
          <img src={tablet} className='opacity-5 w-[170px] h-[100px] rounded-3xl position-relative -right-36 top-1' alt="" />
        </div>
      </div>
    </div>
  );
};

export default Login;
