import React, { createContext, useContext, useEffect, useState } from 'react';

const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [username, setUsername] = useState(''); 
  const [email, setEmail] = useState(''); 
  const [role, setRole] = useState(''); 
  const [id,setId]=useState('');
  const [center_id,setCenterId]=useState('')
  const [isAuthenticated, setIsAuthenticated] = useState(false);


  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      setUsername(JSON.parse(storedUser));
      setEmail(JSON.parse(localStorage.getItem('email')))
      setRole(JSON.parse(localStorage.getItem('role')))
      setId(JSON.parse(localStorage.getItem('id')))
      setIsAuthenticated(true)
    }
  }, []);

  const login = (user) => {
    console.log(user)
    console.log(user.role)
    localStorage.setItem('user', JSON.stringify(user.username));
    localStorage.setItem('email', JSON.stringify(user.email));
    localStorage.setItem('role', JSON.stringify(user.role));
    localStorage.setItem('id', JSON.stringify(user.id));
    
   
    setUsername(user.username);
    setEmail(user.email)
    setRole(user.role)
    setId(user.id)
    setIsAuthenticated(true)
  };

  const logout = () => {
    localStorage.removeItem('user');
    localStorage.removeItem('role');
    localStorage.removeItem('email');
    localStorage.removeItem('id')
    setUsername(null);
    setEmail(null)
    setRole(null)
    setId(null)
    setIsAuthenticated(false)
  };

  const centerAdd=(center_id)=>{
  setCenterId(center_id);
  }
  
  return (
    <UserContext.Provider value={{isAuthenticated, username,email,role,id,center_id,centerAdd, login, logout }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUser = () => useContext(UserContext);

