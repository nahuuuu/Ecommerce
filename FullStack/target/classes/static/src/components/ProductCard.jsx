import { useEffect, useState } from 'react';
import "../stylesheets/productCard.css"
import CartDelete from '../svg/CartDelete';
const ProductCard = () => {
  const [products, setProducts] = useState([]);
  useEffect(() => {
    fetch('http://localhost:8080/api/get-products')
      .then(response => {
        if (!response.ok) {
          throw new Error('Error al obtener los productos');
        }
        return response.json();
      })
      .then(data => setProducts(data))
      .catch(error => {
        console.error('Error:', error);
      });
  }, []);

  return (
    <div className="l">
      {products.map((product) => (
        <div className="product_container" key={product.id}>
        <div className="product_card" >
          <div className="product_image"></div>
          <div className="product_info">
            <div className="product_a_container">
              
              <a href="" className="product_a">
                <div className="product_tittle">
                  <h2 className="product_tittle_h2">{product.title}</h2>
                </div>
                <div className="product_addCart"><button className='product_buttoncartsvg'><CartDelete/> </button><button className='product_buttoncartsvg'><CartDelete/> </button></div>
              </a>
            </div>
            <div className="product_price">
              <div className="product_price_p"><p>${product.price}</p></div>
              <div className="product_rate">{product.rating}</div>
            </div>
          </div>
        </div>
        </div>
      ))}
</div>
  )
}

export default ProductCard