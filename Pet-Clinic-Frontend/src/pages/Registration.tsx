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
        if (!/\S+@\S+\.\S+/.test(email)) {  //.test(email): This method tests whether the email string matches the regular expression pattern. It returns true if the string matches and false otherwise.
            /*             / and /: These are delimiters that indicate the start and end of the regular expression.
            
                            \S +:
            
                            \S: This matches any non - whitespace character.It is equivalent to[^\s].
                            +: This quantifier means "one or more" of the preceding token.So, \S + matches one or more non - whitespace characters.
                            @: This matches the literal @character.
            
                            \S +: Same as above, matches one or more non - whitespace characters.This part is used for the domain part before the dot.
            
                            \.:
            
                            \.: This matches the literal dot.character.The backslash \ is used to escape the dot because, in regular expressions, a dot normally matches any character.
                            \S +: Again, matches one or more non - whitespace characters.This part is used for the domain extension(e.g., com, net, etc.). */
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
