import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
// import Placeholder from 'react-bootstrap/Placeholder';
import Button from 'react-bootstrap/Button';


const Body = () => {
	return (
        <Container>
            <div className="subTitulo my-4">
                <h2 className="h2" style={{ "color": '#234A6B' }}>
                    Consulte a los mejores Doctores en línea para cualquier problema de salud
                </h2>
                <p style={{ "color": '#B0B5BA' }}>Consulta privada online con médicos verificados en todas las especialidades</p>
            </div>
            <Row className="align-items-center">
                <Col md={6}>
                    <img
                        className="d-block w-100"
                        src="public/img4.png"
                        alt="First slide"
                    />
                </Col>
                <Col md={6}>
                    <h1 className="h1">
                        <span  style={{ "color": '#234A6B' }}>Prácticas médicas preventivas, prescriptivas y curativas de primer nivel</span>
                    </h1>
                    <p style={{ "color": '#B0B5BA' }}>Al trabajar en el sector de la salud, consideramos que es nuestro deber primordial garantizar la seguridad de nuestros pacientes, la eficacia de nuestros tratamientos, la transparencia en nuestras prácticas y una atención absolutamente oportuna.</p>
                    <div className="mb-2">
                        <Button variant="primary" size="lg">
                            Contactanos
                        </Button>{' '}
                    </div>
                </Col><Col md={6}>
                <h1 className="h1">
                        <span  style={{ "color": '#234A6B' }}>Nuestras Valores Estelares</span>
                    </h1>
                    <p style={{ "color": '#B0B5BA' }}>La piedra angular de nuestro establecimiento es “Hacer que los
                    beneficios de los servicios médicos excepcionales lleguen a las personas sin discriminación”. Nos esforzamos por estar a la altura de esta filosofía a través de nuestros valores estelares, que son los pilares de cada servicio que ofrecemos.</p>
                    <div className="mb-2">
                        <Button variant="primary" size="lg">
                            Contactanos
                        </Button>{' '}
                    </div>
                </Col>
                <Col md={6}>
                    <img
                        className="d-block w-100"
                        src="public/img5.png"
                        alt="First slide"
                    />
                </Col>
            </Row>
        </Container>
	);
}

export default Body;