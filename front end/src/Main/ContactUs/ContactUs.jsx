import React from 'react'

import "./contactus.css"

const ContactUs = () => {
  return (
    <div className="main_container">
    <p>Get in touch</p>
    <h3>Send A Message</h3>
    <div className="sub_container">
        <div className="message_container">

        <div className="line1">
        <div className="input-fields">
        <p>First Name</p>
        <input type="text" id="FName" placeholder="First Name"></input>
        </div>
        <div className="input-fields">
        <p>Last Name</p>
        <input type="text" id="LName" placeholder="Last Name"></input>
        </div>
        </div>

        <div className="line2">
        <div className="input-fields">
        <p>Email</p>
        <input type="email" id="mail" placeholder="Email"></input>
        </div>
        <div className="input-fields">
        <p>Phone</p>
        <input type="number" id="ph" placeholder="Phone"></input>
        </div>
        </div>


        <div className="input-fields">
        <p>Message</p>
        <textarea placeholder="Write your message here"></textarea>
        </div>
        <button className="submit_message">Send Message</button>

        </div>
        <div className="address_container">
            <h3>Address</h3>
            <p style={{textAlign:"center"}}><span>Location</span>Aarog Health-care appointment center,Bellandur Branch,Bengaluru</p>
            <p><span>Phone</span>:+91 9148551359 </p>
            <p><span>Email</span>:namanbhat002@gmail.com </p>
        </div>
    </div> 
    </div>
    
)}


export default ContactUs;
