import './App.css';
import Login from './Main/Login Page/Login';
import 'bootstrap/dist/css/bootstrap.min.css';
import SignUp from './Main/SignUp Page/Signup';
import PrivateRoute from './PrivateRoute';
import 'react-toastify/dist/ReactToastify.css';
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
  RouterProvider,
} from "react-router-dom";
import Welcome from './Main/Welcome Page/welcome';
import LayoutWithNavigation from './LayoutWithNavigation';
import LayoutWithoutNav from './LayoutWithoutNav';
import Home from './Main/Home Page/Home';
import ApointmentForm from './Main/Appointment/ApointmentForm';
import InvalidUrlPage from './Components/InvalidUrlPage';
import ContactUs from './Main/ContactUs/ContactUs';
import AboutUs from './Main/AboutUS/AboutUs';
import Dashboard from './Main/DashBoard/Dashboard';
function App() {
  return (<Router>
     <Routes>
       <Route element={<LayoutWithNavigation />}>
       {/* <Route path="/home" element={<PrivateRoute element={ <Home/>}/>}/>  */}
       <Route path='/home' element={<Home/>}/>
       <Route path="/contact" element={<ContactUs/>}/>
       <Route path="/about" element={<AboutUs/>}/>
       <Route path="/appointmentBooking" element={<PrivateRoute element={<ApointmentForm />} />}/> 
      </Route>

        <Route path='' element={<LayoutWithoutNav/>}>
        <Route path="/" element={<Welcome/>} /> 
       <Route path="/login" element={<Login />}/>
       <Route path="/signUp" element={<SignUp/>}/>
      
       <Route path="/dashboard" element={ <PrivateRoute element={<Dashboard/> }/>}/>
       </Route>

       <Route path="*" element={<InvalidUrlPage/>} />
     </Routes>
    
   </Router>);
}

export default App;
