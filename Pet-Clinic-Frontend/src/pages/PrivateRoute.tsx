import { Navigate } from 'react-router-dom';
import { ReactNode } from 'react';
import { useAuth } from './AuthContext';

interface PrivateRouteProps {
  children: ReactNode; // Změna typu na ReactNode
}

const PrivateRoute = ({ children }: PrivateRouteProps) => {
  const { isLoggedIn } = useAuth();

  return isLoggedIn ? <>{children}</> : <Navigate to="/login" />;
};

export default PrivateRoute;