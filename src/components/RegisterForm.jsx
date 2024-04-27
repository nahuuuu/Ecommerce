import "../stylesheets/registerForm.css"
import LoginInput from "./LoginInput.jsx"
import LoginButton from "./LoginButton.jsx"
const LoginForm = () => {
  
  return (
    <div className="loginForm_container">
        <form action="">
        <LoginInput type="text" text="Usuario"></LoginInput>
        <LoginInput type="email" text="Correo Electrónico"></LoginInput>
        <LoginInput type="password" text="Contraseña"></LoginInput>
        
            <div className="registerForm_buttons">
                <LoginButton type={"submit"} text={"Completar Registro"}/>
            </div>

        </form>
    </div>
  )
}

export default LoginForm