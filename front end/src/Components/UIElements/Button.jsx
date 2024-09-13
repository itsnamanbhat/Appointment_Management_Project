import React from "react";
import "./button.css"

const Button=({size,color,value,type,myStyles})=>{
    return <button type={type} className="my_btn" style={{...myStyles}}>{value}</button>
}

export default Button;