import axios from "axios";
import { useContext, useEffect, useState } from "react"
import { UserContext } from "../Context/UserContext";
import { toast, ToastContainer } from "react-toastify";

export default function ReceptionistList() {
    const { token, user } = useContext(UserContext);
    const [receptionists, setReceptionists] = useState([]);

    const config = {
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }

    const getAllReceptionist = async () => {
        try {
            let response = await axios.get('http://localhost:8000/api/receptionist/all', config);

            if (response.status === 200) {
                setReceptionists(response.data);
            }
        }
        catch (error) {
            toast.error('Doctor not loaded');
        }
    }

    const deleteReceptionist = async (id) => {
        try {
            let response = await axios.delete(`http://localhost:8000/api/receptionist/delete/${id}`, config,)
            console.log(response);
            if (response) {
                toast.success("doctor deleted...");
                let index = receptionists.findIndex((obj) => obj._id === id);
                receptionists.splice(index, 1);
                setReceptionists([...receptionists]);
            }
        }
        catch (error) {
            toast.error("Oops, doctor not deleted");
        }
    }

    useEffect(() => {
        getAllReceptionist();
    }, []);

    return <>
        <ToastContainer />
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
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        receptionists.filter(receptionist=>receptionist.doctor===user._id).map((receptionist, index) => <tr key={index}>
                            <th>{index + 1}</th>
                            <td>{receptionist.firstName}</td>
                            <th>{receptionist.lastName}</th>
                            <th>{receptionist.phoneNumber}</th>
                            <th>{receptionist.email}</th>
                            <th>{receptionist.gender}</th>
                            <th><button onClick={() => deleteReceptionist(receptionist._id)} className="btn btn-danger">Delete</button></th>
                        </tr>)
                    }
                </tbody>
            </table>
        </div>
    </>
}