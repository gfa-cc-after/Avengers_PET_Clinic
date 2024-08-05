import React from 'react'
import ReactDOM from 'react-dom/client'
import {
  createBrowserRouter,
  RouterProvider,
  Outlet
} from "react-router-dom"
import './index.css'
import Registration from './pages/Registration';
import Login from './pages/Login';
import Landing from './pages/Landing';
import { AuthProvider } from './pages/AuthContext';
import Navbar from "./components/Navbar"
import { routes } from "./routes"

const Root = () => (
  <>
    <Navbar />
    <Outlet />
  </>
);


const router = createBrowserRouter([
  {
    element: <Root />,
    children: [
      {
        path: routes[0].path,
        element: <Landing />,
      },
      {
        path: routes[1].path,
        element: <Registration />,
      },
      {
        path: routes[2].path,
        element: <Login />,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </React.StrictMode>,
)
