import Carousel from 'react-bootstrap/Carousel';
// import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
// import Placeholder from 'react-bootstrap/Placeholder';

const Banner = () => {
	return (
		<Carousel slide={false} data-bs-theme="dark">
			<Carousel.Item>
				<img
					className="d-block w-100"
					src="public/img1.png"
					alt="First slide"
				/>
				{/* <Carousel.Caption> */}
					{/* <h5>First slide label</h5> */}
					{/* <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p> */}
				{/* </Carousel.Caption> */}
			</Carousel.Item>
			<Carousel.Item>
					<Row className="align-items-center"> {/* Centra verticalmente */}
						<Col md={6} style={{ "paddingLeft": '160px' }}>
							<h1 className="h1">
								<span  style={{ "color": '#234A6B' }}>Obtenga una consulta</span> 
								<span  style={{ "color": '#0086FF' }}> Medica Personalizada</span></h1>
							<p style={{ "color": '#B0B5BA' }}>Nuestros médicos brindan asesoramiento y consulta médica especializada. Póngase en contacto con nuestro equipo para hablar al respecto.</p>
						</Col>
						<Col md={6}> {/* Ajusta md según el tamaño de pantalla deseado */}
							<img
								className="d-block w-100"
								src="public/img3.png"
								alt="First slide"
							/>
						</Col>
					</Row>
			</Carousel.Item>
			{/* <Carousel.Item> */}
				{/* <Placeholder className="w-100 h-100" as="p" /> */}
				{/* <img
					className="d-block w-100"
					src="holder.js/800x400?text=Third slide&bg=e5e5e5"
					alt="Third slide"
				/> */}
				{/* <Placeholder className="w-75" as="p" animation="glow"> */}
					{/* <Placeholder  /> */}
				{/* </Placeholder> */}
				{/* <Carousel.Caption>
					<h5>Third slide label</h5>
					<p>
						Praesent commodo cursus magna, vel scelerisque nisl consectetur.
					</p>
				</Carousel.Caption> */}
			{/* </Carousel.Item> */}
		</Carousel>
	);
}

export default Banner;