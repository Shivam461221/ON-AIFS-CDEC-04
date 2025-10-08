import { useState } from "react"

export default function Form(){
    const [username, SetUsername] = useState('');
    const [email, setEmail] = useState('');
    const [age, setAge] = useState('');
    

    //useEffect - Side Effect

    return <>
        <p>Username: {username}</p>
        <p>Email: {email}</p>
        <p>Age: {age}</p>
        <div>
            <label htmlFor="">Username</label>
            <input value={username} onChange={(event)=>SetUsername(event.target.value)} type="text" />
            <br />
            <label htmlFor="">Email</label>
            <input value={email} onChange={(event)=>setEmail(event.target.value)} type="text" />
            <br />
            <label htmlFor="">Age</label>
            <input value={age} onChange={(event)=>setAge(event.target.value)} type="text" />
            <br />

        </div>
    </>
}