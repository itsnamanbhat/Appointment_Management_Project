// LayoutWithoutNav.jsx
import React from 'react';
import { Outlet } from 'react-router-dom';

const LayoutWithoutNav = () => {
  return (
    <main>
      <Outlet />
    </main>
  );
};

export default LayoutWithoutNav;
