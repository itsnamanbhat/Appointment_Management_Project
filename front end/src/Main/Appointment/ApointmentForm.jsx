import React, { useState, useEffect } from 'react';
import { Button, Form, Alert, Row, Col, Modal } from 'react-bootstrap';
import Axios from '../../configurations/Axios';
import { useUser } from '../../UserContext';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';


const AppointmentForm = () => {
  const today = new Date();
  const navigate=useNavigate();
  const maxDate = new Date(today);
  maxDate.setDate(today.getDate() + 7);
  const todayFormatted = today.toISOString().split('T')[0];
  const maxDateFormatted = maxDate.toISOString().split('T')[0];
  const [isChecked,setIsChecked]=useState(false)
  const { id: userId } = useUser();
 
  const [formData, setFormData] = useState({
    patientName: '',
    age: '',
    mobileNumber: '',
    gender: '',
    aadharNumber: '',
    appointmentDate: '',
    diagnosticTests: [], // Store test IDs
    diagnosticCenter: ''
  });
  const [centersWithTests, setCentersWithTests] = useState([]);
  const [filteredCenters, setFilteredCenters] = useState([]);
  const [selectedCenterId, setSelectedCenterId] = useState(null);
  const [selectedTests, setSelectedTests] = useState([]); // Store selected test IDs from modal
  const [showAlert, setShowAlert] = useState({ show: false, message: '', variant: '' });
  const [isNewPatient, setIsNewPatient] = useState(false);
  const [isShowFields, setIsShowFields] = useState(false);
  const [patientId, setPatientId] = useState(null); // State to store patient ID
  const [showModal, setShowModal] = useState(false);
  const [isAppointmentDateSelected, setIsAppointmentDateSelected] = useState(false);
  const [mobileNumberValid, setMobileNumberValid] = useState(false); // New state for mobile number validation
 
  useEffect(() => {
    // Fetch diagnostic centers when the component mounts
    const fetchCentersWithTests = async () => {
      try {
        const response = await Axios.get('/diagnosticcenter');
        setCentersWithTests(response.data.data);
      } catch (error) {
        console.error('Error fetching diagnostic centers:', error);
      }
    };
 
    fetchCentersWithTests();
  }, []);
 
  useEffect(() => {
    if (formData.appointmentDate) {
      fetchAvailableCenters(formData.appointmentDate);
    }
  }, [formData.appointmentDate]);
 
  const fetchAvailableCenters = async (date) => {
    try {
      const response = await Axios.get(`/booking/date/${date}`);
      const availableTestIds = response.data;
      const filteredCenters = centersWithTests.filter(center =>
        center.diagnosticTests.some(test => availableTestIds.includes(test.id))
      );
      setFilteredCenters(filteredCenters);
      const centersWithFilteredTests = filteredCenters.map(center => ({
        ...center,
        diagnosticTests: center.diagnosticTests.filter(test => availableTestIds.includes(test.id))
      }));

      setFilteredCenters(centersWithFilteredTests);
    } catch (error) {
      console.error('Error fetching available centers:', error);
      toast.error('Error fetching available centers.')
    }
  };
 
  const handleChange = (e) => {
    const { name, value, type, checked, } = e.target;
     setIsChecked(checked)
    if (type === 'checkbox') {
      setSelectedTests(prevTests =>
        checked ? [...prevTests, value] : prevTests.filter(testId => testId != value)
      );
    } else if (type === 'radio') {
      setSelectedCenterId(value);
      setSelectedTests([]);
    } else {
      setFormData(prevState => ({
        ...prevState,
        [name]: value
      }));
      if (name === 'appointmentDate') {
        setIsAppointmentDateSelected(value !== '');
      }
      if (name === 'mobileNumber') {
        const mobileNumberPattern = /^[6789][0-9]{9}$/;
        setMobileNumberValid(mobileNumberPattern.test(value));
      }
    }
  };
 
  const handleFetchPatient = async () => {
    // Validate mobile number format
    const mobileNumberPattern = /^[6789][0-9]{9}$/;
    if (!mobileNumberPattern.test(formData.mobileNumber)) {
      toast.info('Please enter a valid 10-digit mobile number starting with 6, 7, 8, or 9.')
      return;
    }
 
    try {
      const response = await Axios.get(`/patient/mobile/${formData.mobileNumber}`);
      const patientData = response.data.data;
      if (patientData && typeof patientData !== 'string' && patientData.user.id !== userId) {
        toast.warn('Patient already exists with a different user. Please provide a valid mobile number.')
      } else if (patientData && typeof patientData !== 'string') {
        setFormData({
          ...formData,
          patientName: patientData.name || '',
          age: patientData.age || '',
          gender: patientData.gender || '',
          aadharNumber: patientData.aadharNumber || ''
        });
        setPatientId(patientData.id); // Store patient ID
        setIsNewPatient(false);
        setIsShowFields(true);
        toast.info('Patient found! Please complete the form.')
      } else {
        setFormData(prevState => ({
          ...prevState,
          patientName: '',
          age: '',
          gender: '',
          aadharNumber: ''
        }));
        setIsNewPatient(true);
        setIsShowFields(true);
        toast.info('New patient! Please enter details.')
      }
    } catch (error) {
      console.error('Error fetching patient details:', error);
      toast.error('Error fetching patient details.')
    }
  };
 
  const handleCreatePatient = async () => {
    try {
      const newPatientData = {
        name: formData.patientName,
        age: formData.age,
        gender: formData.gender,
        aadharNumber: formData.aadharNumber,
        phoneNo: formData.mobileNumber,
        user: {
          id: userId
        }
      };
      const response = await Axios.post('/patient', newPatientData);
      const patientData = response.data.data;
      if (patientData) {
        setPatientId(patientData.id); // Store patient ID
        // setShowAlert({ show: true, message: 'Patient created successfully!', variant: 'success' });
      }
    } catch (error) {
      console.error('Error creating patient:', error);
      // setShowAlert({ show: true, message: 'Error creating patient.', variant: 'danger' });
    }
  };
 
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isNewPatient) {
        await handleCreatePatient(); // Create patient first if new
      }
      
      if (!formData.diagnosticCenter) {
        toast.info('Please select a diagnostic center.')
        return;
      }
 
      const appointmentData = {
        appointmentDate: formData.appointmentDate,
        diagnosticCenter: {
          id: parseInt(formData.diagnosticCenter, 10) // Use the selected center ID
        },
        patient: {
          id: patientId // Use the patient ID retrieved from the backend
        },
        diagnosticTests: formData.diagnosticTests.map(testId => ({
          id: testId // Send test IDs
        }))
      };
      const response = await Axios.post('/appointment', appointmentData);
      //await Axios.put(`/booking/slot/${appointmentData.diagnosticCenter}/date/${appointmentData.appointmentDate}`)
      if (response.status === 200) {
        setFormData({
          patientName: '',
          age: '',
          mobileNumber: '',
          gender: '',
          aadharNumber: '',
          appointmentDate: '',
          diagnosticTests: [],
          diagnosticCenter: ''
        });
        setCentersWithTests([]);
        setFilteredCenters([]);
        setSelectedCenterId(null); // Clear selected center
        setSelectedTests([]); // Clear selected tests
        setIsNewPatient(false);
        setPatientId(null); // Clear patient ID
      }
     toast.success("Appointment Booked Successfully")
      navigate('/home')

    } catch (error) {
      // console.error('Error booking appointment:', error);
      // setShowAlert({ show: true, message: 'Error booking appointment.', variant: 'danger' });
    }
  };
 
  const handleOpenModal = () => {
    setShowModal(true);
  };
 
  const handleCloseModal = () => {
    setShowModal(false);
    // setSelectedCenterId(null);
    // setSelectedTests([]);
  };
 
  const handleCloseNoSelectModal = () => {
    setShowModal(false);
    setSelectedTests([]);
    setSelectedCenterId(null);
  };
 
  const handleTestChange = (testId) => {
    setSelectedTests((prevSelectedTests) =>
      prevSelectedTests.includes(testId)
        ? prevSelectedTests.filter(id => id !== testId)
        : [...prevSelectedTests, testId]
    );
  };
 
  const handleSelectTests = () => {
    setFormData(prevState => ({
      ...prevState,
      diagnosticTests: selectedTests,
      diagnosticCenter: selectedCenterId
    }));
    handleCloseModal();
   
  };
 
  return (
 
       <div className="container flex justify-center mt-20 text-sm">
      <Form className="form-container w-2/3 border p-4 rounded shadow" onSubmit={handleSubmit}>
        <h3 className="text-center mb-2">Appointment Form</h3>
     
        {showAlert.show && <Alert className='custom-alert' variant={showAlert.variant}>{showAlert.message}</Alert>}
       
        {/* Mobile Number Input and Find Button */}
        <Row className="flex mb-3">
          <Col md={4}>
            <Form.Group controlId="formMobileNumber">
              <Form.Label>Mobile Number</Form.Label>
              <Form.Control
                type="tel"
                placeholder="Enter mobile number"
                name="mobileNumber"
                value={formData.mobileNumber}
                onChange={handleChange}
                pattern="[6789][0-9]{9}"
                title="Mobile number must be 10 digits and start with 6, 7, 8, or 9."
                required
              />
            </Form.Group>
          </Col>
          <Col md={4}>
            <Button
              variant="primary"
              onClick={handleFetchPatient}
              className="w-100 mt-[28px]"
              disabled={!mobileNumberValid} // Disable button if mobile number is not valid
            >
              Find
            </Button>
          </Col>
        </Row>
 
        {/* Conditional Rendering Based on Patient Existence */}
        {isShowFields &&(
          <>
            <Row className="mb-3">
              <Col md={4}>
                <Form.Group controlId="formPatientName">
                  <Form.Label>Patient Name</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter patient name"
                    name="patientName"
                    value={formData.patientName}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>
              <Col md={4}>
                <Form.Group controlId="formAge">
                  <Form.Label>Age</Form.Label>
                  <Form.Control
                    type="number"
                    placeholder="Enter age"
                    name="age"
                    value={formData.age}
                    onChange={handleChange}
                    min={3}
                    max={100}
                    required
                  />
                </Form.Group>
              </Col>
            </Row>
            <Row className="mb-3">
              <Col md={4}>
                <Form.Group controlId="formGender">
                  <Form.Label>Gender</Form.Label>
                  <Form.Control
                    as="select"
                    name="gender"
                    value={formData.gender}
                    onChange={handleChange}
                    required
                  >
                    <option value="" disabled>Select gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                  </Form.Control>
                </Form.Group>
              </Col>
              <Col md={4}>
                <Form.Group controlId="formAadharNumber">
                  <Form.Label>Aadhar Number</Form.Label>
                  <Form.Control
                    type="tel"
                    placeholder="Enter aadhar number"
                    name="aadharNumber"
                    value={formData.aadharNumber}
                    onChange={handleChange}
                    required
                    pattern="[2-9][0-9]{11}"
                  />
                </Form.Group>
              </Col>
            </Row>
          </>
        )}
       
        <Row className="mb-3">
          <Col md={4}>
            <Form.Group controlId="formAppointmentDate">
              <Form.Label>Appointment Date</Form.Label>
              <Form.Control
                type="date"
                name="appointmentDate"
                value={formData.appointmentDate}
                onChange={handleChange}
                min={todayFormatted}
                max={maxDateFormatted}
                required
              />
            </Form.Group>
          </Col>
        </Row>
 
        <Row className="mb-3">
          <Col md={4}>
            <Button disabled={!isAppointmentDateSelected} className='bg-success border-0' onClick={handleOpenModal}>
              Select Tests
            </Button>
          </Col>
        </Row>
 
        {/* Modal for selecting tests */}
        <Modal show={showModal} onHide={handleCloseModal}>
          <Modal.Header closeButton>
            <Modal.Title>Select Tests</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {filteredCenters.map(center => (
              <div key={center.id} className="mb-3">
                <h5>{center.name}</h5>
                <Form.Check
                  type="radio"
                  id={center.id}
                  label={`Select ${center.name}`}
                  value={center.id}
                  checked={selectedCenterId == center.id}
                  onChange={handleChange}
                />
                {selectedCenterId == center.id && center.diagnosticTests.map(test => (
                  <Form.Check
                    key={test.id}
                    type="checkbox"
                    id={test.id}
                    label={`${test.testName} - ${test.testPrice} Rs`}
                    value={test.id}
                    checked={selectedTests.includes(test.id)}
                    onChange={() => handleTestChange(test.id)}
                  />
                ))}
              </div>
            ))}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseNoSelectModal}>
              Close
            </Button>
            <Button
              variant="primary"
              onClick={handleSelectTests}
            >
              Select Tests
            </Button>
          </Modal.Footer>
        </Modal>
 
        {/* Diagnostic Centers (Already Selected Center) */}
        {formData.diagnosticCenter && (
          <Row className="mb-3">
            <Col>
              <Form.Group controlId="formDiagnosticCenter">
                <Form.Label>Diagnostic Center</Form.Label>
                <Form.Control
                  type="text"
                  value={centersWithTests.find(center => center.id === parseInt(formData.diagnosticCenter, 10))?.name || 'No Center Selected'}
                  readOnly
                />
              </Form.Group>
              <div>
                {formData.diagnosticTests.map(testId => {
                  var testDetails = centersWithTests
                    .flatMap(center => center.diagnosticTests);
                    testDetails.map(test=>{console.log(test.id)})
                    testDetails=testDetails.filter(test=>test.id==testId)
                    console.log(testDetails)
                  return (
                    <div key={testId}>
                      {testDetails.length > 0 ? (
                        <ul>
                          {testDetails.map(test => (
                            <li key={test.id}>
                             <p>Test Name: {test.testName}</p>
                              <p>Test Price: {test.testPrice} Rs</p>
                             
                             
                            </li>
                          ))}
                        </ul>
                      ) : (
                        <p>No details found for Test ID: {testId}</p>
                      )}
                    </div>
                  );
                })}
              </div>
            </Col>
          </Row>
        )}
 
        <Button type="submit" variant="primary" className="w-100 mt-3">
          Book Appointment
        </Button>
      </Form>
    </div>
  );
};
 
export default AppointmentForm;