import axios from "axios";
import { useContext, useEffect, useState } from "react"
import { UserContext } from "../Context/UserContext";
import { toast, ToastContainer } from "react-toastify";

export default function PatientList() {
    const { token, user } = useContext(UserContext);
    const [patients, setPatients] = useState([]);

    const config = {
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }

    const getAllPatients = async () => {
        try {
            let response = await axios.get('http://localhost:8000/api/patients/all', config);

            if (response.status === 200) {
                setPatients(response.data);
            }
        }
        catch (error) {
            toast.error('Patient not loaded');
        }
    }

    const deletePatient = async (id) => {
        try {
            let response = await axios.delete(`http://localhost:8000/api/patients/delete/patients/${id}`, config,)
            console.log(response);
            if (response) {
                toast.success("Patient deleted...");
                let index = patients.findIndex((obj) => obj._id === id);
                patients.splice(index, 1);
                setPatients([...patients]);
            }
        }
        catch (error) {
            toast.error("Oops, patient not deleted");
        }
    }

    useEffect(() => {
        getAllPatients();
    }, []);

    return <>
        <ToastContainer />
        <div className="container-fluid mt-3">
            <table className="table table-striped">
                <thead>
                    <tr className="table-dark">
                        <th>S.No</th>
                        <th>Full Name</th>
                        <th>Age</th>
                        <th>Phone</th>
                        <th>Appointment Date</th>
                        <th>Gender</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        patients.filter(patients=>patients.receptionist===user._id).map((patient, index) => <tr key={index}>
                            <th>{index + 1}</th>
                            <td>{patient.firstName +" "+patient.lastName}</td>
                            <th>{patient.age}</th>
                            <th>{patient.phoneNumber}</th>
                            <th>{patient.appointmentDate}</th>
                            <th>{patient.gender}</th>
                            <th><button onClick={() => deletePatient(patient._id)} className="btn btn-danger">Delete</button></th>
                        </tr>)
                    }
                </tbody>
            </table>
        </div>
    </>
}