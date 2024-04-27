import { memo } from 'react'
import "../stylesheets/register.css"
import RegisterForm from "./RegisterForm"
const Login = memo(() => {
  return (
    <div className='register'>
        
        <div className='register_card'>
                <h1>Registrarse</h1>
                <RegisterForm className='login_card-form'></RegisterForm>
        </div>
    </div>
    
  )
  
})
Login.displayName="hola";

export default Login