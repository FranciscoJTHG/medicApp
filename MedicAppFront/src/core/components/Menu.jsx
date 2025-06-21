import React from 'react';
 import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../auth/hook/useAuth';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';


const Menu = () => {
     const navigate = useNavigate();
    const { logOut, isLoggedIn } = useAuth();

    function cerrarSesion(){
        sessionStorage.removeItem('token');
    // Redirigir al login
    navigate('/login');
    }
    // const handleLoginClick = () => {
    //    navigate('/login'); // Cambia la ruta según sea necesario
    // };

    // const handleRegistrarseClick = () => {
    //     navigate('/registrarse'); // Cambia la ruta según sea necesario
    // };

    return (
        <Navbar expand="lg" className="bg-body-tertiary justify-content-center">
            <Container className="justify-content-center" fluid style={{ "backgroundColor": 'rgb(155 ,219, 242' }}>
                <Navbar.Brand href="/">
                    <img
                        alt=""
                        src="/public/logo.jpeg"
                        width="80"
                        height="100"
                        className="d-inline-block align-top"
                    />{' '}
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="/" className="mx-2">Inicio</Nav.Link>
                        <Nav.Link href="/especialidades" className="mx-2">Especialidades</Nav.Link>
                        {/* <Nav.Link href="/especialidades">Especialidades</Nav.Link> */}
                        <NavDropdown title="Medico" id="basic-nav-dropdown" className="mx-2">
                            <NavDropdown.Item href="/medico/turnos">Turnos Disponibles</NavDropdown.Item>
                            <NavDropdown.Item href="/medico/videoLlamada">Video Llamada</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/login">Iniciar Sesión</NavDropdown.Item>
                            <NavDropdown.Divider />
                            {/* <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item> */}
                        </NavDropdown>
                        <NavDropdown title="Paciente" id="basic-nav-dropdown" className="mx-2">
                            <NavDropdown.Item href="/agendar_cita">Agendar Cita</NavDropdown.Item>
                            <NavDropdown.Item href="/videoLlamada">Video Llamada</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="/login">Iniciar Sesión / Registrarse</NavDropdown.Item>
                            {/* <NavDropdown.Divider />
                            <NavDropdown.Item href="#action/3.4">
                                Separated link
                            </NavDropdown.Item> */}
                            <NavDropdown.Item onClick={cerrarSesion}>Cerrar Sesion</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Menu;