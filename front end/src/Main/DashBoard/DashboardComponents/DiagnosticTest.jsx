import React, { useEffect, useState } from 'react';
import { Dropdown, DropdownButton, Button, Modal, Form, Table } from 'react-bootstrap';
import Axios from '../../../configurations/Axios';
import { useUser } from '../../../UserContext';
import { toast } from 'react-toastify';

const DiagnosticTest = () => {
  const {id:userId,role,centerAdd,center_id}=useUser()
  const today = new Date();
  const maxDate = new Date(today);
  maxDate.setDate(today.getDate() + 7);
  const todayFormatted = today.toISOString().split('T')[0];
  const maxDateFormatted = maxDate.toISOString().split('T')[0];
  const [testsList, setTestsList] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [slots, setSlots] = useState([]);
  const [showSlotModal, setShowSlotModal] = useState(false);
  const [showSlotsModal, setShowSlotsModal] = useState(false); // New state
  const [selectedCenterId, setSelectedCenterId] = useState(null)
  const [selectedTestId,setSelectedTestId]=useState(null)
  const [center,setCenter]=useState("");
  const [newTest, setNewTest] = useState({
    testName: '',
    testPrice: '',
    diagnosticCenter: {
      id: center_id
    }
  });
  const [currentTest, setCurrentTest] = useState(null);

  // useEffect(() => {
  const fetchDiagnosticTests = async () => {
    try {
      if (role === 'CENTER_ADMIN') {

        const response = await Axios.get(`/diagnostictest/center/${center_id}`);
        console.log(center_id)
        const testData = response.data.data;
        setTestsList(testData || []);
      } else {
        const response = await Axios.get('/diagnostictest');
        const testData = response.data.data;
        setTestsList(testData || []);
      }

    } catch (error) {
      console.error('Error fetching tests:', error);
    }
  };
  useEffect(() => {
    fetchDiagnosticTests();
  }, []);

  const handleEdit = (id) => {
    const test = testsList.find(t => t.id === id);
    setCurrentTest(test);
    setNewTest({
      testName: test.testName,
      testPrice: test.testPrice,
      diagnosticCenter: {
        id: center_id
      }
    });
    setShowEditModal(true);
  };

  const handleDelete = async (id) => {
    try {
      const response = await Axios.delete(`/diagnostictest/${id}`);
      const deletedTestResponse = response.data.data;
      const message = response.data.message;
      toast.success("Successfully Deleted Test");
      fetchDiagnosticTests()
    } catch (error) {
      fetchDiagnosticTests()
      toast.info("cannot delete test, Appointment exists");
    }
  };

  const handleAddTest = async () => {
    try {
      const response = await Axios.post('/diagnostictest', newTest);
      console.log(response)
      const addedTestResponse = response.data;
      const addedTest = addedTestResponse.data;
      const message = addedTestResponse.message;
      setTestsList([...testsList, addedTest]);
     toast.success(message)
      setShowModal(false);
      setNewTest({
        testName: '',
        testPrice: '',
        diagnosticCenter: {
          id: center_id
        }
      });
    } catch (error) {
      console.error('Error adding test:', error);
    }
  };

  const handleUpdateTest = async () => {
    try {
      const response = await Axios.put(`/diagnostictest/${currentTest.id}`, newTest);
      const updatedTestResponse = response.data;
      const updatedTest = updatedTestResponse.data;
      const message = updatedTestResponse.message;
      setTestsList(testsList.map(test => (test.id === updatedTest.id ? updatedTest : test)));
      toast.success(message)
      setShowEditModal(false);
      setNewTest({
        testName: '',
        testPrice: '',
        diagnosticCenter: {
          id: center_id
        }
      });
    } catch (error) {
      console.error('Error updating test:', error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    setNewTest(prev => ({ ...prev, [name]: value }));

  };

  const handleShowModal = () => setShowModal(true);
  const handleCloseModal = () => setShowModal(false);
  const handleCloseEditModal = () => {
    setShowEditModal(false);
    setNewTest({
      testName: '',
      testPrice: '',
      diagnosticCenter: {
        id: center_id
      }
    });
  }

  //SLOT LOGIC
  const [slotData, setSlotData] = useState({
    test_id: null,
    date: '',
    slot: ''
  });

  const handleSlotChange = (e) => {
    const { name, value } = e.target;
    setSlotData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };
  const handleAddSlot = async (e) => {
    e.preventDefault();
    try {
      const response = await Axios.post('/booking', slotData);
      console.log(response);
      toast.success('Slot added successfully')
      setShowSlotModal(false)
      setShowSlotsModal(false)
    
      // Optionally, refresh slot data or centers list
    } catch (error) {
      const msg = error.response.data;
      toast.error(`Error adding slot: ${msg}`)
    }
  };

  const handleAddSlotClick = (testId) => {
    setSlotData(prevState => ({
      ...prevState,
      test_id: testId
    }));
    setShowSlotModal(true);
  };

  // New function to handle viewing slots
  const handleViewSlots = async (testId) => {
    try {
      const response = await Axios.get(`/booking/test/${testId}`);
      console.log(response.data)
      setSlots(response.data);
      setSelectedTestId(testId);
      setShowSlotsModal(true);
    } catch (error) {
      console.error('Error fetching booking slots:', error);
      toast.error('Failed to fetch booking slots');
    }
  };

  return (
    <div className="container mx-auto my-4 px-4">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-2xl font-bold">Diagnostic Tests List</h2>
        {role==='CENTER_ADMIN' && 
        <Button
          variant="primary"
          onClick={handleShowModal}
          className="bg-blue-500 hover:bg-blue-600 text-white"
        >
          Add Test
        </Button>}
      </div>

      <div>
        <Table striped bordered hover>
          <thead className="bg-gray-100">
            <tr>
              <th>Test Name</th>
              <th>Price</th>
              {role!=='CENTER_ADMIN' && <th>Diagnostic Center</th>}
              {role==="CENTER_ADMIN" &&<th>Actions</th>}
              <th>Add Slots</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {typeof testsList === 'string' ? (
              <tr>
                <td colSpan="6" className="text-center py-4">No tests available</td>
              </tr>
            ) : (
              testsList.map(test => (
                <tr key={test.id}>
                  <td>{test.testName}</td>
                  <td>{test.testPrice} Rupees</td>
                 {role!=="CENTER_ADMIN" && <td>{test.diagnosticCenter.id}</td>}
                  {role==="CENTER_ADMIN" &&
                  <td>
                  <DropdownButton id="dropdown-basic-button" title="Actions">
                    <Dropdown.Item as="button" onClick={() => handleEdit(test.id)} className="text-yellow-600">Edit</Dropdown.Item>
                    
                    {/* <Dropdown.Item as="button" onClick={() => handleDelete(test.id)} className="text-red-600">Delete</Dropdown.Item> */}
                  </DropdownButton>
                </td> }
                  
                  <td>
                  
                  <Button variant="primary" onClick={() => handleViewSlots(test.id)}>View Slots</Button> 

                </td>

                </tr>
              ))
            )}
          </tbody>
        </Table>
      </div>

      {/* Add Test Modal */}
      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>Add Diagnostic Test</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="formTestName">
              <Form.Label>Test Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter test name"
                name="testName"
                value={newTest.testName}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formTestPrice">
              <Form.Label>Test Price</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter test price (in rupees)"
                name="testPrice"
                value={newTest.testPrice}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formDiagnosticCenters">
              <Form.Label>Diagnostic Center id</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter diagnostic center Id"
                name="diagnosticCenter.id"
                value={center_id}
                readOnly
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal} className="bg-gray-500 hover:bg-gray-600 text-white">Close</Button>
          <Button variant="primary" onClick={handleAddTest} className="bg-blue-500 hover:bg-blue-600 text-white">Add Test</Button>
        </Modal.Footer>
      </Modal>

      {/* Edit Test Modal */}
      <Modal show={showEditModal} onHide={handleCloseEditModal}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Diagnostic Test</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="formTestName">
              <Form.Label>Test Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter test name"
                name="testName"
                value={newTest.testName}
                onChange={handleChange}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formTestPrice">
              <Form.Label>Test Price</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter test price"
                name="testPrice"
                value={newTest.testPrice}
                onChange={handleChange}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formDiagnosticCenters">
              <Form.Label>Diagnostic Center</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter diagnostic center Id"
                name="diagnosticCenter.id"
                value={newTest.diagnosticCenter.id}
                onChange={handleChange}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseEditModal} className="bg-gray-500 hover:bg-gray-600 text-white">Close</Button>
          <Button variant="primary" onClick={handleUpdateTest} className="bg-blue-500 hover:bg-blue-600 text-white">Update Test</Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showSlotModal} onHide={() => setShowSlotModal(false)} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Add Booking Slot</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleAddSlot}>
            <Form.Group controlId="formDate">
              <Form.Label>Date</Form.Label>
              <Form.Control
                type="date"
                name="date"
                value={slotData.date}
                min={todayFormatted}
                max={maxDateFormatted}
                onChange={handleSlotChange}
                required
              />
            </Form.Group>
            <Form.Group controlId="formSlot">
              <Form.Label>Slots</Form.Label>
              <Form.Control
                type="number"
                name="slot"
                value={slotData.slot}
                onChange={handleSlotChange}
                min={1}
                max={1000}
                required
              />
            </Form.Group>
            <Button variant="primary" type="submit" style={{margin:"0.7rem 0"}}>Add Slot</Button>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => 
            {
              setShowSlotModal(false)
              }}>Close</Button>
        </Modal.Footer>
      </Modal>

      {/* Modal to view booking slots */}
      <Modal show={showSlotsModal} onHide={() => setShowSlotsModal(false)} size="lg">
  <Modal.Header>
    <div className="d-flex justify-content-between w-100">
      <h5 className="modal-title">Booking Slots for Test: {selectedTestId}</h5>
      {role==='CENTER_ADMIN' && 
      
      <Button as="button" onClick={() => handleAddSlotClick(selectedTestId)} className="btn-primary">
        Add Slot
      </Button>}
      
    </div>
    <Button type="button" className="btn-close" aria-label="Close" onClick={() => setShowSlotsModal(false)}></Button>
  </Modal.Header>
  <Modal.Body>
    {slots.length > 0 ? (
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Date</th>
            <th>Slots</th>
          </tr>
        </thead>
        <tbody>
          {slots.map((slot, index) => (
            <tr key={index}>
              <td>{new Date(slot.date).toLocaleDateString()}</td>
              <td>{slot.slot}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    ) : (
      <p>No slots available for this test.</p>
    )}
  </Modal.Body>
  <Modal.Footer>
    <Button variant="secondary" onClick={() => setShowSlotsModal(false)}>Close</Button>
  </Modal.Footer>
</Modal>

    </div>
  );
};

export default DiagnosticTest;
