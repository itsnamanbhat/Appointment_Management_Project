import React, { useEffect, useState } from 'react';
import "./centerAdministrator.css";
import Axios from '../../../configurations/Axios';
import { Dropdown, Button, Table } from 'react-bootstrap';
import { paste } from '@testing-library/user-event/dist/paste';
import { toast } from 'react-toastify';


const CenterAdministrator = () => {
    const [centerAdmin, setCenterAdmin] = useState([]);
    const [view, setView] = useState('all'); // State to track the current view
    const [changed,setChanged]=useState(false);

    useEffect(() => {
        const fetchAdmins = async () => {
            try {
                const url = view === 'all' ? "/requests" : "/requests/pending";
                const response = await Axios.get(url);
                setCenterAdmin(response.data.data);
                console.log(response.data.data); // Log the response data
            } catch (err) {
                console.log("Error fetching centerAdministrators", err);
            }
        };
        fetchAdmins();
    }, [view,changed]); // Dependency array includes `view` to refetch data when `view` changes

    const handleApproveRequest = async (c) => {
        try {
            
            const response = await Axios.put(`/approve/${c.id}`);
            console.log(response.data.data);
            // Optionally refetch data here or update state to reflect the change
            const additionalResponse = await Axios.post('/auth/signup', {username:c.username,email:c.email,password:c.password,role:"CENTER_ADMIN"});
            console.log('Additional Response:', additionalResponse.data.data);
            toast.success("Request has been approved");
            setChanged(true);
        } catch (err) {
            console.log("Error approving request", err);
        }
    };

    const handleDeleteRequest = async(c) => {
        try{
       const response= await Axios.delete(`/requests/${c.id}`);
       toast.dismiss("Approval request has been denied by admin successfully")
       setChanged(true);
        }catch(err){
            console.log(err);
        }

    };

    const toggleView = () => {
        setView(view === 'all' ? 'pending' : 'all');
    };

    return (
        <div className='centerAdmin_main' >
            <Button
                variant="primary"
                onClick={toggleView}
                className="mb-3"
            >
                {view === 'all' ? 'Show Pending Requests' : 'Show All Requests'}
            </Button>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Approved</th>
                        <th>Username</th>
                        <th>Email</th>
                       {view==='pending'&& (<th>Status</th>)}
                    </tr>
                </thead>
                <tbody>
                    {typeof centerAdmin =='string' ? (
                        <tr>
                            <td colSpan="5">No requests available</td>
                        </tr>
                    ) : (
                        centerAdmin.map(c => (
                            <tr key={c.id}>
                                <td>{c.id}</td>
                                <td>{c.approved ? 'Yes' : 'No'}</td>
                                <td>{c.username}</td>
                                <td>{c.email}.com</td>
                               {view==='pending' && <td>
                                    <Dropdown>
                                        <Dropdown.Toggle variant="secondary">
                                            Actions
                                        </Dropdown.Toggle>

                                        <Dropdown.Menu>
                                            <Dropdown.Item onClick={() => handleApproveRequest(c)}>
                                                Approve Request
                                            </Dropdown.Item>
                                            <Dropdown.Item onClick={() => handleDeleteRequest(c)}>
                                                Delete Request
                                            </Dropdown.Item>
                                        </Dropdown.Menu>
                                    </Dropdown>
                                </td>}
                            </tr>
                        ))
                    )}
                </tbody>
            </Table>
        </div>
    );
};

export default CenterAdministrator;
