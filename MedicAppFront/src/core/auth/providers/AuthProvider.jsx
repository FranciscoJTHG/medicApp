// 2- este componente contiene la logica que se va a compartir de la autentificacion
// su funcion es parecida al Layout

import { useEffect, useState } from "react"
import { AuthContext } from "../context/AuthContext"

export const AUTH_KEY = "isLoggedIn";

export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    // manejo de sesion
    const saveLoginState = (state) => sessionStorage.setItem(AUTH_KEY, state);
    const getLoginState = () => sessionStorage.getItem(AUTH_KEY);
    const removeLoginState = () => sessionStorage.removeItem(AUTH_KEY)

    // reviso sesion storage - se ejecuta 1 sola vez
    useEffect(() => {
        // si no esta logueado entonces sale
        // si esta logueado entonces actualiza estado
        const initAuth = async () => {
            const loginState = await getLoginState();
            console.log("auth:"+loginState)
            if (!loginState) return;
            setIsLoggedIn(loginState);
        }
        initAuth();
    }, []);

    const logIn = async () => {
        // aqui se puede agregar la logica para iniciar la sesion
        setIsLoggedIn(true)
       await saveLoginState(true);
    }
    const logOut = async () => {
        // aqui se puede agregar la logica para cerrar la sesion
        setIsLoggedIn(false)
       await removeLoginState();
    }
    return (
        <>
            <AuthContext.Provider value={{
                // aqui se va a compartir la logica de autenticacion
                isLoggedIn: isLoggedIn,
                logIn: logIn,
                logOut: logOut,
            }}>
                {children}
            </AuthContext.Provider>
        </>
    )
}