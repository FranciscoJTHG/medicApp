import React from 'react'
import { useAuth } from '../hook/useAuth'
import { Navigate } from 'react-router-dom';

const PublicRoute = ({ children }) => {
    const { isLoggedIn } = useAuth();

    if (isLoggedIn)
        return <Navigate to={"/"} ></Navigate>
    return children;
}

export default PublicRoute