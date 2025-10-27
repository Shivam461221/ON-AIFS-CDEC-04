import axios from "axios";
import { useState } from "react"

export default function Login(){
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const login = async()=>{
        try{
            let repsosne = await axios.post('https://dummyjson.com/user/login', {username, password});

            console.log(repsosne)
            if(repsosne.status===200){
                alert('Login Successful')
            }
           
        }
        catch(error){
            console.log(error.message);
            alert('Wrong credentials')
        }
    }

    return <>
        <p className="display-6 bg-primary text-white text-center">Login Form</p>
        <div className="container">
            <div className="form-group">
                <label htmlFor="" className="form-label">Username</label>
                <input type="text" onChange={(e)=>setUsername(e.target.value)} className="form-control" />
            </div>
            <div className="form-group">
                <label htmlFor="" className="form-label">Password</label>
                <input type="password" onChange={(e)=>setPassword(e.target.value)} className="form-control" />
            </div>

            <button onClick={login} className="btn btn-primary my-3">Login</button>
        </div>
    </>
}