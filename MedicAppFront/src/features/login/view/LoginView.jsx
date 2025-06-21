// import React from 'react'
import React, { useState } from 'react';
// import { useAuth } from '../../../core/auth/hook/useAuth'
// import Row from 'react-bootstrap/Row';
// import Col from 'react-bootstrap/Col';
import Alert from 'react-bootstrap/Alert';
import { useNavigate } from 'react-router-dom'


const LoginView = () => {
	// const { isLoggedIn, logIn } = useAuth();
	const [user, setUser] = useState({ email: '', clave: '' });
	const [showAlert, setShowAlert] = useState(false);
	const [alertConfig, setAlertConfig] = useState({ variant: '', message: '' });
	const navigate = useNavigate(); 
	console.log(user);
	
	const handleSubmit = async (event) => {
		event.preventDefault();
		try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(user),
            });

            const data = await response.json();
			sessionStorage.setItem('email', user.email);
			

            if (!response.ok) {
				console.log(response.status);

                switch (response.status) {
                    case 404:
                        setAlertConfig({
                            variant: 'danger',
                            message: '¡Usuario No Registrado!'
                        });
                        break;
                    case 401:
                        setAlertConfig({
                            variant: 'danger',
                            message: '¡Credenciales inválidas!'
                        });
                        break;
                    default:
                        setAlertConfig({
                            variant: 'danger',
                            message: '¡Error en el servidor!'
                        });
                }
                setShowAlert(true);
                return;
            }

            if (data && data.JWTtoken) {
                setAlertConfig({
                    variant: 'success',
                    message: '¡Inicio de sesión exitoso!'
                });
                setShowAlert(true);
                sessionStorage.setItem('token', JSON.stringify(data));
                setTimeout(() => {
                    navigate('/agendar_cita');
                }, 1500);
            }
        } catch (error) {
            setAlertConfig({
                variant: 'danger',
                message: 'Error de conexión'
            });
            setShowAlert(true);
        }
	};

	const handleChange = (event) => {
		
		const { name, value } = event.target;
		setUser(prevUser => ({ ...prevUser, [name]: value }));
	};

	return (
		<>
			<div className="container">
				<div className="row">
					<div className="col-lg-12 d-flex justify-content-center mt-5">
						<h1 className="h3 mb-3 fw-normal" style={{ "color": '#234A6B' }}>¡Bienvenido a MedicApp!</h1>
					</div>
					<div className="col-lg-4 mb-lg-0 mt-5">
					<Alert 
                        show={showAlert}
                        variant={alertConfig.variant}
                        onClose={() => setShowAlert(false)}
                        dismissible
                        className="position-fixed top-0 start-50 translate-middle-x mt-3"
                        style={{ zIndex: 1000, minWidth: '300px' }}
                    >
                        {alertConfig.message}
                    </Alert>
						<div className="card">
							<div className="card-body py-5 px-md-5">
								<form className="was-validated" onSubmit={handleSubmit}>
									<div className="form-floating mb-3">
										<input type="email" className="form-control" id="floatingInput" placeholder="name@example.com" name="email" value={user.email} onChange={handleChange} required/>
										<label htmlFor="floatingInput">Email</label>
										<div className="valid-feedback">Email valido</div>
										<div className="invalid-feedback">Por favor ingrese un email valido.</div>
									</div>
									<div className="form-floating mb-3">
										<input type="password" className="form-control" id="floatingPassword" placeholder="Password" name="clave" value={user.clave} onChange={handleChange} required/>
										<label htmlFor="floatingPassword">Password</label>
									</div>
									<button type="submit" className="btn btn-primary w-100 py-2">Iniciar Sesión</button>
									<div className="text-center mt-4">
										<p className="mt-5 text-body-secondary">¿Aún no tienes cuenta? <a className="link-opacity-10-hover" href="/registrarse">Registrate</a></p>										
										<p className="text-body-secondary">¿Olvidaste tu contraseña? <a className="link-opacity-10-hover" href="#">Recupérala aquí</a></p>										
									</div>
								</form>
							</div>
						</div>
					</div>
					<div className="col-md-4 mt-5 d-flex justify-content-center" style={{"height": "400px"}}>
						<div className="vr"></div>
					</div>
					<div className="col-md-4 mt-5">
						<img className="logo mb-4" src="public/img6.png" style={{ maxWidth: '100%', height: 'auto' }} />
					</div>
				</div>
			</div>
		</>

	)
}

export default LoginView