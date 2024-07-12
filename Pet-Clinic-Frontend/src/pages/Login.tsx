import { useState } from 'react'
import { useNavigate } from 'react-router-dom';  // must be install by command: npm list react-router-dom
import './Login.css';


const LoginForm = () => {
  const [email, setemail] = useState("")
  const [password, setPassword] = useState("")
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const validateForm = () => {
    if (!email || !password) {
      setError("email and Password are required");
      return false;
    }

    const emailRegex = /\S+@\S+\.\S+/;
    if (!emailRegex.test(email)) {
      setError("Email: must be a valid email address");
      return false;
    }
    return true;
  };

  const sendToBackend = async (email: string, password: string) => {
    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      if (data.success) {
        navigate('/landing'); // Redirect to landing page on successful login
      } else {
        setError('Invalid email or password');
      }
    } catch (error) {
      console.error('Error:', error);
      setError('Login failed. Please try again.');
    }
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError(null);

    if (!validateForm()) {
      return;
    }

    sendToBackend(email, password);
  };



  return (
    <>
      <h1>Pet Clinic</h1>
      <h2>Login</h2>
      <div className="formDiv">
        <form className='form' onSubmit={handleSubmit}>
          {error && <div className="error-message">{error}</div>}
          <div className='inputGroup'>
            <label className='label'> Email:
              <input className='input'
                type='text'
                name='email'
                value={email}
                onChange={(event) => setemail(event.target.value)}
              />
            </label>
          </div>
          <div className='inputGroup'>
            <label className='label'>Password:
              <input className='input'
                type='password'
                name='password'
                value={password}
                onChange={(event) => setPassword(event.target.value)}
              />
            </label>
          </div>
          <button className='loginButton' type="submit">Login</button>
        </form>
      </div>
    </>
  );
};

export default LoginForm