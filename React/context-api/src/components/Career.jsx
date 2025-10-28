import { useContext } from "react"
import { CountContext } from "../Context/CountContext"


export default function Career(){
    const {count} = useContext(CountContext);
    return <>
        <h1>Career {count} </h1>

        
    </>
}