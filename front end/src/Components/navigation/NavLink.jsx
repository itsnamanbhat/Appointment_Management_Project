import React from "react";

import { NavLink, useNavigate } from "react-router-dom";
import "./NavLink.css"
import { useUser } from "../../UserContext";
import ProfileImage from "../ProfileImage";
const NavLinks =props=>{
    const {username}=useUser()
    const navigate=useNavigate()
return <ul className="nav-links">
<li>
    <NavLink to="/" exact>Welcome Page</NavLink>
</li>
<li>
    <NavLink to="/home" exact>Home</NavLink>
</li>
<li>
    <NavLink to="/about">About Us</NavLink>
</li>
<li>
    <NavLink to="/contact">Contact Us</NavLink>
</li>


<li>

<div onClick={()=>navigate('/dashboard')} className="flex gap-2 mt-3 cursor-pointer text-white"> <ProfileImage  name={username}/><p className="-mt-2">{username}</p></div> 

</li>

</ul>
}

export default NavLinks;