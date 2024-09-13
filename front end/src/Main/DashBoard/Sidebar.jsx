
import React, { useState } from 'react';
import { useUser } from '../../UserContext';
import "./sidebar.css";
import { useNavigate } from 'react-router-dom';
import { Button } from "react-bootstrap";
import ProfileImage from '../../Components/ProfileImage';
import logo from '../../images/MainLogo.png'
const Sidebar = ({ setSelectedView }) => {
  // const { user } = useUser();
  const [selectedView, setSelectedViewState] = useState(null);
  const {username,role,logout}=useUser()
  const navigate=useNavigate()
  const adminLinks = [
    'dashboard',
    'appointments',
    'diagnosticCenters',
    'diagnosticTests',
    'centerAdministator',
    'patients',
    'Reset Password'
  ];

  const userLinks = [
    'dashboard',
    'appointments',
    'patients',
    'Reset Password'
    

  ];

  const centerAdminLinks = [
    'dashboard',
    'appointments',
    'My Center',
    'diagnosticTests',
    'Reset Password'
  ];

  const linksToShow = role === 'ADMIN' ? adminLinks 
                     : role === 'CENTER_ADMIN' ? centerAdminLinks
                    : userLinks;

  const handleClick = (view) => {
    setSelectedView(view);
    setSelectedViewState(view);
  };

  return (
    <nav id="sidebar" className="col-md-3 col-lg-2 d-md-block bg-[#202531] text-white sidebar p-4">
      <div onClick={()=>navigate('/')} className='cursor-pointer flex gap-2 justify-center items-start -mt-4 pb-[3px] -ml-8 '>
      <img src={logo} className='w-10' alt="Logo" />
      <strong><p className='mt-2'>AAROG PLUS</p></strong>
      </div>
      <div className="position-sticky">
        <hr className='-mt-[0px]'/>
        <ul className="nav flex-column">
          {linksToShow.map(view => (
            <li className={`nav-item flex -ml-4 -mr-6 mb-3 ${view==='dashboard' ? '-mt-4':''}`} key={view}>
              <a
                className={`nav-link shadow-sm  ${selectedView === view ? 'active' : ''}`}
                href="#"
                onClick={() => handleClick(view)}
              >
                {view==='dashboard' ?<i class="fa-solid fa-gauge mr-4"></i>:
                 view==='appointments'?<i class="fa-regular fa-calendar-check mr-4"></i>:
                 view==='diagnosticCenters'?<i class="fa-regular fa-hospital mr-2"></i> :
                 view==='My Center'?<i class="fa-regular fa-hospital mr-2"></i> :
                 view==='diagnosticTests'? <i class="fa-solid fa-flask-vial mr-4"></i>:
                 view==='centerAdministator'? <i class="fa-solid fa-user-tie mr-2"></i>:
                 view==='testResults'? <i class="fa-solid fa-square-poll-vertical mr-4"></i>:
                 view==='patients'?<i class="fa-solid fa-bed mr-4"></i>
                 :view==='Users'?<i class="fa-solid fa-bed mr-4"></i>:<i class="fa-solid fa-gear mr-4"></i>
              
                }
                {view.charAt(0).toUpperCase() + view.slice(1)}
              </a>
            </li>
          ))}
        </ul>
      </div>
    </nav>
  );
};

export default Sidebar;

// <div className="user-details p-3 border-bottom">
//           {/* <h5>User Details</h5> */}
//           <div className="sidebar_userdetails">
//           <ProfileImage name={username}/>
//            <p>Username: {username}</p>
          
//           </div>
//           <div>
//              <Button onClick={logouts} className="welcome_navbtn">Logout</Button>
//            </div>
//         </div>