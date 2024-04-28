import  { useState } from 'react'
import "../../stylesheets/loginForm.css"
import Input from "../Input.jsx"
import Button from "../Button.jsx"

const LoginForm = () => {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div className="loginForm_container">
        <form action="">
        <Input type="email" text="Correo Electrónico"></Input>
        <Input type="password" text="Contraseña"></Input>
        
            <div className="loginForm_buttons">
                <Button b="b1" buttonExpand="buttonExpand" buttonExpandMin={"25%"}  buttonExpandMax={"35%"} type="submit" text="Iniciar Sesión" isHovered={isHovered}/>
                <Button b="b2" buttonExpand="buttonExpand" buttonExpandMin={"25%"} buttonExpandMax={"35%"} type="redireccion" text="Registrarse" ruta={"/register"} onMouseEnter={() => setIsHovered(true)} onMouseLeave={() => setIsHovered(false)} />
            </div>

        </form>
    </div>
  )
}

export default LoginForm