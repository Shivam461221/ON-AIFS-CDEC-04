import { useContext } from "react";
import { UserContext } from "../Context/UserContext";
import {Navigate} from 'react-router-dom'

export default function ProtectedRoute({children}){

    const {isLoggedIn} = useContext(UserContext);

    if(isLoggedIn){
        return children;
    }
    else{
        return <Navigate to={"/"} />
    }
}