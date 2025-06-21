import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
//import 'bootstrap/dist/css/bootstrap.min.css';
import Alert from 'react-bootstrap/Alert';
// import { useNavigate } from 'react-router-dom'


const Horario = () => {
	const location = useLocation();
	const navigate = useNavigate();
	const { event } = location.state || {};

	const [selectedTime, setSelectedTime] = useState('');
	const [selectedSpecialty, setSelectedSpecialty] = useState('');
	const [showAlert, setShowAlert] = useState(false);
	const [alertConfig, setAlertConfig] = useState({ variant: '', message: '' });


	const availableTimes = [
		'09:00 AM',
		'10:00 AM',
		'11:00 AM',
		'01:00 PM',
		'02:00 PM',
		'03:00 PM',
		'04:00 PM',
	];

	const specialties = [
		'Traumatólogo',
		'Cardiólogo',
		'Pediatra',
		'Dermatólogo',
		'Oftalmólogo',
		'Ginecólogo',
		'Endocrinólogo',
	];
	const [year, month, day] = event.dia.split('-'); // Descompone la fecha
	const formattedDate = `${day}/${month}/${year}`; // Formato DD/MM/YYYY

	const handleTimeChange = (e) => {
		setSelectedTime(e.target.value);
	};

	const handleSpecialtyChange = (e) => {
		setSelectedSpecialty(e.target.value);
	};

	const handleCancel = () => {
		navigate('/agendar_cita'); // Redirige a la página principal
	};

	const handleConfirm = () => {
		// alert(`Turno confirmado para el paciente: ${event.nombre} a las: ${selectedTime} con el especialista: ${selectedSpecialty}.`);
		// aca debe enviar los datos al servidor
		// navigate('/'); // Redirige después de confirmar

		setAlertConfig({
			variant: 'success',
			message: '¡Su cita fue agendada para la fecha y hora seleccionada por favor revise su correo en donde se le enviara el link de la reunión!'
		});
		setShowAlert(true);
		// sessionStorage.setItem('token', JSON.stringify(data));
		// setTimeout(() => {
		// 	navigate('/agendar_cita');
		// }, 1500);
	};

	if (!event) {
		return <div className="alert alert-warning">No hay evento seleccionado.</div>;
	}

	return (
		<div className="container mt-5">
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
			<h1 className="text-center" style={{ "color": '#234A6B' }}>Detalles del Turno</h1>
			<div className="card">
				<div className="card-body py-5 px-md-5">
					<div className="form-floating mb-3">
						<input type="email" className="form-control" id="floatingInput" placeholder="name@example.com" name="nombre" disabled/>
						<label htmlFor="floatingInput"><p><strong>Nombre del Paciente: </strong>{event.nombre}</p></label>
					</div>
					<div className="form-floating mb-3">
						<input type="email" className="form-control" id="floatingInput" placeholder="name@example.com" name="email" disabled/>
						<label htmlFor="floatingInput"><p><strong>Email del Paciente: </strong>{event.email}</p></label>
					</div>
					<div className="form-floating mb-3">
						<input type="email" className="form-control" id="floatingInput" placeholder="name@example.com" name="email" disabled/>
						<label htmlFor="floatingInput"><p><strong>Id del Paciente: </strong>{event.id}</p></label>
					</div>
					<div className="form-floating mb-3">
						<input type="email" className="form-control" id="floatingInput" placeholder="name@example.com" name="email" disabled/>
						<label htmlFor="floatingInput"><p><strong>Fecha de la Cita: </strong>{formattedDate}</p></label>
					</div>
					<div className="form-group mt-3">
						<label htmlFor="visitTime">Selecciona un horario de visita:</label>
						<select
							id="visitTime"
							className="form-control"
							value={selectedTime}
							onChange={handleTimeChange}
						>
							<option value="">Elige un horario</option>
							{availableTimes.map((time, index) => (
								<option key={index} value={time}>
									{time}
								</option>
							))}
						</select>
					</div>

					{selectedTime && (
						<div className="mt-3">
							<p><strong>Horario seleccionado:</strong> {selectedTime}</p>
						</div>
					)}

					{/* Select para especialidades médicas */}
					<div className="form-group mt-3">
						<label htmlFor="specialty">Selecciona una especialidad médica:</label>
						<select
							id="specialty"
							className="form-control"
							value={selectedSpecialty}
							onChange={handleSpecialtyChange}
						>
							<option value="">Elige una especialidad</option>
							{specialties.map((specialty, index) => (
								<option key={index} value={specialty}>
									{specialty}
								</option>
							))}
						</select>
					</div>

					{selectedSpecialty && (
						<div className="mt-3">
							<p><strong>Especialidad seleccionada:</strong> {selectedSpecialty}</p>
						</div>
					)}

					<div className="mt-4">
						<button className="btn btn-danger me-2" onClick={handleCancel}>
							Cancelar
						</button>
						<button
							className="btn btn-success"
							onClick={handleConfirm}
							disabled={!selectedTime || !selectedSpecialty} // Deshabilitar si no hay horario o especialidad seleccionada
						>
							Confirmar
						</button>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Horario