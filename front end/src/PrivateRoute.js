import React from 'react';
import { Navigate } from 'react-router-dom';
import { useUser } from './UserContext'; // Adjust the import based on your context file

const PrivateRoute = ({ element }) => {
  const { isAuthenticated } = useUser();

  return isAuthenticated ? element : <Navigate to="/" />;
};

export default PrivateRoute;
