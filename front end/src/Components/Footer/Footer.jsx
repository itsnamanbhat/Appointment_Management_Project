import React from 'react'

import "./Footer.css"
import githubImg from "../Images/github-mark.png"
import twitterImg from "../Images/twitter.png"
import instagramImg from "../Images/instagram.png"
import mainLogo from "../../images/MainLogo.png"

const Footer = () => {
  return (
    <div className='footer_main'>
        <div className="footer_logo">
            <img src={mainLogo} alt="" />
        </div>
        <div className="footer_description">In case of any inconviniences and problems, Reach us out through contact-us page and feel free to share your queries. </div>
        <div className="footer_contact">
        <a href="/contact">Contact Us</a>
        <a href="/about">About Us</a>
        </div>
        <div className="footer_links">
        <div className="footer_link_container"> 
                <img className='footer_logos' src={twitterImg} alt="" />   
            <a href="">Twitter</a>
            </div>
            <div className="footer_link_container"> 
                <img className='footer_logos' src={githubImg} alt="" />   
            <a href="https://github.com/itsnamanbhat">Github</a>
            </div>

        </div>
    </div>
  )
}

export default Footer
