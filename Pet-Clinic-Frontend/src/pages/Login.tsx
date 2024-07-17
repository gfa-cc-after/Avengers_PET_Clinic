import { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import './Login.css';

const Login = () => {
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const validateForm = () => {
    if (!email || !password) {
      setError("Email and password are required");
      return false;
    }

    const emailRegex = /\S+@\S+\.\S+/;
    if (!emailRegex.test(email)) {
      setError("Must be a valid email address");
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
    <div>
      <form className='formDiv' onSubmit={handleSubmit}>
        <h3 className='rf-title'>Login</h3>
        {error && <div className="error-message">{error}</div>}
        <label className='label'>Email:</label>
        <input
          className='input'
          type='text'
          name='email'
          value={email}
          onChange={(event) => setEmail(event.target.value)}
        />
        <label className='label'>Password:</label>
        <input
          className='input'
          type='password'
          name='password'
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
        <button className='btn' type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
