import { useState } from 'react';
import './Registration.css';

const Registration = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState<string | null>(null); //generic type <string | null> specify that the state managed by useState can be a string or null. Initial State (null)
    const [submitted, setSubmitted] = useState(false);

    const handleEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
        setSubmitted(false);
    }

    const handlePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
        setSubmitted(false);
    }

    const validateForm = () => {
        if (!email || !password) {
            //If email is an empty string, null, or undefined, !email will be true.
            // When email is evaluated, if email is an empty string (""), null, or undefined, it is considered falsy. 
            setError("Email and Password are required");
            return false;
        }
        if (!/\S+@\S+\.\S+/.test(email)) {  // he syntax /\S+@\S+\.\S+/ is a regular expression (regex) https://regexr.com //.test(email): This method tests whether the email string matches the regular expression pattern. 
            setError("Email address is invalid");
            return false;
        }
        if (password.length < 6) {
            setError("Password must be at least 6 characters");
            return false;
        }
        return true;
    }

    const checkEmailExists = (email: string) => {
        //  API call.
        const existingEmails = ["test@example.com", "user@example.com"];
        return existingEmails.includes(email);
    }

/*     const sendToBackend = async (email: string, password: string) => {
        try {
            const response = await fetch('https://backend-api.com/register.. must be add', {
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
            console.log('Success:', data);
        } catch (error) {
            console.error('Error:', error);
            setError('Registration failed. Please try again.');
        }
    } */


    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError(null);

        if (!validateForm()) {
            return;
        }

        if (checkEmailExists(email)) {
            setError("Email already exists");
            return;
        }

          // If all validations pass
        setSubmitted(true);
        //sendToBackend(email, password);
        setEmail("");
        setPassword("");
    }

    return (
        <div>
            <form className='register-form' onSubmit={handleSubmit}>
                <h3 className='rf-title'>Registration</h3>

                {error && <div className="error-message">{error}</div>}
                {submitted && <div className="success-message">From validation successful!</div>}

                <label className="label">Email</label>
                <input
                    onChange={handleEmail}
                    className="input"
                    value={email}
                    type="text"
                />

                <label className="label">Password</label>
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
