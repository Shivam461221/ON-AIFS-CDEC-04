import { useState } from "react";

function Header(){
    const [x, setX] = useState(0);
    

    return (
        <>
            <h3>X: {x}</h3>
            <button onClick={()=>setX(prev=>prev+1)}>Add</button>
        </>
    )
}

export default Header;