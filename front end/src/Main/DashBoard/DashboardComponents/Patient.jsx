import React, { useState, useEffect } from 'react';
import { Modal, Button, Form, Alert, Table, Dropdown } from 'react-bootstrap';
import Axios from '../../../configurations/Axios';
import { useUser } from '../../../UserContext';
import { downloadAppointmentHtmlFile } from '../../../utils/downloadAppointmentHtmlFile';
import { toast } from 'react-toastify';
const Patient = () => {
  const [patients, setPatients] = useState([]);
  const [showAddPatientModal, setShowAddPatientModal] = useState(false);
  const [showAppointmentsModal, setShowAppointmentsModal] = useState(false);
  const [showUpdatePatientModal, setShowUpdatePatientModal] = useState(false);
  const [selectedPatient, setSelectedPatient] = useState(null);
  const [appointments, setAppointments] = useState([]);
  const [showAlert, setShowAlert] = useState({ show: false, message: '', variant: '' });
  const {role,username,center_id,id:userId}=useUser()
  const [newPatient, setNewPatient] = useState({
    name: '',
    phoneNo: '',
    age: '',
    gender: '',
    userId: ''
  });
  const [patientToUpdate, setPatientToUpdate] = useState({
    id: '',
    name: '',
    phoneNo: '',
    age: '',
    gender: ''
  });

 
    const fetchPatients = async () => {
      try {
        if(role==='USER'){
          const response = await Axios.get(`/patient/username/${username}`);
          setPatients(response.data.data);
        }else if(role==='CENTER_ADMIN'){
          const response = await Axios.get(`/appointment/center/${center_id}`);
          const appointments=response.data.data;
          const patients=appointments.map(appointment=>appointment.patient)
          console.log(patients)
          setPatients(patients);
        }
        else{
          const response = await Axios.get('/patients');
          setPatients(response.data.data);
        }
        
      } catch (error) {
        console.error('Error fetching patients:', error);
      }
    };
 useEffect(() => {
    fetchPatients();
  }, []);

  const handleShowAddPatientModal = () => setShowAddPatientModal(true);
  const handleCloseAddPatientModal = () => setShowAddPatientModal(false);

 

  const handleDeletePatient = async (id) => {
    try {
      await Axios.delete(`/patient/${id}`);
      toast.success('Successfully deleted  patient');
      fetchPatients()
    } catch (error) {
      toast.success('Successfully deleted  patient');
    }
  };

  const handleUpdatePatient = async (id) => {
    try {
      const response = await Axios.get(`/patient/${id}`);
      const patient = response.data.data;
      console.log(patient)
      setPatientToUpdate(patient);
      setShowUpdatePatientModal(true);
      fetchPatients()
    } catch (error) {
      toast.success('Successfully updated patient');
    }
  };

  const handleShowAppointmentsModal = async (patient) => {
    try {
      const response = await Axios.get(`/appointment/patient/${patient.id}`);
      setAppointments(response.data.data);
      setSelectedPatient(patient);
      setShowAppointmentsModal(true);
    } catch (error) {
      console.error('Error fetching appointments:', error);
      toast.error('Error fetching appointments.');
    }
  };

  const handleCloseAppointmentsModal = () => {
    setShowAppointmentsModal(false);
    setAppointments([]);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewPatient(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleUpdateChange = (e) => {
    const { name, value } = e.target;
    setPatientToUpdate(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleUpdateSubmit = async () => {
    try {
      await Axios.put(`/patient/${patientToUpdate.id}`, patientToUpdate);
      toast.success("patient updated successfully")
      setShowUpdatePatientModal(false);
      setPatientToUpdate({
        id: '',
        name: '',
        phoneNo: '',
        age: '',
        gender: ''
      });

     fetchPatients();
    } catch (error) {
      
      toast.success("patient updated successfully")
    }
  };

  const handlePrintInvoice = (id) => {
 
      const appointment = appointments.find(a => a.id === id);
          downloadAppointmentHtmlFile(appointment, appointment.testResults);
  
  };

  return (
    <div className="container mx-auto mt-4 p-4">
      <h2 className="text-2xl font-bold text-center mb-4">Patient Management</h2>

      {/* Alert for Success/Failure */}
      {showAlert.show && <Alert variant={showAlert.variant}>{showAlert.message}</Alert>}

      {/* Patients Table */}
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Name</th>
            <th>Phone Number</th>
            <th>Age</th>
            <th>Gender</th>
            {role==="USER" && <th>Actions</th>}
          </tr>
        </thead>
        <tbody>
          {typeof patients === 'string' ? (
            <tr>
              <td colSpan="5" className="text-center">No Patients currently</td>
            </tr>
          ) : (
            patients.map(patient => (
              <tr key={patient.id}>
                <td>{patient.name}</td>
                <td>{patient.phoneNo}</td>
                <td>{patient.age}</td>
                <td>{patient.gender}</td>
                {role==="USER" &&   
                <td>
                  <Dropdown>
                    <Dropdown.Toggle variant="secondary">
                      Actions
                    </Dropdown.Toggle>

                    <Dropdown.Menu>
                      <Dropdown.Item onClick={() => handleShowAppointmentsModal(patient)}>View Appointments</Dropdown.Item>
                    {role==='USER' &&  <Dropdown.Item onClick={() => handleUpdatePatient(patient.id)}>Update</Dropdown.Item>}
                    {role!=='CENTER_ADMIN'&& <Dropdown.Item onClick={() => handleDeletePatient(patient.id)}>Delete</Dropdown.Item>}
                    </Dropdown.Menu>
                  </Dropdown>
                </td>
}
              </tr>
            ))
          )}
        </tbody>
      </Table>

      {/* Update Patient Modal */}
      <Modal show={showUpdatePatientModal} onHide={() => setShowUpdatePatientModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Update Patient</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formName">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter name"
                name="name"
                value={patientToUpdate.name}
                onChange={handleUpdateChange}
                required
              />
            </Form.Group>
            <Form.Group controlId="formPhoneNo">
              <Form.Label>Phone Number</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter phone number"
                name="phoneNo"
                value={patientToUpdate.phoneNo}
                onChange={handleUpdateChange}
                pattern="[6789][0-9]{9}"
                title="Mobile number must be 10 digits and start with 6, 7, 8, or 9."
                required
              />
            </Form.Group>
            <Form.Group controlId="formAge">
              <Form.Label>Age</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter age"
                name="age"
                value={patientToUpdate.age}
                onChange={handleUpdateChange}
                min={3}
                max={100}
                required
              />
            </Form.Group>
            <Form.Group controlId="formGender">
              <Form.Label>Gender</Form.Label>
              <Form.Control
                as="select"
                name="gender"
                value={patientToUpdate.gender}
                onChange={handleUpdateChange}
                required
              >
                <option value={patientToUpdate.gender}>{patientToUpdate.gender}</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </Form.Control>
            </Form.Group>
            <Form.Group controlId="formUserId">
              <Form.Label>User ID</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter user ID"
                name="userId"
                value={userId}
                readOnly
                onChange={handleUpdateChange}
                required
              />
            </Form.Group>
            <Button variant="primary" onClick={handleUpdateSubmit} className="mt-3">
              Update Patient
            </Button>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowUpdatePatientModal(false)}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>

      {/* Appointments Modal */}
      <Modal show={showAppointmentsModal} onHide={handleCloseAppointmentsModal} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Appointments for {selectedPatient?.name}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>Date</th>
                <th>Approval Status</th>
                {/* <th>Diagnostic Tests</th> */}
                <th>Center</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {typeof appointments === 'string'? (
                <tr>
                  <td colSpan="5" className="text-center">No appointments found</td>
                </tr>
              ) : (
                appointments.map((appointment) => (
                  <tr key={appointment.id}>
                    <td>{new Date(appointment.appointmentDate).toLocaleDateString()}</td>
                    <td>{appointment.approvalStatus}</td>
                    {/* <td>{appointment.diagnosticTests.map(test => test.name).join(" ")}</td> */}
                    <td>{appointment.diagnosticCenter.name}</td>
                    <td>
                    
                      <Button disabled={appointment.approvalStatus==='PENDING'} variant="primary" onClick={() => handlePrintInvoice(appointment.id)}>
                        Print Invoice
                      </Button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </Table>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseAppointmentsModal}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default Patient;
