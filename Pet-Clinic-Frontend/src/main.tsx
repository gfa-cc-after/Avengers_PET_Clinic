import React from "react"
import ReactDOM from "react-dom/client"
import { Outlet, RouterProvider, createBrowserRouter } from "react-router-dom"
import "./index.css"
import Navbar from "./components/Navbar"
import { AuthProvider } from "./pages/AuthContext"
import PrivateRoute from "./pages/PrivateRoute"
import { routes } from "./routes"

const Root = () => (
  <>
    <Navbar />
    <Outlet />
  </>
)

const router = createBrowserRouter([
  {
    element: <Root />,
    children: routes.map(({ path, requiresAuth, element: component }) => ({
      path,
      element: requiresAuth ? (
        <PrivateRoute>{component}</PrivateRoute>
      ) : (
        component
      ),
    })),
  },
])

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </React.StrictMode>,
)
