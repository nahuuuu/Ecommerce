import "../stylesheets/textArea.css"

const TextArea = ({text,l,onChange}) => {


  return (
    <div className="textArea">
        <label htmlFor="">{text}</label>
        <textarea maxLength={l} name="" id="" onChange={onChange}></textarea>
    </div>
  )
}

export default TextArea