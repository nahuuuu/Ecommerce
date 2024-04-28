import "../../stylesheets/registerForm.css"
import Input from "../Input.jsx"
import Button from "../Button.jsx"
const LoginForm = () => {
  
  return (
    <div className="loginForm_container">
        <form action="">
        <Input type="text" text="Usuario"></Input>
        <Input type="email" text="Correo Electrónico"></Input>
        <Input type="password" text="Contraseña"></Input>
        
            <div className="registerForm_buttons">
                <Button buttonExpand={"buttonExpand"} buttonExpandMin={"30%"} buttonExpandMax={"40%"} type={"submit"} text={"Completar Registro"}/>
            </div>

        </form>
    </div>
  )
}

export default LoginForm