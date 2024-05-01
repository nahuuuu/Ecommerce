import Nav from "../../components/Nav"
import "../../stylesheets/home.css"
import ProductCard from "../ProductCard"
import ArrowSvg from "../../svg/ArrowSvg"
const Home = () => {
  return (
    <div className="home">
<Nav cn="nav"></Nav>
<aside className="filter-left"> </aside>
<div className="products">
    <ProductCard/>
    <ProductCard/>
    <ProductCard/>
    <ProductCard/>
    <ProductCard/>
    <ProductCard/>
</div>
<aside className="filter-right"><a href="#nav" className="arrow_container" ><ArrowSvg/></a></aside>
</div>
  )
}

export default Home