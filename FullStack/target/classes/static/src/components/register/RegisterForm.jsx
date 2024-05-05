import "../../stylesheets/registerForm.css"
import Input from "../Input.jsx"
import Button from "../Button.jsx"
import { useState } from 'react';

const RegisterForm = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    const response = await fetch('http://localhost:8080/crear-usuario', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: username,
        email: email,
        password: password
      })
    });
      console.log(response);

  }

  return (
    <div className="loginForm_container">
        <form onSubmit={handleSubmit}>
          <div className='loginForm_input'><Input type="text" text="Usuario" onChange={e => setUsername(e.target.value)}></Input></div>
          <div className='loginForm_input'><Input type="email" text="Correo Electrónico" onChange={e => setEmail(e.target.value)}></Input></div>
          <div className='loginForm_input'><Input type="password" text="Contraseña" onChange={e => setPassword(e.target.value)}></Input></div>
        
            <div className="registerForm_buttons">
                <Button buttonExpand={"buttonExpand"} buttonExpandMin={"30%"} buttonExpandMax={"40%"} type={"submit"} text={"Completar Registro"}/>
            </div>

        </form>
    </div>
  )
}

export default RegisterForm
