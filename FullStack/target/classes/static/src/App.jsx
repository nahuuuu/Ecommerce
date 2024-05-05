import { BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import Cookies from 'js-cookie';
import axios from "axios";
import Login from "./components/login/Login"
import Register from "./components/register/Register"
import Sell from "./components/home/Sell";
import Home from "./components/home/Home"
import "./App.css"

const PrivateRoute = /*async*/ ({ children }) => {

  const token = Cookies.get('token');
  return token ? children : <Navigate to="/login" />;
  /*
    try {
      const response = await axios.post('http://localhost:8080/auth', {
          token: token
      });

      if (response.status === 200) {
        return children
      } else if (response.status === 401) {
          return <Navigate to="/login" />;
      }
    } catch (error) {
      console.error('Error al iniciar sesión:', error);
    }
    */
};

function App() {
  return(
    <Router>
      <Routes>
        <Route path="/login" element={<Login/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/home" element={<PrivateRoute><Home/></PrivateRoute>}/>
        <Route path="/home/vender" element={<PrivateRoute><Sell/></PrivateRoute>}/>
        <Route path="/" element={<Navigate to={"/login"}/>}/>
      </Routes>
    </Router>
  )
}

export default App