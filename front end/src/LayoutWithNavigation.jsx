// LayoutWithNav.jsx
import React from 'react';
import { Outlet } from 'react-router-dom';
import MainNavigation from './Components/navigation/MainNavigation';


const LayoutWithNavigation = () => {
  return (
    <>
      <MainNavigation />
      <main>
        <Outlet />
      </main>
    </>
  );
};

export default LayoutWithNavigation;
