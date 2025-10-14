import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from './components/Header';
import Home from './components/Home';
import Career from './components/Career';
import Contact from './components/Contact';
import About from "./components/About";
import Footer from './Footer';
import { Route, Routes } from 'react-router-dom';
function App() {
  

  return (
    <>
      <Header/>
      <Routes>
            <Route path='/' element={<Home/>} />
            <Route path='/about' element={<About />} />
            <Route path='/career' element={<Career />} />
            <Route path='/contact' element={<Contact />} />
        </Routes>
      
      <Footer/>
    </>
  )
}

export default App
