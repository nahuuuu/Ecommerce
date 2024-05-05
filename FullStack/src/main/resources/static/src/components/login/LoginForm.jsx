import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';
import "../../stylesheets/loginForm.css";
import Input from "../Input.jsx";
import Button from "../Button.jsx";

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isHovered, setIsHovered] = useState(false);
  const navigate = useNavigate();
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/login', {
        username: username,
        password: password
      });

      if (response.status === 200) {
        Cookies.set('token', response.data.jwt, { secure: false, sameSite: 'strict' });
        
        navigate("/home");
      } else if (response.status === 401) {
          console.log("contraseña o usuario incorrecto");
      }
    } catch (error) {
      console.error('Error al iniciar sesión:', error);
    }
  };
  return (
    <div className="loginForm_container">
      <form onSubmit={handleSubmit}>
        <div className='loginForm_input'>
          <Input type="username" text="Nombre de usuario" onChange={e => setUsername(e.target.value)}></Input>
        </div>
        <div className='loginForm_input'>
          <Input type="password" text="Contraseña" onChange={e => setPassword(e.target.value)}></Input>
        </div>
        <div className="loginForm_buttons">
          <Button b="b1" buttonExpand="buttonExpand" buttonExpandMin={"25%"} buttonExpandMax={"35%"} type="submit" text="Iniciar Sesión" isHovered={isHovered} />
          <Button b="b2" buttonExpand="buttonExpand" buttonExpandMin={"25%"} buttonExpandMax={"35%"} type="redireccion" text="Registrarse" ruta={"/register"} onMouseEnter={() => setIsHovered(true)} onMouseLeave={() => setIsHovered(false)} />
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
