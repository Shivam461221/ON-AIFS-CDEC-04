import { use, useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [products, setProducts] = useState([]);

   async function getProducts(){
    try{
      let response = await fetch('https://dummyjson.com/products');
      let data = await response.json();
      setProducts(data.products);
    }
    catch(error){
      console.log(error);
    }
  }
  
  //first renders
  //component updates
  //component removes from DOM

  useEffect(()=>{
    getProducts();
  }, []);

  // useEffect(()=>{
  //   console.log('Runs everytime when components renders or rerenderds');
  // });

  // useEffect(()=>{
  //   console.log('when dependency changes');
  // },[products]);

  return (
    <>
      <div className="container-fluid bg-danger display-6 text-white text-center p-2">
        Products List
      </div>
      <div className="row">
        {
          products.map((product, index)=><div className='col-md-4 border text-center p-5'>
            <img src={product.thumbnail} />
            <h4>{product.title}</h4>
            <h3>Rs.{parseInt(product.price*80)}</h3>
            <p className='text-end'>🌟 Rating {product.rating}</p>
            <button className="btn btn-primary form-control">Buy Now</button>
            
          </div>)
        }
      </div>
    </>
  )
}

export default App
