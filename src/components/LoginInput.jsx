import { useEffect, useRef, useState } from 'react';
import "../stylesheets/loginInput.css"

const LoginInput = ({type, text, name}) => {
  const [error, setError] = useState('');
  const inputRef = useRef();

  useEffect(() => {
    const handleInvalid = (event) => {
      event.preventDefault();
      if (!event.target.validity.valid) {
        if(event.target.validity.valueMissing){
          setError('Este campo es obligatorio.');
        }
        
        if(event.target.validity.typeMismatch){
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
      <input type={type} name={name} id={type} required minLength={type === 'password' ? 8 : undefined}  ref={inputRef} />
      {error && <p className='error'>{error}</p>}
    </div>
  );
};

export default LoginInput;