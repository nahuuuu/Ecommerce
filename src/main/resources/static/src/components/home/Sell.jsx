import "../../stylesheets/sellForm.css"
import { useState } from "react"
import Input from '../Input'
import Button from "../Button"
import axios from "axios"
import TextArea from "../TextArea"
import Nav from '../Nav'
import Cookies from 'js-cookie'

const Sell = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const handleSubmit = async (event) => {
    event.preventDefault();

    const token = Cookies.get("token");

    try{
      const response = await axios.post("http://localhost:8080/api/post-one-products",{
        title:title,
        description:description,
        price:price
      },

      {
        headers: {
        "Authorization":"Bearer " + token


        }
      }


        );

      if(response.status === 200){
        console.log("funciona")
      }

    }
    catch(error){
      console.error(error);
    }

  }

  return (
    <div className='home_vender'>
        <Nav cn="nav"/>
        <div className="body_vender">
        <div className="sellForm_container">
          <h2 className="sellForm_h2"><p className="sellForm_title">Crear publicación</p></h2>
        <form className='sellForm' onSubmit={handleSubmit}>
        <Input text={"Titulo del producto"} type={"text"} l={10} onChange={e=> setTitle(e.target.value)}/>
        <TextArea l={165} text={"Descripción del producto"} onChange={e=>setDescription(e.target.value)}/>
        <Input type={"number"} text={"Precio del producto"} onChange={e=>setPrice(e.target.value)}/>
        <Button type={"submit"} text={"Publicar Producto"} buttonExpand={"buttonExpand"} buttonExpandMin={"40%"} buttonExpandMax={"60%"}/>
        </form>
        </div>
        </div>
        </div>
  )
}

export default Sell