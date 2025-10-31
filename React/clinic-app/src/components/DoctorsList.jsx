import axios from "axios";
import { useContext, useEffect, useState } from "react"
import { UserContext } from "../Context/UserContext";
import { toast, ToastContainer } from "react-toastify";

export default function DoctorsList(){
    const {token} = useContext(UserContext);
    const [doctors, setDoctors] = useState([]);

    const config = {
                headers:{
                     'Authorization':`Bearer ${token}`,
                    'Content-Type':'application/json'
                }
            }

    const getAllDoctors=async()=>{
        try{
            let response = await axios.get('http://localhost:8000/api/doctors/getAll', config);

            if(response.status===200){
                setDoctors(response.data);
            }
        }
        catch(error){
            toast.error('Doctor not loaded');
        }
    }

    const deleteDoctor=async(id)=>{
        try{
            let response = await axios.delete(`http://localhost:8000/api/doctors/delete/${id}`,config,)
            console.log(response);
            if (response.status===200) {
            toast.success("doctor deleted...");
            let index = doctorList.findIndex((obj) => obj._id === id);
            doctors.splice(index, 1);
            setDoctors([...doctors]);
        }
        }
        catch(error){
             toast.error("Oops, doctor not deleted");
        }
    }

    useEffect(()=>{
        getAllDoctors();
    }, []);

    return <>
    <ToastContainer/>
    <div className="container-fluid mt-3">
        <table className="table table-striped">
            <thead>
                <tr className="table-dark">
                    <th>S.No</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    doctors.map((doctor, index)=><tr key={index}>
                        <th>{index+1}</th>
                        <td>{doctor.firstName}</td>
                        <th>{doctor.lastName}</th>
                        <th>{doctor.phoneNumber}</th>
                        <th>{doctor.email}</th>
                        <th>{doctor.gender}</th>
                        <th>{doctor.role}</th>
                        <th><button onClick={()=>deleteDoctor(doctor._id)} className="btn btn-danger">Delete</button></th>
                    </tr>)
                }
            </tbody>
        </table>
    </div>
    </>
}