import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

interface PrivateProps {
    element: React.ReactElement;
}

export const PrivateRoute: React.FC<PrivateProps> = ({ element }) => {
    const { authToken } = useAuth();


    return authToken? element: <Navigate to="/login" />

};