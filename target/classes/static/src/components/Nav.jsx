
import "../stylesheets/button.css"
import Input from "./Input"
import HomeSvg from "../svg/HomeSvg"
import MagnifyingGlass from "../svg/MagnifyingGlass"
import ProfileSvg from "../svg/ProfileSvg"
import { Link } from "react-router-dom"
import NotificationSvg from "../svg/NotificationSvg"
import CartSvg from "../svg/CartSvg"
import HeartSvg from "../svg/HeartSvg"
import "../stylesheets/nav.css"
const Nav = ({cn}) => {
return (
    <nav className={`${cn}`}>

        <div className="nav_div" id="nav">
            <div className="nav_div_home"><div className="nav_div_a"><a  href="/home"><HomeSvg/></a></div>

                </div>
        <div className="nav_search">
            <div className="nav_search_input"><Input  type="text" /></div>

            <div className="nav_search_magnifying">
            <a href=""><MagnifyingGlass/></a>
            </div>
        </div>
            <div className="nav_buttons">
            <div className="nav_compras"><Link  to="/home"><div>Mis compras</div></Link></div>
            <div className="nav_compras"><Link  to="/home/sell"><div>Vender</div></Link></div>
                <div className="nav_div_a"><Link  to="/home"><NotificationSvg/></Link></div>
                <div className="nav_div_a"><Link  to="/home"><HeartSvg className="svg-heart" /></Link></div>
                <div className="nav_div_a"><Link  to="/home/cart"><CartSvg className="svg-cart" /></Link></div>
                <div className="nav_div_a"><Link  to="/home"><ProfileSvg/></Link></div>
            </div>
        </div>
    </nav>
)
}

export default Nav