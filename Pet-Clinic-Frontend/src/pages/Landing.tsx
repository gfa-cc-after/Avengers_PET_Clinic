import "./Landing.css";
import clinicImage from "../images/petclinic.png";
import { useLocation } from "react-router-dom";
import { Pets } from "../components/Pets";

const Landing = () => {
  const location = useLocation();
  const error = location.state?.error;

  return (
    <>
      {error && <div className="error-message">{error}</div>}
      <img src={clinicImage} alt="Clinic" className="clinic-image" />
      <h1 className="h1">Pet Clinic</h1>
      <h2 className="h2">Welcome to our fabulous clinic</h2>
      <Pets />
    </>
  );
};

export default Landing;
