import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import axios from 'axios'
import AddUser from './components/AddUser'
import 'bootstrap/dist/css/bootstrap.min.css/'
import Login from './components/Login'

function App() {
  
  const [users, setUsers] = useState([]);

  const getUsers = async ()=>{
    try{
      let response = await axios.get('https://dummyjson.com/users');
      setUsers(response.data.users);
    }
    catch(error){
      console.log(error);
    }
  }

  useEffect(()=>{
    getUsers();
  }, [])

  return (
    <>
     <AddUser/>
     <ul>
      {
        users.map((user, index)=><li key={index} >ID: {user.id} and Name: {user.firstName}</li>)
      }
     </ul>

      <Login/>
    
    </>
  )
}

export default App
