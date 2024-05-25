
import Nav from '../Nav'
import { useState } from 'react'
import ProductCart  from "../ProductCart"
import "../../stylesheets/cart.css"
const Cart = () => {
  const [totalPrice, setTotalPrice] = useState(0);

  // Función para actualizar el precio total desde el componente hijo
  const handleTotalChange = (newTotal) => {
    setTotalPrice(newTotal);
  };

  return (
    <div className='home_cart'>
      <Nav cn={"nav"}/>
      <div className="home_cart_body">
        <div className="home_cart_body_products">
          <ProductCart onTotalChange={handleTotalChange} />
        </div>
        <div className="home_cart_body_price">
          <h2 className='home_finalPrice'>Total: ${totalPrice.toFixed(2)}</h2>
          <button className='button_cart'><p>FINALIZAR EL PAGO</p></button>
        </div>
      </div>
    </div>
  );
}

export default Cart;