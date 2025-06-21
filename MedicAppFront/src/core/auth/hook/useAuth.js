import { useContext } from "react"
import { AuthContext } from "../context/AuthContext";

// 3-sirve para facilitar el uso del contexto
export const useAuth = ()=>{
    const {isLoggedIn,logIn,logOut} = useContext(AuthContext);

    return {isLoggedIn,logIn,logOut}
}