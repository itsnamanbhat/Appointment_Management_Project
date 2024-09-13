import React from 'react';
import './customAlert.css'; 

const CustomAlert = ({ message, onClose, type }) => {
  return (
    <div className={`custom-alert ${type}`}>
      <span>{message}</span>
      <button onClick={onClose} className="close-btn">×</button>
    </div>
  );
};

export default CustomAlert;