import { useState } from 'react'
import './App.css'
import Header from './Components/Header'
import Footer from './Components/Footer'
import Form from './Components/Form';

function App() {
  const [count, setCount] = useState(0);

  const increase=()=>{
    setCount(count+1);
  }

  const decrease=()=>{
    setCount(prev=>prev-1);
  }
  
  //<> React fragement  
  return (
    <>
      {/* <h1>Count: {count}</h1>
      <button onClick={increase} >Increase</button>

      <button onClick={decrease} >Decrease</button>

      <Header/> */}

      <Form/>
    </>
  )
}

export default App
