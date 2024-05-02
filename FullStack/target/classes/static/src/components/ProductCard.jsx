import "../stylesheets/productCard.css"
const ProductCard = () => {
  return (
    <div className="product_container">
        <div className="product_card">
            <div className="product_image"></div>
            <div className="product_info">
                <div className="product_a_container">
                    <a href="" className="product_a">
                    <div className="product_tittle"><h2 className="product_tittle_h2">MONEDA DE DESAFÍO DIRECTO SITIO FINANCIERO Y CONTABLE DE DEFENSA DFAS COLUMBUS</h2></div>
                    </a>
                </div>
                <div className="product_price">
                    <div className="product_price_p"><p>$500</p></div>
                    <div className="product_rate">rate</div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default ProductCard