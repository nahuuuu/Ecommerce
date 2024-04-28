import { useNavigate } from "react-router-dom"
import { useState } from "react";
import "../stylesheets/button.css"
const Button = ({type, text, ruta,b,isHovered,buttonExpand,buttonExpandMin,buttonExpandMax,onMouseLeave,onMouseEnter }) => { 
  const style = b === 'b1' ? { opacity: isHovered ? 0 : 1 } : {};
  let buttonStyle = {};
/*se usa onMouseEnter && onMouseEnter(); y handleMouseEnter porque al
usar solo onmouseenter o leave se le cambia el valor de este y ya no sirve para el is hovered
al usar otra variable handlemouseenter hacemos que la que pasamos como prop no cambie
en  onMouseEnter && onMouseEnter(); estamos verificando que la funcion no este vacia 
y al verificarlo ejecutamos la funcion pasandola como prop*/
  const [buttonWidth, setButtonWidth] = useState(buttonExpandMin);

  const handleMouseEnter = () => {
    if(buttonExpand==="buttonExpand"){
      setButtonWidth(buttonExpandMax);
    }
    onMouseEnter && onMouseEnter();
  };

  const handleMouseLeave = () => {
    if(buttonExpand==="buttonExpand"){
      setButtonWidth(buttonExpandMin);
    }
    onMouseLeave && onMouseLeave();
  };

  if(buttonExpand==="buttonExpand"){
    buttonStyle = {
      transition: '1s',
      width: buttonWidth,
    };
  }

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
    <button 
      className={`${buttonExpand} ${b}`} 
      style={{...style,...buttonStyle}} 
      type={type} 
      onClick={handleClick} 
      onMouseEnter={handleMouseEnter} 
      onMouseLeave={handleMouseLeave}
    >
      {text}
    </button>
  )
}

export default Button