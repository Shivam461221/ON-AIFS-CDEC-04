import axios from 'axios'
import { useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../Context/UserContext';

export default function Login(){
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const {setUser, setToken, setIsLoggedIn} = useContext(UserContext);

    const login =async ()=>{
        try{
            let response = await axios.post('https://dummyjson.com/user/login', {username, password});
            console.log(response);
            if(response.status==200){
                setUser(response.data.username);
                setToken(response.data.accessToken);
                setIsLoggedIn(true);
                navigate('/dashboard');
            }
        }
        catch(error){
            alert('Invaid Credential');
            console.log(error);
        }
    }

    return <>
        <div className="container">
            <p className="display-6">Login</p>
            <div>
                <div className="form-group">
                    <label htmlFor="" className="form-label">Username</label>
                    <input onChange={(e)=>setUsername(e.target.value)} type="text" className="form-control" />
                </div>
                <div className="form-group">
                    <label htmlFor="" className="form-label">Password</label>
                    <input onChange={(e)=>setPassword(e.target.value)} type="password" className="form-control" />
                </div>
                <button onClick={login} className="btn btn-primary mt-2">Login</button>
            </div>
        </div>
    </>
}