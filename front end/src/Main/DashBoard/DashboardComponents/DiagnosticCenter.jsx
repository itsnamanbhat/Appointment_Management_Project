import React, { useEffect, useState } from 'react';
import { Dropdown, DropdownButton, Button, Table, Modal, Form } from 'react-bootstrap';
import Axios from '../../../configurations/Axios';
import { useUser } from '../../../UserContext';
import { toast } from 'react-toastify';

const DiagnosticCenter = () => {
  const {id:userId,role,centerAdd,center_id}=useUser()
  const today = new Date();
  const maxDate = new Date(today);
  maxDate.setDate(today.getDate() + 7);
  const todayFormatted = today.toISOString().split('T')[0];
  const maxDateFormatted = maxDate.toISOString().split('T')[0];
 

  const [centersList, setCentersList] = useState([]);
  const [selectedCenter, setSelectedCenter] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [showAddModal, setShowAddModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
 
  const [selectedCenterId, setSelectedCenterId] = useState(null) // New state

  const [newCenter, setNewCenter] = useState({
    name: '',
    contactNO: '',
    address: '',
      user: {
        id: userId
      
    },
    diagnosticTests: []
  });

  const [editCenter, setEditCenter] = useState({
  
    name: '',
    contactNO: '',
    address: '',
    
  });



  
    const fetchCenters = async () => {
      try {
        console.log(role)
        if(role==="CENTER_ADMIN"){
          const response = await Axios.get(`/diagnosticcenter/user/${userId}`);
          const centersData = response.data.data;
          console.log(centersData)
          // console.log(centersData.id)
          {centersData && typeof centersData!=='string' && centerAdd(centersData.id)}
          const center=[]
          center.push(centersData)
          setCentersList(center);
          console.log(center_id)
          
        }else{
          const response = await Axios.get('/diagnosticcenter');
          const centersData = response.data.data;
          setCentersList(centersData);
        }
      
      } catch (error) {
        console.error('Error fetching diagnostic centers:', error);
        setCentersList(error.response.data)
        console.log(centersList)
      }
    };
    useEffect(() => {
    fetchCenters();
  }, []);

  const handleEdit = (center) => {
    setEditCenter({
      name: center.name,
      contactNO: center.contactNO,
      address: center.address
      
    });
    setShowEditModal(true);
  };

  const handleDelete = async (id) => {
    try {
      const response = await Axios.delete(`/diagnosticcenter/${id}`);
      toast.success(response.data.message)
      fetchCenters()
    } catch (error) {
      console.error('Error deleting diagnostic center:', error);
      
    }
  };

  const handleView = (id) => {
    const center = centersList.find(cen => cen.id === id);
    setSelectedCenter(center);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedCenter(null);
  };

  const handleOpenAddModal = () => setShowAddModal(true);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewCenter(prevState => ({
      ...prevState,
      [name]: value
    }));
  };



  const handleAddCenter = async (e) => {
    e.preventDefault();
    try {
      const response = await Axios.post('/diagnosticcenter', newCenter);
     toast.success('Diagnostic center added successfully')
      fetchCenters()
      setNewCenter({
        name: '',
        contactNO: '',
        address: '',
        user: {
            id: userId
          },

        diagnosticTests: []
      });
      setShowAddModal(false);
    } catch (error) {
      console.error('Error adding diagnostic center:', error);
      toast.error('Failed to add diagnostic center')
    }
  };

  const handleEditCenter = async (e) => {
    e.preventDefault();
    try {
      const response = await Axios.put(`/diagnosticcenter/${center_id}`, editCenter);
      toast.success('Diagnostic center updated successfully')
      fetchCenters()
      setShowEditModal(false);
    } catch (error) {
      console.error('Error updating diagnostic center:', error);
      toast('Failed to update diagnostic center');
    }
  };


  
console.log(centersList)
  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">{role==='ADMIN'? "Diagnostic Centers List" :"Diagnostic Center"}</h2>

      {centersList && typeof centersList==='string' &&
       <Button variant="primary" onClick={handleOpenAddModal} className="mb-3">Add Diagnostic Center</Button>}
     
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Contact Number</th>
            <th>Address</th>
            <th>Total Tests</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {Array.isArray(centersList) && centersList.length > 0 ? (
            centersList.map(center => (
              <tr key={center.id}>
                <td>{center.id|| 'N/A'}</td>
                <td>{center.name || 'null'}</td>
                <td>{center.contactNO || 'null'}</td>
                <td>{center.address || 'null'}</td>
                <td>{center.diagnosticTests && center.diagnosticTests.length}</td>
                 {role==='ADMIN' ? 
                 <td><Button as="button" onClick={() => handleView(center.id)}>View Details</Button></td> :
              
                <td>
                  <DropdownButton id="dropdown-basic-button" title="Actions">
                    <Dropdown.Item as="button" onClick={() => handleView(center.id)}>View Details</Dropdown.Item>
                    <Dropdown.Item as="button" onClick={() => handleEdit(center)}>Edit</Dropdown.Item>
                    {/* <Dropdown.Item as="button" onClick={() => handleDelete(center.id)}>Delete</Dropdown.Item> */}
                  </DropdownButton>
                </td>
}
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7" className="text-center">No diagnostic centers to display</td>
            </tr>
          )}
        </tbody>
      </Table>

      {/* Modal to display diagnostic center details */}
      <Modal show={showModal} onHide={handleCloseModal} size="lg">
        <Modal.Header closeButton>
          <Modal.Title> Diagnostic Center Details</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {selectedCenter ? (
            <div className="center-details">

              <h5>{selectedCenter.name}</h5>
              <p><strong>Id: </strong>{selectedCenter.id}</p>
              <p><strong>Contact:</strong> {selectedCenter.contactNO}</p>
              <p><strong>Address:</strong> {selectedCenter.address}</p>
 
            </div>
          ) : (
            <p>No details available.</p>
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>Close</Button>
        </Modal.Footer>
      </Modal>

      {/* Modal to add a new diagnostic center */}
      <Modal show={showAddModal} onHide={() => setShowAddModal(false)} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Add Diagnostic Center</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleAddCenter}>
            <Form.Group controlId="formName">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                name="name"
                value={newCenter.name}
                onChange={handleInputChange}
                pattern="[A-Za-z\s]+"
                required
              />
            </Form.Group>
            <Form.Group controlId="formContactNO">
              <Form.Label>Contact Number</Form.Label>
              <Form.Control
                type="text"
                name="contactNO"
                value={newCenter.contactNO}
                onChange={handleInputChange}
                pattern="[6789][0-9]{9}"
                required
              />
            </Form.Group>
            <Form.Group controlId="formAddress">
              <Form.Label>Address</Form.Label>
              <Form.Control
                type="text"
                name="address"
                value={newCenter.address}
                onChange={handleInputChange}
                required
              />
            </Form.Group>
           
            <Button variant="primary" type="submit" style={{margin:"0.5rem 0"}}>Add Center</Button>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowAddModal(false)}>Close</Button>
        </Modal.Footer>
      </Modal>

      {/* Modal to edit diagnostic center */}
      <Modal show={showEditModal} onHide={() => setShowEditModal(false)} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Edit Diagnostic Center</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleEditCenter}>
            <Form.Group controlId="formName">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                value={editCenter.name}
                onChange={(e) => setEditCenter(prevState => ({ ...prevState, name: e.target.value }))}
                pattern="[A-Za-z\s]+"
                required
              />
            </Form.Group>
            <Form.Group controlId="formContactNO">
              <Form.Label>Contact Number</Form.Label>
              <Form.Control
                type="text"
                value={editCenter.contactNO}
                onChange={(e) => setEditCenter(prevState => ({ ...prevState, contactNO: e.target.value }))}
                pattern="[6789][0-9]{9}"
                required
              />
            </Form.Group>
            <Form.Group controlId="formAddress">
              <Form.Label>Address</Form.Label>
              <Form.Control
                type="text"
                value={editCenter.address}
                onChange={(e) => setEditCenter(prevState => ({ ...prevState, address: e.target.value }))}
                required
              />
            </Form.Group>
            <Button variant="primary" type="submit" style={{margin:"0.5rem 0"}}>Save Changes</Button>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowEditModal(false)}>Close</Button>
        </Modal.Footer>
      </Modal>


      
    </div>
  );
};

export default DiagnosticCenter;
