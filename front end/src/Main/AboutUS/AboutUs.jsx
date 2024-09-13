
import React from 'react';
import "./aboutus.css"
 
const AboutUs = () => {
  return (
    <div className="about-us-container">
      <section className="company-section">
        <h1 className="company-title">About Our Company</h1>
        <p className="company-description">
          Welcome to Aarog! We are a  company which is dedicated to providing top-notch services and solutions to our clients.We work relentlessly to ensure the highest standards of quality and innovation in all our projects.
        </p>
        <p className="company-description">
          Established in 2024, our mission is to deliver exceptional value and drive success for our customers through cutting-edge technology and unparalleled expertise. We pride ourselves on our commitment to excellence and customer satisfaction.
        </p>
      </section>
      <section className="developers-section">
        <h2 className="developers-title">Meet Our Developers</h2>
        <div className="developer">
          <h3 className="developer-name">Naman Bhat</h3>
          <p className="developer-bio">
            A software developer with a passion for creating innovative solutions. With knowledge in Java,React, and Javascript , have contributed significantly to our projects.
          </p>
        </div>
        <div className="developer">
          <h3 className="developer-name">Harsha Poojari</h3>
          <p className="developer-bio">
           He brings a wealth of knowledge in TailWind and ReactJS along with other libraries and a strong background in Java. Dedicated to delivering high-quality code and enhancing the user experience.
          </p>
        </div>
      </section>
    </div>
  );
};
 
export default AboutUs;