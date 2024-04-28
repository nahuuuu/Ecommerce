import { BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import Login from "./components/login/Login"
import Register from "./components/register/Register"
import Home from "./components/home/Home"
import "./App.css"

function App() {
  return(
    <Router>
      <Routes>
        <Route path="/login" element={<Login/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/home" element={<Home/>}/>
        <Route path="/" element={<Navigate to={"/login"}/>}/>
      </Routes>
    </Router>

  )
}

export default App
