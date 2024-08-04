import { Link } from 'react-router-dom';
import { useAuth } from './AuthContext';
import './Landing.css';
import clinicImage from "../images/petclinic.png";

const Landing = () => {

  const { isLoggedIn, logout } = useAuth();

  return (
    <div>
      <nav className='menu'>
        <Link to="/login" className="menu-item">Login</Link>
        <Link to="/registration" className="menu-item">Registration</Link>
        {isLoggedIn && (
          <>
            <Link to="/profile" className="menu-item">Profile</Link>
            <button className="menu-item" onClick={logout}>Logout</button>
          </>
        )}
      </nav>

      <img src={clinicImage} alt="Clinic" className="clinic-image" />
      <h1 className="h1">Pet Clinic</h1>
      <h2 className="h2">Welcome to our fabulous clinic</h2>
    </div>
  );
};

export default Landing;