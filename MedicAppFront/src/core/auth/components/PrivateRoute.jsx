import React from 'react'
import { useAuth } from '../hook/useAuth'
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ children }) => {
    const { isLoggedIn } = useAuth();
    // si esta logueado entonces muestra el children
    console.log(isLoggedIn)
    // if (isLoggedIn) 
    return  children 
    // si no esta logueado redirecciona
    return <>
    <Navigate to={"/login"}></Navigate>
    </>
}

export default PrivateRoute