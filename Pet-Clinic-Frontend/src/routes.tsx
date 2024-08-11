/* export const routes = [
	{ id: 1, text: 'Landing', path: '/',requiresAuth: false },
	{ id: 2, text: 'Registration', path: '/registration',requiresAuth: false },
	{ id: 3, text: 'Login', path: '/login',requiresAuth: false },
	{ id: 4, text: 'Edit Profile', path: '/profile-edit',requiresAuth: true }
  ]; */

import Landing from './pages/Landing';
import Registration from './pages/Registration';
import Login from './pages/Login';
import ProfileEdit from './pages/ProfileEdit';

export const routes = [
  { id: 1, text: 'Landing', path: '/', requiresAuth: false, component: <Landing /> },
  { id: 2, text: 'Registration', path: '/registration', requiresAuth: false, component: <Registration /> },
  { id: 3, text: 'Login', path: '/login', requiresAuth: false, component: <Login /> },
  { id: 4, text: 'Edit Profile', path: '/profile-edit', requiresAuth: true, component: <ProfileEdit /> }
];