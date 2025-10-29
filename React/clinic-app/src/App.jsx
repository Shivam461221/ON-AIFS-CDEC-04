
import './App.css'
import Header from './components/Header'
import Slider from './components/Slider'
import Booking from './components/Booking'
import About from './components/About'
import Treatment from './components/Treatment'
import Team from './components/Team'
import Client from './components/Client'
import Contact from './components/Contact'
import Footer from './components/Footer'
import { Route, Routes } from 'react-router-dom'
import Home from './Home'
import Login from './components/Login'
import AdminDashboard from './components/AdminDashboard'

function App() {


  return (
    <>
      <Header />
      <Routes>
        <Route path='/' element={<Home/> } />
        <Route path='/booking' element={<Booking/>} />
        <Route path='/about' element={<About/>} />
        <Route path='/treatment' element={<Treatment />} />
        <Route path='/team' element={<Team />} />
        <Route path='/client' element={<Client />} />
        <Route path='/contact' element={<Contact />} />
        <Route path='/login' element={<Login/>} />
        <Route path='admin-dashboard' element={<AdminDashboard/>} />
      </Routes>
      <Footer />
    </>
  )
}

export default App
