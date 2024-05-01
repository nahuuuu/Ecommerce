import { useEffect, useRef, useState } from 'react';
import "../stylesheets/loginInput.css"

const Input = ({type, text, name, className}) => {
  const [error, setError] = useState('');
  const [opacity, setOpacity] = useState(0);
  const  inputRef = useRef();

  let pattern;
  if(type === "email"){
    pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  }

  const handleBlur = (event) => {
    if (type === "email" && !new RegExp(pattern).test(event.target.value)) {
      setOpacity(0);
      setError('Este campo tiene que ser un correo electrónico.');
      event.target.setCustomValidity('Este campo tiene que ser un correo electrónico.');
    } else {
      setError('');
      event.target.setCustomValidity('');
    }
  };

  useEffect(() => {
    const handleInvalid = (event) => {
      event.preventDefault();
      setOpacity(1);
      if (!event.target.validity.valid) {
        if(event.target.validity.valueMissing){
          setError('Este campo es obligatorio.');
        }
        
        if(event.target.validity.typeMismatch ){
          setError('Este campo tiene que ser un correo electrónico.');
        }
        if(event.target.validity.tooShort){
          setError('La contraseña debe tener al menos 8 caracteres.');
        }
      }
    };

    const handleInput = (event) => {
      event.target.setCustomValidity('');
      setError('');
    };
    const inputElement = inputRef.current;
    inputElement.addEventListener('invalid', handleInvalid);
    inputElement.addEventListener('input', handleInput);
    
    // Limpiar los event listeners cuando el componente se desmonte
    return () => {
      inputElement.removeEventListener('invalid', handleInvalid);
      inputElement.removeEventListener('input', handleInput);
    };
  }, []);

  return (
    <div className="input">
      <label htmlFor={type}>{text}</label>
      <input type={type} name={name} id={type} className={className} required minLength={type === 'password' ? 8 : undefined} onBlur={handleBlur} ref={inputRef} />
      {error && <p style={{opacity: opacity}} className='error'>{error}</p>}
    </div>
  );
};

export default Input;
