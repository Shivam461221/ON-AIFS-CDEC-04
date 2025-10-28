import { useState } from "react"
import { CountContext } from "./CountContext";

export const CountProvider = ({children}) =>{
    const [count, setCount] = useState(0);

    const add = () =>{
        setCount(count+1);
    }

    const substract = () =>{
        setCount(count-1);
    }

    return <CountContext.Provider value={{count, add, substract}} >
        {children}
    </CountContext.Provider>
}