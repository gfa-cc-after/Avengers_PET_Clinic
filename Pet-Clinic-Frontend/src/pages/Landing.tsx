import "./Landing.css"
import { useLocation } from "react-router-dom"
import clinicImage from "../images/petclinic.png"

const Landing = () => {
  const location = useLocation()
  const error = location.state?.error

  return (
    <>
      {error && <div className="error-message">{error}</div>}
      <img src={clinicImage} alt="Clinic" className="clinic-image" />
      <h1 className="h1">Pet Clinic</h1>
      <h2 className="h2">Welcome to our fabulous clinic</h2>
    </>
  )
}

export default Landing
