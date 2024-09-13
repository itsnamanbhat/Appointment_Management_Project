import React, { useState, useEffect } from 'react';
import { Modal, Button, Alert } from 'react-bootstrap';
import Axios from '../../../configurations/Axios';
import { useUser } from '../../../UserContext';
import { toast } from 'react-toastify';

const Setting = () => {
  const { username, email, role, id } = useUser();
  const [userDetails, setUserDetails] = useState(null);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showResetPasswordModal, setShowResetPasswordModal] = useState(false);
  const [editedDetails, setEditedDetails] = useState(null);
  const [showAlert, setShowAlert] = useState({ show: false, message: '', variant: '' });
  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmNewPassword, setConfirmNewPassword] = useState('');
  const [validationErrors, setValidationErrors] = useState({
    currentPassword: '',
    newPassword: '',
    confirmNewPassword: ''
  });

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const response = await Axios.get(`/user/${id}`);
        setUserDetails(response.data.data);
        setEditedDetails(response.data.data);
      } catch (error) {
        console.error('Error fetching user details:', error);
        setShowAlert({ show: true, message: 'Error fetching user details.', variant: 'danger' });
      }
    };

    fetchUserDetails();
  }, [id]);

  const handleEditClick = () => {
    setShowEditModal(true);
  };

  const handleCloseEditModal = () => {
    setShowEditModal(false);
  };

  const handleUserChange = (e) => {
    const { name, value } = e.target;
    setEditedDetails((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleResetPasswordClick = () => {
    setShowEditModal(false);
    setShowResetPasswordModal(true);
  };

  const handleCloseResetPasswordModal = () => {
    setShowResetPasswordModal(false);
  };

  const validatePassword = () => {
    const errors = {
      currentPassword: '',
      newPassword: '',
      confirmNewPassword: ''
    };

    const passwordRegex = /^(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;

    if (!passwordRegex.test(newPassword)) {
      errors.newPassword = 'New password must be at least 8 characters long, include at least one capital letter and one number.';
    }

    if (newPassword !== confirmNewPassword) {
      errors.confirmNewPassword = 'New passwords do not match.';
    }

    if (currentPassword === newPassword) {
      errors.currentPassword = 'New password cannot be the same as the current password.';
    }

    setValidationErrors(errors);
    return Object.values(errors).every((error) => !error);
  };

  const handlePasswordReset = async () => {
    if (!validatePassword()) {
      return;
    }

    try {
      await Axios.put(`/user/${email}/${currentPassword}/${newPassword}`);
      console.log(email, currentPassword, newPassword);
      toast.success('Password updated successfully!');
      setConfirmNewPassword('');
      setCurrentPassword('')
      setNewPassword('')
    } catch (error) {
      console.error('Error resetting password:', error);
      toast.error('Error resetting password.');
    } finally {
      handleCloseResetPasswordModal();
    }
  };

  if (!userDetails) {
    return <p>Loading...</p>;
  }

  return (
    <div className="container mx-auto p-4">
      <h2 className="text-2xl font-semibold mb-4 text-center">{role} Settings</h2>
      <div className="bg-white p-6 rounded-lg shadow-lg max-w-lg mx-auto">
        <div className="mb-4">
          <h3 className="text-lg font-medium">Username</h3>
          <p className="text-gray-600">{userDetails.username}</p>
        </div>
        <div className="mb-4">
          <h3 className="text-lg font-medium">Email</h3>
          <p className="text-gray-600">{userDetails.email}</p>
        </div>
        <div className="mb-4">
          <h3 className="text-lg font-medium">Password</h3>
          <p className="text-gray-600">********</p>
        </div>
         <Button
            variant="success"
            onClick={handleResetPasswordClick}
            className="text-white"
          >
            Reset Password
          </Button>
      </div>

      {showAlert.show && (
        <Alert variant={showAlert.variant} className="mt-3">
          {showAlert.message}
        </Alert>
      )}

      <Modal show={showEditModal} onHide={handleCloseEditModal}>
        <Modal.Header closeButton>
          <Modal.Title>Edit User Details</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-gray-700">Username</label>
              <input
                type="text"
                name="username"
                value={editedDetails.username}
                onChange={handleUserChange}
                readOnly
                className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm bg-gray-100"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700">Email</label>
              <input
                type="email"
                name="email"
                value={editedDetails.email}
                onChange={handleUserChange}
                readOnly
                className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm bg-gray-100"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700">Password</label>
              <input
                type="password"
                name="password"
                value={editedDetails.password}
                onChange={handleUserChange}
                className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
              />
            </div>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseEditModal}>
            Close
          </Button>
          {/* <Button
            variant="success"
            onClick={handleResetPasswordClick}
            className="text-white"
          >
            Reset Password
          </Button> */}
        </Modal.Footer>
      </Modal>

      <Modal show={showResetPasswordModal} onHide={handleCloseResetPasswordModal}>
        <Modal.Header closeButton>
          <Modal.Title>Reset Password</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-gray-700">Current Password</label>
              <input
                type="password"
                value={currentPassword}
                onChange={(e) => setCurrentPassword(e.target.value)}
                className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700">New Password</label>
              <input
              pattern='/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/'
                type="password"
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
                className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
              />
              {validationErrors.newPassword && (
                <p className="text-red-600 text-sm mt-1">{validationErrors.newPassword}</p>
              )}
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700">Confirm New Password</label>
              <input
                type="password"
                value={confirmNewPassword}
                 pattern='/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/'
                onChange={(e) => setConfirmNewPassword(e.target.value)}
                className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
              />
              {validationErrors.confirmNewPassword && (
                <p className="text-red-600 text-sm mt-1">{validationErrors.confirmNewPassword}</p>
              )}
            </div>
            {validationErrors.currentPassword && (
              <p className="text-red-600 text-sm mt-1">{validationErrors.currentPassword}</p>
            )}
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseResetPasswordModal}>
            Close
          </Button>
          <Button variant="primary" onClick={handlePasswordReset}>
            Reset Password
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default Setting;
