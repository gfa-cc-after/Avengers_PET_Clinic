import { createContext, useContext, useState, ReactNode } from 'react';

type AuthContextType = {
  isLoggedIn: boolean;
  token: string | null;
  login: (token: string) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(true); // must be change to false after testing of logIn with BackEnd
  const [token, setToken] = useState<string | null>(null);

  const login = (token: string) => {
    setIsLoggedIn(true);
    setToken(token);
  };
  const logout = () => {
    setIsLoggedIn(false);
    setToken(null);
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout, token }}>
      {children}
    </AuthContext.Provider>
  );
};



export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
