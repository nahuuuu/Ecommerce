import { useEffect,useState } from "react";
import Cookies from "js-cookie"
import axios from "axios";
import "../stylesheets/productCart.css"
import CartDelete from "../svg/CartDelete";
import CartAdd from "../svg/CartAdd";
import CartDecrease from "../svg/CartDecrease";
import Star from "../components/Star"

const ProductCart = ({onTotalChange }) => {
    const [products, setProducts] = useState([]);
    useEffect(() => {
        const getProducts = async () => {
    const token = Cookies.get("token");
    try {
      // Asegúrate de pasar la URL directamente a axios.get, no dentro de un objeto
      const response = await axios.get('http://localhost:8080/api/get-products', {
        headers: {
          'Authorization': 'Bearer ' + token
        }
      });
      if (response.status === 200) {
        setProducts(response.data); 
      }
    } catch (error) {
      console.error(error);
    }
  };
  getProducts();
    }, []);
    const handleClickIncrease = async (product) => {
      const token = Cookies.get("token");
      try {
        const response = await axios.post('http://localhost:8080/api/update-cart', {
          count: product.count + 1
        }, {
          headers: {
            "Authorization": "Bearer " + token
          }
        });
        if (response.status === 200) {
          const updatedProducts = products.map(p => {
            if (p.id === product.id) {
              // Actualiza el count del producto específico
              return { ...p, count: p.count + 1 };
            }
            return p;
          });
          setProducts(updatedProducts);
        }
      } catch (error) {
        console.error(error);
      }
    };
    
    const handleClickDecrease = async (product) => {
      const token = Cookies.get("token");
      try {
        const response = await axios.post('http://localhost:8080/api/update-cart', {
          count: product.count - 1
        }, {
          headers: {
            "Authorization": "Bearer " + token
          }
        });
        if (response.status === 200) {
          const updatedProducts = products.map(p => {
            if (p.id === product.id) {
              // Actualiza el count del producto específico
              return { ...p, count: p.count - 1 };
            }
            return p;
          });
          setProducts(updatedProducts);
        }
      } catch (error) {
        console.error(error);
      }
    };
    const handleClickEliminate = async(product)=>{
      const token = Cookies.get("token");
      try {
        const response = await axios.delete('http://localhost:8080/api/update-product', {
          headers: {
            "Authorization": "Bearer " + token
          },
          data:{
            id: product.id
          }
        });
        if (response.status === 200) {
          const updatedProducts = products.filter(p => p.id !== product.id);
          setProducts(updatedProducts);
        }
    }
    catch(error){
      console.error(error);
    }
  }
  const totalPrice = products.reduce((total, product) => {
    return total + (product.price * 1);
  }, 0);
  useEffect(() => {
    // Llama a la función 'onTotalChange' con el nuevo precio total
    onTotalChange(totalPrice);
  }, [totalPrice, onTotalChange]);
    return (
      <div className="l">
        <h1 className="cart_tittle">MI CARRITO DE COMPRAS</h1>
        {products.map((product) => (
          <div className="product_container_c" key={product.id}>
          <div className="product_card_c" >
            <div className="product_image_c"></div>
            <div className="product_info_c">
              <div className="product_a_container_c">
                <a href="" className="product_a_c">
                  <div className="product_tittle_c">
                    <h2 className="product_tittle_h2_c">{product.title}</h2>
                  </div>
                </a>
              </div>
              <div className="product_price_c">
              <div className="product_rate_c"><p>{product.rating}</p><Star value={product.rating}/></div>
                <div className="product_amount_container_c">
                    <button className={"product_button"} onClick={()=>handleClickDecrease(product)}><CartDecrease/></button>
                    <div className="product_count"><p>0</p></div>
                    <button  className={"product_button"} onClick={()=>handleClickIncrease(product)}><CartAdd/></button>
                </div>
                <div className="product_price_p_c"><p>${product.price}</p>
                </div>
                <div className="product_eliminate "><button className="product_button" onClick={()=>handleClickEliminate(product)}><CartDelete/></button></div>
              </div>
            </div>
          </div>
          </div>
        ))}
  </div>
    )
}
export default ProductCart