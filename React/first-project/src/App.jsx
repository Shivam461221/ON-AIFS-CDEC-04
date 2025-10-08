import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  const getUsers = async () => {
    try {
      let response = await fetch('https://dummyjson.com/products');
      let data = await response.json();
      setProducts(data.products);

    }
    catch (error) {
      console.log(error);
    }
    finally {
      setLoading(false);
    }
  }


  useEffect(() => {
    getUsers();
  }, [])

  if (loading) return <div>Loading...</div>

  return (
    <>
      {products.map(product => <div>{product.title}</div>)}
    </>
  )
}

export default App
