import { useRef, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  const countRef = useRef(1);

  const nameRef = useRef();

  // const add=()=>{
  //   countRef.current++;
  //   setCount(count+1);
  //   console.log(countRef.current);
  // }

  const focus=()=>{
    nameRef.current.value = "Shivam";
  }

  return (
    <>
      <h1>Count {count}</h1>

      {/* <button onClick={add}>Add</button> */}

      <h2>CountRef {countRef.current}</h2>

      <input ref={nameRef} type="text" />

      <button onClick={focus}>Focus</button>
    </>
  )
}

export default App
