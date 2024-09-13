import React from 'react'
import { Button } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'
const InvalidUrlPage = () => {
    const navigate=useNavigate()
  return (
    <div className='flex flex-col  justify-center items-center'>    
      <h1 className='text-blue-600'>AAROG.in</h1>
      <h5 className='font-bold text-blue-600'> Looking for something?</h5>
      <p>We're sorry. The Web address you entered is not a functioning page on our site.</p>
      <p>Go to      <Button onClick={()=>navigate('/')}>Home Page</Button></p>
    </div>
  )
}

export default InvalidUrlPage
