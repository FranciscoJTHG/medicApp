import React from 'react'

import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';


const Footer = () => {
    return (
        <Row className="row row-cols-1 row-cols-sm-2 row-cols-md-5 pt-5 mt-5" style={{"backgroundColor": "#B3B3B3"}}>
            <Col mb={3}>
                <a href="/" className="d-flex justify-content-center mb-3 link-dark text-decoration-none">
                <img
                    alt=""
                    src="/public/logo.jpeg"
                    width="80"
                    height="100"
                    className="d-inline-block align-top"
                />{' '}
                </a>
                <div className='d-flex justify-content-center'>
                    <i className="bi bi-youtube mx-2 tamIco"></i>
                    <i className="bi bi-facebook mx-2 tamIco"></i>
                    <i className="bi bi-twitter mx-2 tamIco"></i>
                    <i className="bi bi-instagram mx-2 tamIco"></i>
                </div>
                <p className="text-muted d-flex justify-content-center">© 2024</p>
            </Col>
            <Col mb={3}>
            </Col>

            <Col mb={3}>
                <h5>Para Pacientes</h5>
                <ul className="nav flex-column">
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Buscar Doctor</a></li>
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Buscar Hospital</a></li>
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Test de Diagnóstico</a></li>
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Chequeo Completo</a></li>
                </ul>
            </Col>
            <Col mb={3}>
                <h5>Para Doctores</h5>
                <ul className="nav flex-column">
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Consulta de Perfil</a></li>
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Equipo Colaborador</a></li>
                </ul>
            </Col>
            <Col mb={3}>
                <h5>Mas</h5>
                <ul className="nav flex-column">
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Ayuda</a></li>
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Desarrolladores</a></li>
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Políticas de Privacidad</a></li>
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Términos y Condiciones</a></li>
                    <li className="nav-item mb-2"><a href="#" className="nav-link p-0 text-muted">Soporte</a></li>
                </ul>
            </Col>
        </Row>
    )
}

export default Footer