import  { useState } from 'react'
import "../stylesheets/loginForm.css"
import LoginInput from "./LoginInput.jsx"
import LoginButton from "./LoginButton.jsx"

const LoginForm = () => {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div className="loginForm_container">
        <form action="">
        <LoginInput type="email" text="Correo Electrónico"></LoginInput>
        <LoginInput type="password" text="Contraseña"></LoginInput>
        
            <div className="loginForm_buttons">
                <LoginButton className="b1" type="submit" text="Iniciar Sesión" isHovered={isHovered}/>
                <LoginButton className="b2" type="redireccion" text="Registrarse" ruta={"/register"} onMouseEnter={() => setIsHovered(true)} onMouseLeave={() => setIsHovered(false)}/>
            </div>

        </form>
    </div>
  )
}

export default LoginForm