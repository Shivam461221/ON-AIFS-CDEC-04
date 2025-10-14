import { Link } from "react-router-dom";

export default function Header(){

    return <>
        <div className="container-fluid d-flex p-3 display-6 bg-success text-white justify-content-between">
            <div className="h1 text-dark">CLOUDBLITZ</div>
            <div className="d-flex">
                <div className="me-5">
                    <Link className="text-decoration-none text-white" to={'/'} >Home</Link>
                </div>
                <div className="me-5">
                    <Link className="text-decoration-none text-white" to={'/about'} >About</Link>
                </div>
                <div className="me-5">
                    <Link className="text-decoration-none text-white" to={'/career'} >Career</Link>
                </div>
                <div className="me-5">
                    <Link className="text-decoration-none text-white" to={'/contact'} >Contact</Link>
                </div>
            </div>
        </div>
    </>
}