import { useState } from "react"
import { UserContext } from "./UserContext"

export const UserProvider=({children})=>{
    const [token, setToken]  = useState('');
    const [user, setUser] = useState({});
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    return <UserContext.Provider value={{token, setToken, user, setUser, isLoggedIn, setIsLoggedIn}} >
        {children}
    </UserContext.Provider>
}