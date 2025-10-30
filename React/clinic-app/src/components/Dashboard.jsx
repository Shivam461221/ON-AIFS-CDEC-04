import { useContext, useEffect } from "react"
import { UserContext } from "../Context/UserContext"

export default function Dashboard(){
    const {user} = useContext(UserContext);

    

    return <>
        <section className="book_section layout_padding">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <form>
                            <h4>
                                YOUR <span>PROFILE</span>
                            </h4>
                            <div className="form-row ">
                                <div className="form-group col-lg-4">
                                    <label >First Name </label>
                                    <input value={user.firstName} type="text" className="form-control" readOnly />
                                </div>
                                <div className="form-group col-lg-4">
                                    <label >Last Name </label>
                                    <input value={user.lastName} type="text" className="form-control" readOnly />
                                </div>
                                <div className="form-group col-lg-4">
                                    <label >Email</label>
                                    <input value={user.email} type="text" className="form-control" readOnly />
                                </div>
                                
                            </div>
                            <div className="form-row ">
                                <div className="form-group col-lg-4">
                                    <label >Phone Number </label>
                                    <input value={user.phoneNumber} type="text" className="form-control" readOnly />
                                </div>
                                <div className="form-group col-lg-4">
                                    <label >Gender</label>
                                    <input value={user.gender} type="text" className="form-control" readOnly />
                                </div>
                                
                            </div>
                            <div className="btn-box">
                                <button type="submit" className="btn ">Update Profile</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </>
}