
import React, { useEffect, useState } from 'react'
import Axios from '../../configurations/Axios';
import { Card, Row, Col } from 'react-bootstrap';
import { useUser } from '../../UserContext';
const  CenterAdminstatorDashboard = () => {
  const {center_id,id:userId,centerAdd}=useUser();
  const [appointments, setAppointments] = useState(0);
  const [diagnosticCenters, setDiagnosticCenters] = useState(0)
  const [diagnosticTests, setDiagnosticTests] = useState(0)
  const [patients, setPatients] = useState(0)
  

  useEffect(() => {
    const fetchAllDetails = async () => {
      try {
        const response = await Axios.get(`/diagnosticcenter/user/${userId}`);
        console.log(userId)
        const centersData = response.data.data;
        console.log(centersData)
       if(centersData && typeof centersData!=='string'){
        centerAdd(centersData.id);setDiagnosticCenters(1)
       }
        setAppointments(centersData.appointments.length)
        setDiagnosticTests(centersData.diagnosticTests.length)
        const appointments=centersData.appointments;
        const patients=appointments.map(appointment=>appointment.patient)
        const uniquePatientNumber=patients.map(patient=>patient.phoneNo)
        const uniquePhoneNumbers = Array.from(new Set(uniquePatientNumber));
        setPatients(uniquePhoneNumbers.length)
        
        
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
    { id: 2, title: 'Diagnostic Centers', description: `${diagnosticCenters}`, bgColor:  gradients[3], symbol: <i class="fa-regular fa-hospital mr-4"></i> },
    { id: 3, title: 'Diagnostic Tests', description: `${diagnosticTests}`, bgColor:  gradients[0], symbol: <i class="fa-solid fa-flask-vial mr-4"></i> },
    { id: 4, title: 'Patients', description: `${patients}`, bgColor:  gradients[1], symbol: <i class="fa-solid fa-bed"></i> },
  ];
  return (
    <div className="content-area mt-4 ">
      <h6>Welcome to the Admin dashboard!</h6>
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

export default CenterAdminstatorDashboard;
