import { useState } from "react"

export default function Booking() {
const [formData, setFormData] = useState({
    firstName:"",
    lastName:"",
    age:"",
    phoneNumber:"",
    gender:"",
    appointmentDate:"",
    daignosis:"not done yet"
});

console.log(formData);

const handleUpdate=(e)=>{
    setFormData(prev=>({...prev, [e.target.name]:e.target.value}));
}

    return <>
        <section className="book_section layout_padding">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <form>
                            <h4>
                                BOOK <span>APPOINTMENT</span>
                            </h4>
                            <div className="form-row ">
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">First Name </label>
                                    <input onChange={(e)=>handleUpdate(e)} name="firstName" type="text" className="form-control" id="inputPatientName" placeholder="" />
                                </div>
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Last Name </label>
                                    <input onChange={(e)=>handleUpdate(e)} type="text" name="lastName" className="form-control" id="inputPatientName" placeholder="" />
                                </div>
                                 <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Age</label>
                                    <input onChange={(e)=>handleUpdate(e)} type="text" name="age" className="form-control" id="inputPatientName" placeholder="" />
                                </div>
                                                               
                            </div>
                            <div className="form-row ">
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Gender</label>
                                    <input onChange={(e)=>handleUpdate(e)} type="text" name="gender" className="form-control" id="inputPatientName" placeholder="" />
                                </div>
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Phone Number</label>
                                    <input onChange={(e)=>handleUpdate(e)} type="text" name="phoneNumber" className="form-control" id="inputPatientName" placeholder="" />
                                </div>
                                 <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Branch</label>
                                    <input onChange={(e)=>handleUpdate(e)} name="branch" type="text" className="form-control" id="inputPatientName" placeholder="" />
                                </div>                             
                            </div>
                            <div className="form-row ">
                                <div className="form-group col-lg-4">
                                    <label for="inputPatientName">Date</label>
                                    <input onChange={(e)=>handleUpdate(e)} type="text" name="appointmentDate" className="form-control" id="inputPatientName" placeholder="" />
                                </div>
                                                          
                            </div>
                            <div className="btn-box">
                                <button type="submit" className="btn ">Book</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </>
}