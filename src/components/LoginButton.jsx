import { useNavigate } from "react-router-dom"
import "../stylesheets/loginButton.css"
const LoginButton = ({type, text, ruta,className,isHovered, onMouseEnter, onMouseLeave }) => { 
  const style = className === 'b1' ? { opacity: isHovered ? 0 : 1 } : {};

    const navigate = useNavigate();
    const handleClick = () =>{
        if(type==="submit"){
            console.log("datos");
        }
        else{
            navigate(ruta);
        }
    }
  return (
    <button className={`button ${className}`} style={style} type={type} onClick={handleClick} onMouseEnter={onMouseEnter} onMouseLeave={onMouseLeave}>{text}</button>
  )
}

export default LoginButton