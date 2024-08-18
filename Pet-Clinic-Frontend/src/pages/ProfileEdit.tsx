import React, { useState } from 'react';
import "./ProfileEdit.css";
import { useNavigate } from 'react-router-dom';
import { useAuth } from './AuthContext';

const backendUrl = import.meta.env.VITE_BACKEND_URL;

const ProfileEdit = () => {
  const { token } = useAuth();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);
  const [confirmingDelete, setConfirmingDelete] = useState(false);
  const navigate = useNavigate();


  const sendToBackend = async (email: string, password: string) => {
    try {
      const response = await fetch(`${backendUrl}/profile-edit`, { // what endpoint is in backEnd??
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({ email, password }),
      });

      if (!response.status.toString().startsWith('2')) {
        setError('Failed to update profile. Please try again.');
      } else {
        setSuccess('Profile updated successfully!');
      }

    } catch (error) {
      console.error('Error:', error);
      setError('An error occurred. Please try again later.');
    }
  };

  const deleteProfile = async () => {
    try {
      console.log("asdasdasdasdasdasd");
      const response = await fetch(`${backendUrl}/delete`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
      });

      if (!response.status.toString().startsWith('2')) {
        navigate('/', { state: { error: 'Failed to delete profile. Please try again.' } });

      } else {
        navigate('/', { state: { success: 'Profile deleted successfully!' } });
      }
    } catch (error) {
      console.error('Error:', error);
      navigate('/', { state: { error: 'An error occurred. Please try again later.' } });
    }
  };


  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError(null);
    setSuccess(null);

    if (!email || !password) {
      setError('Email and password are required.');
      return;
    }

    sendToBackend(email, password);
  };

  const handleDeleteClick = () => {
    setConfirmingDelete(true);
  };

  const handleCancelClick = () => {
    setConfirmingDelete(false);
  };

  const handleConfirmDeleteClick = () => {
    console.log("delete has been called")
    deleteProfile();
  };

  return (
    <div className='formDiv'>
      <h1 className='rf-title'>Edit Profile</h1>
      <form onSubmit={handleSubmit}>
        {error && <div className="error-message">{error}</div>}
        {success && <div className="success-message">{success}</div>}

        <div>
          <label className='label' htmlFor="email">Enter a new Email:</label>
          <input
            className='input'
            type="email"
            id="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div>
          <label className='label' htmlFor="password">Enter a new Password:</label>
          <input
            className='input'
            type="password"
            id="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button className='btn' type="submit">Save Changes</button>
      </form>
      {confirmingDelete ? (
        <div>
          <button className='btn cancel-btn' onClick={handleCancelClick}>Cancel</button>
          <button className='btn confirm-delete-btn' onClick={handleConfirmDeleteClick}>Confirm Delete</button>
        </div>
      ) : (
        <button className='btn delete-btn' onClick={handleDeleteClick}>Delete Profile</button>
      )}
    </div>

  );


};

export default ProfileEdit;
