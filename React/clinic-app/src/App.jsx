
import './App.css'
import 'react-toastify/dist/ReactToastify.css';
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
import Home from './components/Home'
import Login from './components/Login'
import Dashboard from './components/Dashboard'
import AddDoctor from './components/AddDoctor';
import DoctorsList from './components/DoctorsList';
import ProtectedRoute from './components/ProtectedRoute';
import ReceptionistList from './components/ReceptionistList';
import AddReceptionist from './components/AddReceptionist';
import PatientList from './components/PatientList';
import AddPatient from './components/AddPatient';

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
        <Route path='dashboard' element={<ProtectedRoute><Dashboard/></ProtectedRoute>} />
        <Route path='/add-doctor' element={<ProtectedRoute><AddDoctor/></ProtectedRoute>} />
        <Route path='/view-doctors' element={<ProtectedRoute><DoctorsList/></ProtectedRoute>} />
        <Route path='/receptionist-list' element={<ReceptionistList/>} />
        <Route path='/add-receptionist' element={<AddReceptionist/>} />
        <Route path='/patient-list' element={<PatientList/>} />
        <Route path='/add-patient' element={<AddPatient/>} />


      </Routes>
      <Footer />
    </>
  )
}

export default App
