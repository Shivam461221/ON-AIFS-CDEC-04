import { Suspense, useContext, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Home from './components/home'
import About from './components/About'
import { CountContext } from './Context/CountContext'
import Login from './components/Login'
import { Link, Route, Routes } from 'react-router-dom'
import Dashboard from './components/Dashboard'
import { UserContext } from './Context/UserContext'

function App() {
    const {user, isLoggedIn, setIsLoggedIn} = useContext(UserContext);

  return (
    <>
      <div className="container-fluid display-6">
        <div className="row">
          <div className="col-md-6">Logo</div>
          <div className="col-md-6 text-end">
            {
            (isLoggedIn) ? 
              <Link onClick={()=>setIsLoggedIn(false)} className='btn btn-primary'>Logout</Link>
             : 
              <Link to={"/login"} className='btn btn-primary'>Login</Link>
           }
          </div>
        </div>
      </div>
      <Routes>
        <Route path='/login' element={<Login/>} />
        <Route path='/dashboard' element={<Dashboard/>} />
      </Routes>
      
    </>
  )
}

export default App
