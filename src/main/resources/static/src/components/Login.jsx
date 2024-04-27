import { memo } from 'react'
import "../stylesheets/login.css"
import LoginForm from "./LoginForm"
const Login = memo(() => {
  return (
    <div className='login'>
        
        <div className='login_card'>
            <div className='login_card-left'>

            </div>
            <div className='login_card-right'>
                <h1>Iniciar Sesión</h1>
                <LoginForm className='login_card-form'></LoginForm>
                
            </div>
        </div>
    </div>
    
  )
  
})
Login.displayName="hola";

export default Login