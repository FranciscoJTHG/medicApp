import {createBrowserRouter} from 'react-router-dom'
import HomeView from '../../features/home/view/HomeView';
import LoginView from '../../features/login/view/LoginView';
import PacienteView from '../../features/paciente/view/PacienteView';
import MedicoView from '../../features/medico/view/MedicoView';
import VideollamadaView from '../../features/videollamada/view/VideollamadaView';
import AgendarCita from '../../features/agendarCita/view/AgendarCita';
import Ayuda from '../../features/ayuda/view/Ayuda';
import Especialidades from '../../features/especialidades/view/Especialidades';
import Precios from '../../features/precios/view/Precios';
import Registrarse from '../../features/registrarse/view/Registrarse';
import Servicios from '../../features/servicios/view/Servicios';
import Admin from '../../features/administrador/view/Admin';
import PrivateRoute from '../auth/components/PrivateRoute';
//import PublicRoute from '../auth/components/publicRoute';
import Layout from '../layouts/Layout';
import TurnosView from '../../features/medico/view/TurnosView';
import VIdeollamadaMedico from '../../features/videollamada/view/VIdeollamadaMedico';
import Horario from '../../features/agendarCita/view/Horario';
import PublicRoute from '../auth/components/PublicRoute';

export const AppRouter = createBrowserRouter([
    {
        path: '/',
        element: <Layout><HomeView/></Layout>
    },
    {
        // path: '/login',
        // element: <PublicRoute><Layout><LoginView/></Layout>,</PublicRoute>
        path: '/login',
        element:<PublicRoute><Layout><LoginView/></Layout></PublicRoute> 
    },
    {
        path: '/paciente',
        element: <PrivateRoute><Layout><PacienteView/></Layout></PrivateRoute>
    },
    {
        path: '/medico',
        element: <PrivateRoute><Layout><MedicoView/></Layout>,</PrivateRoute>
    },
    {
        path: '/medico/turnos',
        element:<Layout><TurnosView/></Layout>,
    },
    {
        path: '/medico/videollamada',
        element: <PrivateRoute><Layout><VIdeollamadaMedico/></Layout>,</PrivateRoute>
    },
    {
        path: '/agendar_cita',
        element: <PublicRoute><Layout><AgendarCita/></Layout></PublicRoute>
    },
    {
        path: 'paciente/horario',
        element: <PrivateRoute><Layout><Horario/></Layout>,</PrivateRoute>
    },
    {
        path: '/Precios',
        element: <Layout><Precios/></Layout>,
    },
    {
        path: '/ayuda',
        element: <Layout><Ayuda/></Layout>,
    },
    {
        path: '/especialidades',
        element: <Layout><Especialidades/></Layout>,
    },
    {
        path: '/registrarse',
        element: <Layout><Registrarse/></Layout>,
    },
    {
        path: '/servicios',
        element: <Layout><Servicios/></Layout>,
    },
    {
        path: '/videollamada',
        element: <PrivateRoute><Layout><VideollamadaView/></Layout>,</PrivateRoute>
    },
    {
        path: '/admin',
        element: <PublicRoute><Layout><Admin/></Layout>,</PublicRoute>
    },
    {
        path: '*',
        element: <div>no se encontro la pagina</div>,
    },
]);