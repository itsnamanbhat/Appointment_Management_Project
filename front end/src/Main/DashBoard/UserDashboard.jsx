
import React, { useEffect, useState } from 'react'
import Axios from '../../configurations/Axios';
import { Card, Row, Col } from 'react-bootstrap';
import { useUser } from '../../UserContext';
const UserDashboard = () => {
  const [appointments, setAppointments] = useState(0);
  const [patients, setPatients] = useState(0)
  const [testresults, setTestResults] = useState(0)
  const {id:userId,username}=useUser()

  useEffect(() => {
    const fetchAllDetails = async () => {
      try {
        const Aresponse = await Axios.get(`/appointment/userId/${userId}`);
        const PResponse = await Axios.get(`/patient/username/${username}`)
        const appointments = Aresponse.data.data;
          const testResults = appointments.flatMap(appointment =>
            appointment.testResults.map(testResult => ({
              ...testResult,
              appointment: {
                id: appointment.id
              }
            }))
          );
        setTestResults(testResults && testResults.length);
  
        setAppointments(Aresponse.data.data &&Aresponse.data.data.length)
        setPatients(PResponse.data.data && PResponse.data.data.length)
        
  
      
      } catch (error) {
        console.log("Error in fetching details", error)
      }
    }

    fetchAllDetails()


  }, [])

  const gradients = [
    'linear-gradient(to right, #ECAD21, #F4B756, #F7E0A0)', 
    'linear-gradient(to right, #FF3366, #FF5B77, #FF8A99)',
    'linear-gradient(to right, #1DAB47, #4CC65B, #7FD68F)', 
    'linear-gradient(to right, #349AFF, #5D9BFF, #8AB9FF)', 
    'linear-gradient(to right, #5956D6, #7D7AE2, #9E9BEB)', 
    'linear-gradient(to right, #FC413F, #F7525B, #F97478)' 
  ];
  const cardData = [
    { id: 1, title: 'Appointments', description: `${appointments}`, bgColor: gradients[2], symbol: <i class="fa-regular fa-calendar-check mr-4"></i> },
    { id: 2, title: 'Patients', description: `${patients}`, bgColor:  gradients[1], symbol: <i class="fa-solid fa-bed"></i> },
    { id: 3, title: 'Test Results', description: `${testresults}`, bgColor:  gradients[4], symbol: <i class="fa-solid fa-square-poll-vertical mr-4"></i> },
  ];
  return (
    <div className="content-area mt-4 ">
      <h6>Welcome to the User dashboard!</h6>
      <Row xs={1} md={4} className="g-4">
        {cardData.map((card) => (
          <Col key={card.id}>
            <Card style={{ background:card.bgColor }} className='shadow-md border bottom-1 mt-2 text-white'>
              <Card.Body>
                <Card.Title >{card.title}</Card.Title>
                <Card.Text className='text-3xl position-relative left-48 -bottom-12'>{card.description}</Card.Text>
                <Card.Text className='text-3xl'>{card.symbol}</Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
}

export default UserDashboard
