import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import cardImg from "../Images/welcome_3.avif"
import { useUser } from '../../UserContext';
import { Link } from 'react-router-dom';
function Cards() {
  const {username}=useUser();
  return (
    <Card style={{ width: '20rem',borderRadius:"10px",padding:"1rem" }}>
      <Card.Img variant="top" src={cardImg} style={{ height: '14rem' }} />
      <Card.Body>
        <Card.Title style={{ textAlign:"center" }} >Add and Manage your Bookings here</Card.Title>
        <Card.Text style={{ textAlign:"center" }}>
         Start booking your appointments now
        </Card.Text>
       <Link to={username ? '/appointmentBooking':'/login'}>
        <Button variant="primary" style={{ marginLeft:"3rem" }} 
        >Book appointment</Button>
        </Link>
      </Card.Body>
    </Card>
  );
}

export default Cards;