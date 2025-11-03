import { useContext, useState } from "react";
import { UserContext } from "../Context/UserContext";
import { ToastContainer, toast } from "react-toastify";
import axios from "axios";

export default function AddPatient() {

    const { token, user } = useContext(UserContext);

    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        age: "",
        phoneNumber: "",
        gender: "",
        appointmentDate: "",
        receptionist: user._id,
        doctor: user.doctor
    });


    const handleUpdate = (e) => {
        setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
    }

    const addPatient = async (e) => {
        e.preventDefault();
        try {
            let response = await axios.post('http://localhost:8000/api/patients/register', formData, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            if (response.status === 201) {
                toast.success('Patient Added')
                setFormData({
                    firstName: "",
                    lastName: "",
                    age: "",
                    phoneNumber: "",
                    gender: "",
                    appointmentDate: ""
                });
            }


        }
        catch (error) {
            toast.error('Something went wrong')
            console.log(error);
        }
    }

    return <>
        <ToastContainer />
        <section className="book_section layout_padding">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <form>
                            <h4>
                                ADD <span>PATIENT</span>
                            </h4>
                            <div className="form-row ">
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">First Name </label>
                                    <input onChange={(e) => handleUpdate(e)} name="firstName" type="text" className="form-control" placeholder="" />
                                </div>
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Last Name </label>
                                    <input onChange={(e) => handleUpdate(e)} type="text" name="lastName" className="form-control" placeholder="" />
                                </div>
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Age</label>
                                    <input onChange={(e) => handleUpdate(e)} type="text" name="age" className="form-control" placeholder="" />
                                </div>

                            </div>
                            <div className="form-row ">
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Gender</label>
                                    <input onChange={(e) => handleUpdate(e)} type="text" name="gender" className="form-control" placeholder="" />
                                </div>
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Phone Number</label>
                                    <input onChange={(e) => handleUpdate(e)} type="text" name="phoneNumber" className="form-control" placeholder="" />
                                </div>
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Appointment Date</label>
                                    <input onChange={(e) => handleUpdate(e)} name="appointmentDate" type="date" className="form-control" placeholder="" />
                                </div>
                            </div>
                            

                            <div className="btn-box">
                                <button onClick={addPatient} type="submit" className="btn ">Add</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </>
}