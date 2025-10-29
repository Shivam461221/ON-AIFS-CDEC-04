import { useContext, useState } from "react"
import axios from 'axios'
import { useNavigate } from "react-router-dom";
import { UserContext } from "../Context/UserContext";

export default function Login(){
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const {setToken , setIsLoggedIn, setUser} = useContext(UserContext);

    const login= async(e)=>{
        e.preventDefault();
        try{
            let response = await axios.post('http://localhost:8000/api/auth/login', {email, password});
            console.log(response);
            if(response.data.token){
                setToken(response.data.token);
                setUser(response.data.user);
                setIsLoggedIn(true);
                navigate('/admin-dashboard');
            }
        }
        catch(error){
            console.log(error);
            alert('Wrong Credentials');
        }
    }

    return <>
        <section className="book_section layout_padding">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <form>
                            <h4>
                                LOG <span>IN</span>
                            </h4>
                            <div className="form-row ">
                                <div className="form-group col-lg-6">
                                    <label for="inputEmail">Email</label>
                                    <input onChange={(e)=>setEmail(e.target.value)} type="text" className="form-control" id="inputEmail" placeholder="" />
                                </div>
                                <div className="form-group col-lg-6">
                                    <label for="inputPassword">Password</label>
                                    <input onChange={(e)=>setPassword(e.target.value)} type="password" className="form-control" id="inputPassword" placeholder="" />
                                </div>
                            </div>
                           
                            <div className="btn-box">
                                <button onClick={(e)=>login(e)} type="submit" className="btn ">Login</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </>
}