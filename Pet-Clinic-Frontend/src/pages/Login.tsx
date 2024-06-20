import { useState } from 'react'
import './Login.css'


function LoginForm() {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  return (
    <>
      <h1>Pet Clinic</h1>
      <h2>Login</h2>
     <div className = "formDiv">
        <form className='form' onSubmit={function (event){
          event.preventDefault()
          console.log("Form submitted")
          console.log('username:' , username)
          console.log('password: ' , password)
          window.location.reload()
        }}>
          <div className='inputGroup'>
           <label className='label'>Username:       
              <input className='input'
                type='text'
                name='username'
                value={username}
                onChange={function (event){
                  setUsername(event.target.value)
                }}
            ></input></label>
          </div> 
          <div className='inputGroup'>
            <label className='label'>Password: 
                <input className='input'
                type='password'
                name='password' 
                value={password}
                onChange={function (event){
                  setPassword(event.target.value)
                }}
              ></input>
            </label>
          </div>
          <button className='loginButton'>Login</button>
        </form>
     </div>
    </>
  )
}

export default LoginForm