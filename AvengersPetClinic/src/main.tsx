import React from 'react'
import ReactDOM from 'react-dom/client'
import LoginForm from './App.tsx'
import './index.css'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom"
const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginForm />,
  },
  
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <LoginForm />
  </React.StrictMode>,
)
