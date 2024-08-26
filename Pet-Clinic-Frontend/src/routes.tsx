import Landing from "./pages/Landing";
import Registration from "./pages/Registration";
import Login from "./pages/Login";
import ProfileEdit from "./pages/ProfileEdit";
import { RouteObject } from "react-router-dom";
import { Pets } from "./pages/Pets";

type PrivateRouter = RouteObject & {
  requiresAuth: boolean;
  text: string;
  path: string;
};

export const routes: PrivateRouter[] = [
  { text: "Landing", path: "/", requiresAuth: false, element: <Landing /> },
  {
    text: "Registration",
    path: "/registration",
    requiresAuth: false,
    element: <Registration />,
  },
  { text: "Login", path: "/login", requiresAuth: false, element: <Login /> },
  {
    text: "Edit Profile",
    path: "/profile-edit",
    requiresAuth: true,
    element: <ProfileEdit />,
  },
  {
    text: "Pets",
    path: "/pets",
    requiresAuth: true,
    element: <Pets />,
  },
];
