import { type ReactNode, createContext, useState } from "react"

type AuthContextType = {
  isLoggedIn: boolean
  token: string | null
  login: (token: string) => void
  logout: () => void
}

const AuthContext = createContext<AuthContextType | undefined>(undefined)

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(true) // must be change to false after testing of logIn with BackEnd
  const [token, setToken] = useState<string | null>(null)

  const login = (token: string) => {
    setIsLoggedIn(true)
    setToken(token)
  }
  const logout = () => {
    setIsLoggedIn(false)
    setToken(null)
  }

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout, token }}>
      {children}
    </AuthContext.Provider>
  )
}

export { AuthContext }
