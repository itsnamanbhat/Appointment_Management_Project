import React, { useState } from 'react'
import Sidebar from './Sidebar';
import UserDashboard from './UserDashboard';
import AdminDashboard from './AdminDashboard';
import Appointments from './DashboardComponents/Appointments';
import { useUser } from '../../UserContext';
import DiagnosticCenter from './DashboardComponents/DiagnosticCenter';
import CenterAdministrator from './DashboardComponents/CenterAdministrator.jsx';
import DiagnosticTest from './DashboardComponents/DiagnosticTest';
import TestResult from './DashboardComponents/TestResult';
import Patient from './DashboardComponents/Patient';
import Setting from './DashboardComponents/Setting';
import CenterAdminstatorDashboard from './CenterAdminstatorDashboard';
import ProfileImage from '../../Components/ProfileImage.jsx';
import { useNavigate } from 'react-router-dom';
const Dashboard = () => {
    const { username,role,logout } = useUser();
    const [selectedView, setSelectedView] = useState('dashboard'); // Default view
    const navigate=useNavigate()
    // Content rendering based on selected view
    const renderContent = () => {
      switch (selectedView) {
        case 'appointments':
          return <Appointments />;
        case 'diagnosticCenters':
          return <DiagnosticCenter />;
          case 'My Center':
          return <DiagnosticCenter />;
        case 'centerAdministator':
          return <CenterAdministrator />;
        case 'diagnosticTests':
          return <DiagnosticTest />;
        case 'testResults':
          return <TestResult />;
        case 'patients':
          return <Patient />;
        case 'Users':
          return <Patient/>
        case 'Reset Password':
          return <Setting />;
        default:
          return role === 'ADMIN' ? <AdminDashboard /> :role === 'CENTER_ADMIN' ?<CenterAdminstatorDashboard/> :<UserDashboard />;
      }
    };
    
  const logouts=()=>{
    logout();
    navigate("/login")

 }
  
    return (
     
      <div className={`container-fluid h-full ${selectedView==='dashboard'?'split-background':''} `}>
        
        <div className="row vh-100">
          <Sidebar setSelectedView={setSelectedView} />
          <main className="col-md-9 ms-sm-auto col-lg-10 px-4">
            <div className='flex justify-between  py-3 pb-4 -mb-[23px] '>
                <div className='flex gap-4'>
                <p>Dashboard</p>
              
                </div>
                <div className='flex gap-4 justify-center items-center'>
                <ProfileImage  name={username}/>
                 <p className={` ${selectedView==='dashboard'?'text-white':'text-[#202531]'} -ml-4`}>{username}</p>
                 <p onClick={logouts} className='text-red-400 font-bold cursor-pointer'>Logout</p>
                </div>
               
            </div>
            <hr className='-mt-[2px]'/>
            <div className='flex gap-2 -mt-2 shadow-md border-b '>
                <a href="/home" className='text-decoration-none' style={{margin:"0px 1rem"}}>HOME</a>
                <p>/</p>
                <p>{selectedView.charAt(0).toLocaleUpperCase() + selectedView.slice(1)}</p>
            </div>
            {/* <hr className='-mt-3' /> */}
            {renderContent()}
          </main>
        </div>
      </div>
    );
  };
export default Dashboard;
