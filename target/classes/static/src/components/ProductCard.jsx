import { useEffect, useState } from 'react';
import { Link, Navigate } from 'react-router-dom';
import "../stylesheets/productCard.css"
import Cookies from 'js-cookie';
import CartSvg from "../svg/CartSvg"
import HeartSvg from "../svg/HeartSvg"
import axios from 'axios';
const ProductCard = () => {
  const [products, setProducts] = useState([]);
  useEffect(() => {

    const getProducts = async () =>{
      const token = Cookies.get("token");

      try{
      const response = await axios.post("http://localhost:8080/api/get-products",{ page: 0,size : 22},{

      headers: {
        Authorization: 'Bearer ${token}'
      },
   
    }
    );
    if(response.status===200){
       console.log(response.data)
      setProducts(response.data);
    }else if(response.status === 403){
      <Navigate to="/login"/>
    }
  }catch(error){
    console.error(error);
  }
  }
  getProducts();
}, []);

  return (
    <div className="l">
      {products.map((product) => (
        <div className="product_container" key={product.id}>
        <div className="product_card" >
          <div className="product_image">
          {product.image.map((base64Image, index) => (
                          <img
                              key={index}
                              src={`data:image/jpeg;base64,${base64Image}`}
                              alt={product.title}
                          />
                      ))}
          </div>
          <div className="product_info">
            <div className="product_a_container">

              <div className="product_a_b">
                <Link to={`/product/${product.id}`} className="product_a">
                  <h2 className="product_tittle_h2">{product.title}</h2>
                </Link>
                <div className="product_addCart">
                  <button className='product_buttoncartsvg product_heart'><HeartSvg className="svg-heartN"/> </button>
                  <button className='product_buttoncartsvg'><CartSvg className="svg-cartN"/> </button>
                </div>
              </div>
            </div>
            <div className="product_price">
              <div className="product_price_p"><p>${product.price}</p></div>

            </div>
          </div>
        </div>
        </div>
      ))}
</div>
  )
}

export default ProductCard