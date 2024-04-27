import "../stylesheets/registerForm.css"
import LoginInput from "./LoginInput.jsx"
import LoginButton from "./LoginButton.jsx"
import { useRef } from "react"
const LoginForm = () => {
  const userRef = useRef();
  const emailRef = useRef();
  const passwordRef = useRef();
  const handleSubmit = async (event)=>{
      event.preventDefault();
      const data = {
        user: userRef.current.value,
        email: emailRef.current.value,
        password: passwordRef.current.value
      };
      try {
        const response = await fetch('localhost:8080/post', {
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
        <LoginInput ref={userRef} type="text" text="Usuario"></LoginInput>
        <LoginInput ref={emailRef} type="email" text="Correo Electrónico"></LoginInput>
        <LoginInput ref={passwordRef} type="password" text="Contraseña"></LoginInput>
        
            <div className="registerForm_buttons">
                <LoginButton type={"submit"} text={"Completar Registro"}/>
            </div>

        </form>
    </div>
  )
}

export default LoginForm