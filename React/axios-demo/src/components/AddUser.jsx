import { useState } from "react"
import axios from 'axios'

export default function AddUser(){
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [age, setAge] = useState('');
    const [gender, setGender] = useState('');

    const createUser = async ()=>{
        try{
            let response = await axios.post('https://dummyjson.com/users/add', {firstName, lastName, age, gender});
            console.log(response);
            if(response){
                alert('User Created');
            }
        }

        catch(error){
            console.log(error);
        }
    }

    return<>
        <p className="display-4 bg-success text-center text-white">Add User</p>
        <div className="container">
            <div className="form-group">
                <label htmlFor="" className="form-label">First Name</label>
                <input type="text" onChange={(e)=>setFirstName(e.target.value)} className="form-control" />
            </div>
            <div className="form-group">
                <label htmlFor="" className="form-label">Last Name</label>
                <input onChange={(e)=>setLastName(e.target.ariaValueNow)} type="text" className="form-control" />
            </div>
            <div className="form-group">
                <label htmlFor="" className="form-label">Age</label>
                <input onChange={(e)=>setAge(e.target.value)} type="text" className="form-control" />
            </div>
            <div className="form-group">
                <label htmlFor="" className="form-label">Gender</label>
                <select onChange={(e)=>setGender(e.target.value)} name="" id="" className="form-control">
                    <option value="">Select</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
            </div>
            <button onClick={createUser} className="btn btn-primary my-3">Submit</button>
        </div>
    </>
}