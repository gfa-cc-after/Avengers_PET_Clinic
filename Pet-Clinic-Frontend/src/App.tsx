import './App.css';
import clinicImage from './images/petclinic.png'; 

function App() {
  return (
    <>
     <img src={clinicImage} alt="Clinic" className="clinic-image" /> 
      <h1 className="h1">Pet Clinic</h1>
      <h2 className="h2">Welcome to our fabulous clinic</h2>
      
    </>
  );
}

export default App;
