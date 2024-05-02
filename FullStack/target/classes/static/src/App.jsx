import { BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import Cookies from 'js-cookie';
import Login from "./components/login/Login"
import Register from "./components/register/Register"
import Home from "./components/home/Home"
import "./App.css"

const PrivateRoute = ({ children }) => {
  const token = Cookies.get('token');
  return token ? children : <Navigate to="/login" />;
};

function App() {
  return(
    <Router>
      <Routes>
        <Route path="/login" element={<Login/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/home" element={<PrivateRoute><Home/></PrivateRoute>}/>
        <Route path="/" element={<Navigate to={"/login"}/>}/>
      </Routes>
    </Router>
  )
}

export default App