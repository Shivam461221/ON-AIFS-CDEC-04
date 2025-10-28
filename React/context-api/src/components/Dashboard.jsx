import { useContext } from "react"
import { UserContext } from "../Context/UserContext"

export default function Dashboard(){
    const {user} = useContext(UserContext);
    return <>
        <h1 className="text-capitalize">{user} Dashboard </h1>
    </>
}