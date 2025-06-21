import React from 'react'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Menu from '../components/Menu'
import Footer from '../components/Footer'

const Layout = ({ children }) => {
    return (
        <>
            {/* <div className='container fluid'> */}
                {/* header */}
                {/* <Menu></Menu>  */}
                {/* main */}
                {/* <main> */}
                    {/* <div>{children}</div> */}
                {/* </main> */}
                {/* footer */}
                {/* <Footer></Footer> */}
            {/* </div> */}

            <Container fluid>
                <Row>
                    <Col>
                        <Menu></Menu>
                        <main>
                            <div>{children}</div>
                        </main>
                        <Footer></Footer>
                    </Col>
                </Row>
            </Container>

        </>

    )
}

export default Layout