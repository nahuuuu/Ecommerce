
import "../stylesheets/button.css"
import Input from "./Input"
import HomeSvg from "../svg/HomeSvg"
import MagnifyingGlass from "../svg/MagnifyingGlass"
import ProfileSvg from "../svg/ProfileSvg"
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
                <div className="nav_compras"><a  href="/home"><div>Mis compras</div></a></div>
                <div className="nav_div_a"><a  href="/home"><NotificationSvg/></a></div>
                <div className="nav_div_a"><a  href="/home"><HeartSvg/></a></div>
                <div className="nav_div_a"><a  href="/home"><CartSvg/></a></div>
                <div className="nav_div_a"><a  href="/home"><ProfileSvg/></a></div>
            </div>
        </div>
    </nav>
)
}

export default Nav