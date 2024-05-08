import { BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import Cookies from 'js-cookie';
import { useEffect,useState } from "react";
import axios from "axios";
import Login from "./components/login/Login"
import Register from "./components/register/Register"
import Sell from "./components/home/Sell";
import Home from "./components/home/Home"
import Cart from "./components/home/Cart";
import "./App.css"

const PrivateRoute = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(null);

  useEffect(() => {
    const checkAuthentication = async () => {
      const token = Cookies.get('token');

      try {
        const response = await axios({
          method: 'post',
          url: 'http://localhost:8080/auth',
          headers: {
            'Authorization': 'Bearer ' + token
          }
        });

        if (response.status === 200) {
          setIsAuthenticated(true);
        } else if (response.status === 401) {
          setIsAuthenticated(false);
        }
      } catch (error) {
        console.error('Error al iniciar sesión:', error);
        setIsAuthenticated(false);
      }
    };

    checkAuthentication();
  }, []);

  if (isAuthenticated === null) {
    return <div>Cargando...</div>;
  }

  return isAuthenticated ? children : <Navigate to="/login" />;
};

function App() {
  return(
    <Router>
      <Routes>
        <Route path="/login" element={<Login/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/home" element={<PrivateRoute><Home/></PrivateRoute>}/>
        <Route path="/home/sell" element={<PrivateRoute><Sell/></PrivateRoute>}/>
        <Route path="/" element={<Navigate to={"/home"}/>}/>
        <Route path="/home/cart" element={<PrivateRoute><Cart/></PrivateRoute>}/>
      </Routes>
    </Router>
  )
}

export default App