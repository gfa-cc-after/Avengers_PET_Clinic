import React from 'react'
import ReactDOM from 'react-dom/client'
import {
  createBrowserRouter,
  RouterProvider,
  Outlet
} from "react-router-dom"
import './index.css'
import { AuthProvider } from './pages/AuthContext';
import Navbar from "./components/Navbar"
import { routes } from "./routes"
import PrivateRoute from './pages/PrivateRoute';


const Root = () => (
  <>
    <Navbar />
    <Outlet />
  </>
);


const router = createBrowserRouter([
  {
    element: <Root />,
    children: routes.map(({ path, requiresAuth, component }) => ({
      path,
      element: requiresAuth ? (
        <PrivateRoute>
          {component}
        </PrivateRoute>
      ) : (
        component
      ),
    })),
  },
]);



ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </React.StrictMode>,
)
