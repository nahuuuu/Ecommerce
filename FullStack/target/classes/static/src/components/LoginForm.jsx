import  { useRef, useState } from 'react'
import "../stylesheets/loginForm.css"
import LoginInput from "./LoginInput.jsx"
import LoginButton from "./LoginButton.jsx"

const LoginForm = () => {
  const [isHovered, setIsHovered] = useState(false);
  const emailRef = useRef();
  const passwordRef = useRef();
  const handleSubmit = async (event) => {
    event.preventDefault();

    const data = {
      email: emailRef.current.value,
      password: passwordRef.current.value
    };

    try {
      const response = await fetch('', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      if (!response.ok) {
        throw new Error('Error al enviar los datos');
      }

      const responseData = await response.json();
      console.log(responseData);
    } catch (error) {
      console.error(error);
    }
  }
  return (
    <div className="loginForm_container">
        <form onSubmit={handleSubmit}>
        <LoginInput type="email" text="Correo Electrónico" ref={emailRef}></LoginInput>
        <LoginInput type="password" text="Contraseña" ref={passwordRef}></LoginInput>
        
            <div className="loginForm_buttons">
                <LoginButton className="b1"  type="submit" text="Iniciar Sesión" isHovered={isHovered}/>
                <LoginButton className="b2" type="redireccion" text="Registrarse" ruta={"/register"} onMouseEnter={() => setIsHovered(true)} onMouseLeave={() => setIsHovered(false)}/>
            </div>

        </form>
    </div>
  )
}

export default LoginForm