import { useState } from 'react';
import './Registration.css';

const Registration = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState<string | null>(null); //generic type <string | null> specify that the state managed by useState can be a string or null. Initial State (null)
    const [submitted, setSubmitted] = useState(false);

    const handleEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(event.target.value);
        setSubmitted(false);
    }

    const handlePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value);
        setSubmitted(false);
    }

    const validateForm = () => {
        if (!email || !password) {
            setError("Email and password are required");
            return false;
        }
        if (!isValidEmail(email)) { 
            setError("Email address is invalid");
            return false;
        }
        if (password.length < 6) {
            setError("Password must be at least 6 characters");
            return false;
        }
        return true;
    }

    const isValidEmail = (email: string) => {
        const emailPattern = /\S+@\S+\.\S+/;
        return emailPattern.test(email);
    };

    //const checkEmailExists = (email: string) => {
    //  API call. Not implemented in the backEnd yet.

    //}

    const sendToBackend = async (email: string, password: string) => {
        try {
            const response = await fetch('http://localhost:8080/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.text();
            console.log('Success:', data);
        } catch (error) {
            console.error('Error:', error);
            setError('Registration failed. Please try again.');
        }
    }


    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setError(null);

        if (!validateForm()) {
            return;
        }

        /*         if (checkEmailExists(email)) {
                    setError("Email already exists");
                    return;
                } not checkEmailExists implemented yet*/

        // If all validations pass
        setSubmitted(true);
        sendToBackend(email, password);
        setEmail("");
        setPassword("");
    }

    return (
        <div>
            <form className='register-form' onSubmit={handleSubmit}>
                <h3 className='rf-title'>Registration</h3>

                {error && <div className="error-message">{error}</div>}
                {submitted && <div className="success-message">From validation successful!</div>}

                <label className="label">Email:</label>
                <input
                    onChange={handleEmail}
                    className="input"
                    value={email}
                    type="text"
                />

                <label className="label">Password:</label>
                <input
                    onChange={handlePassword}
                    className="input"
                    value={password}
                    type="password"
                />

                <button className="btn" type="submit">
                    Register
                </button>
            </form>
        </div>
    );
}

export default Registration;
