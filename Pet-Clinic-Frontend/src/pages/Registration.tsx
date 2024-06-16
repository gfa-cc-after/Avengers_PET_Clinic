import React from 'react'
import { useState } from 'react';

import './Registration.css'

const Registration = () => {

    const [email, setEmail] = useState(""); 
    const [password, setPassword] = useState("") 

    const handleEmail = (e) => { 
        setEmail(e.target.value); 
        // setSubmitted(false); 
    }
    
    const handlePassword = (e) => { 
        setPassword(e.target.value); 
        // setSubmitted(false); 
    }

    return (
        <div>
            <form className='register-form'> 
                <h3 className='rf-title'>Registration</h3>
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

export default Registration