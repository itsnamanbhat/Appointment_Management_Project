import React from "react";
import booking from "../../images/hq720.jpg"
import "./home.css"
import { NavLink, useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import Footer from "../../Components/Footer/Footer";
const Home = () => {
    const navigate=useNavigate()
    return (<div
        className="home_main bg-[#EFF6FE] overflow-hidden">
        <div className="flex p-16 mt-16 px-48 gap-48 items-center">
            <div className="w-[50%]">
                <h1 className="font-bold text-[#321D72]">Appointment</h1>
                <h1 className="font-bold text-[#30CECF]">Booking</h1>
                <p className="my-4"> <strong className="text-blue-500">AAROG PLUS </strong>
                 aims to provide patients and healthcare providers with an efficient, user-friendly experience,
                ensuring that appointments are booked, managed, and tracked with ease.
                </p>
                <Button onClick={()=>navigate('/appointmentBooking')} className="shadow-md">Book Now</Button>
            </div>
            <div>
                <img src={booking} className="w-[100%]" alt="" />
            </div>
        </div>
        <div className="mt-14">
        <Footer/>
        </div>
    </div>)

}

export default Home;