import { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { UserContext } from "../Context/UserContext";

export default function Header() {
    const { user, token, setIsLoggedIn, isLoggedIn, setToken, setUser } = useContext(UserContext);
    const navigate = useNavigate();

    const logout = () => {
        setIsLoggedIn(false);
        setToken('');
        setUser({});
        navigate('/');
    }

    return <>
        <header className="header_section">
            <div className="header_top">
                <div className="container">
                    <div className="contact_nav">
                        <a href="">
                            <i className="fa fa-phone" aria-hidden="true"></i>
                            <span>
                                Call : +01 123455678990
                            </span>
                        </a>
                        <Link></Link>
                        <a href="">
                            <i className="fa fa-envelope" aria-hidden="true"></i>
                            <span>
                                Email : demo@gmail.com
                            </span>
                        </a>
                        <a href="">
                            <i className="fa fa-map-marker" aria-hidden="true"></i>
                            <span>
                                Location
                            </span>
                        </a>
                    </div>
                </div>
            </div>
            <div className="header_bottom">
                <div className="container-fluid">
                    <nav className="navbar navbar-expand-lg custom_nav-container ">
                        <a className="navbar-brand" href="index.html">
                            <img src="images/logo.png" alt="" />
                        </a>
                        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span className=""> </span>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <div className="d-flex mr-auto flex-column flex-lg-row align-items-center">
                                
                                {
                                    (isLoggedIn && user.role === 'admin')
                                        ?
                                        <ul className="navbar-nav  ">
                                            <li className="nav-item active">
                                                <Link to={'/dashboard'} className="nav-link">Dashboard <span className="sr-only">(current)</span></Link>
                                            </li>
                                            <li className="nav-item active">
                                                <Link to={'/add-doctor'} className="nav-link">Add Doctor <span className="sr-only">(current)</span></Link>
                                            </li>
                                            <li className="nav-item active">
                                                <Link to={'/view-doctors'} className="nav-link">View Doctor <span className="sr-only">(current)</span></Link>
                                            </li>
                                            
                                        </ul>
                                        :
                                        (isLoggedIn && user.role === 'doctor')
                                            ?
                                            <ul className="navbar-nav  ">
                                                <li className="nav-item active">
                                                    <Link to={'/dashboard'} className="nav-link">Dashboard <span className="sr-only">(current)</span></Link>
                                                </li>
                                                <li className="nav-item active">
                                                    <Link to={'/receptionist-list'} className="nav-link">Receptionist List<span className="sr-only">(current)</span></Link>
                                                </li>
                                                <li className="nav-item active">
                                                    <Link to={'/add-receptionist'} className="nav-link">Add Receptionist <span className="sr-only">(current)</span></Link>
                                                </li>
                                            </ul>
                                            :
                                            (isLoggedIn && user.role === 'receptionist')
                                                ?
                                                <ul className="navbar-nav  ">
                                                    <li className="nav-item active">
                                                        <Link to={'/'} className="nav-link">Dashboard <span className="sr-only">(current)</span></Link>
                                                    </li>
                                                    <li className="nav-item active">
                                                        <Link to={'/add-patient'} className="nav-link">Add Patient<span className="sr-only">(current)</span></Link>
                                                    </li>
                                                    <li className="nav-item active">
                                                        <Link to={'/patient-list'} className="nav-link">Patient List<span className="sr-only">(current)</span></Link>
                                                    </li>

                                                </ul>
                                                :
                                                <ul className="navbar-nav  ">
                                                    <li className="nav-item active">
                                                        <Link to={'/'} className="nav-link">Home <span className="sr-only">(current)</span></Link>
                                                    </li>
                                                    <li className="nav-item">
                                                        <Link to={'/about'} className="nav-link">About <span className="sr-only">(current)</span></Link>
                                                    </li>
                                                    <li className="nav-item">
                                                        <Link to={'/treatment'} className="nav-link">Treatment <span className="sr-only">(current)</span></Link>
                                                    </li>
                                                    <li className="nav-item">
                                                        <Link to={'/team'} className="nav-link">Doctors <span className="sr-only">(current)</span></Link>
                                                    </li>

                                                    <li className="nav-item">
                                                        <Link to={'/contact'} className="nav-link">Contact Us <span className="sr-only">(current)</span></Link>
                                                    </li>

                                                </ul>

                                }
                            </div>
                            <div className="quote_btn-container">

                                {
                                    (isLoggedIn) ?

                                        <button className="btn btn-outline-light" onClick={logout}><i className="fa fa-user" aria-hidden="true"></i><span>
                                            Logout
                                        </span></button>
                                        :
                                        <Link className="btn btn-outline-secondary" to={'/login'}>  <i className="fa fa-user" aria-hidden="true"></i>
                                            <span>
                                                Login
                                            </span>
                                        </Link>

                                }


                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </header>
    </>
}