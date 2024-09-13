import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import ProfileImage from "../../Components/ProfileImage";
import "./welcome.css"
import div2img from "../../Components/Images/welcome_changes.avif"
import div2img2 from "../../Components/Images/welcome_main.jpg"
import Cards from "../../Components/UIElements/Card";
import { useUser } from "../../UserContext";
import Footer from "../../Components/Footer/Footer";

const Welcome = () => {
  const myStyles = {
    width: "10rem",
    color: "#3795BD"
  }
  const {username}=useUser();
  const {logout}=useUser();
  const navigate=useNavigate()
  const logouts=()=>{
    logout();
    navigate("/login")

 }
  return (<div className="welcome_main">
    <div className="welcome_navbar">
      <h1>Health-Care Appointment</h1>
      <div>
        {!username?<Button href="/login" className="welcome_navbtn">Login</Button>
        :<div onClick={()=>navigate('/dashboard')} className="mt-3 cursor-pointer"> <ProfileImage  name={username}/><p className="-mt-2">{username}</p></div> 
        }
    
        <Button href="/about" className="welcome_navbtn"> Know Us</Button>
      </div>
    </div>
    <div className="welcome_one">
      <h1>Get Your Appointment, with just few steps</h1>
      <p>With the help of our service, you can book your appointments within no time and effortlessly!</p>
      <div className="welcome_signup_btn">
      {username ? <h3 className="text-black font-bold shadow-md">WELCOME {username}</h3> :<Button href="/signup" className="welcome_signupBtn">Signup for free</Button>}  
          </div>
    </div>
    <div className="welcome_three">
      <div className="welcome_three_left">
        <Cards/>
      </div>
      <div className="welcome_three_right"><h2>We provide a seamless platform for booking medical appointments and managing diagnostic services. Customers can easily <span className="welcome_span_1"> schedule and track appointments </span>at their chosen diagnostic centers, while diagnostic centers can <span className="welcome_span_2">register to manage appointments, conduct tests, and handle reports.</span> </h2></div>
    </div>
    <div className="welcome_two">
      <img src={div2img2} alt="" className="welcome_first_img"  />
      <img src={div2img} alt="" className="welcome_second_img" />
    </div>
    <div className="welcome_five">
      <Footer/>
    </div>
    

  </div>)
}
export default Welcome;