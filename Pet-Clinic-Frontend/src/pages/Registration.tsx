import { useState } from 'react';
import './Registration.css';

const Registration = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState<string | null>(null);
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
            setError("Email and Password are required");
            return false;
        }
        if (!/\S+@\S+\.\S+/.test(email)) {
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
        // Simulate checking the email. Replace with real API call.
        const existingEmails = ["test@example.com", "user@example.com"];
        return existingEmails.includes(email);
    }

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

        // Simulate a successful registration
        setSubmitted(true);
        setEmail("");
        setPassword("");
    }

    return (
        <div>
            <form className='register-form' onSubmit={handleSubmit}>
                <h3 className='rf-title'>Registration</h3>

                {error && <div className="error-message">{error}</div>}
                {submitted && <div className="success-message">Registration successful!</div>}

                <label className="label">Email</label>
                <input
                    onChange={handleEmail}
                    className="input"
                    value={email}
                    type="email"
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
    )
}

export default Registration;
