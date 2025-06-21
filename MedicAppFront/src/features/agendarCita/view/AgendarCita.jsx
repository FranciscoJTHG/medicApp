import React, { useState, useEffect } from 'react'
// import { formatDate } from '@fullcalendar/core'
import FullCalendar from '@fullcalendar/react'
import dayGridPlugin from '@fullcalendar/daygrid'
// import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { INITIAL_EVENTS, createEventId } from './event-utils'
import { Navigate, useNavigate } from 'react-router-dom'
import multiMonthPlugin from '@fullcalendar/multimonth'

const Calendar = () => {

	// export default function AgendarCita() {
	const [weekendsVisible, setWeekendsVisible] = useState(true)
	const [currentEvents, setCurrentEvents] = useState([])
	const navigate = useNavigate();

	useEffect(() => {
		// Obtener el token del sessionStorage
		const token = JSON.parse(sessionStorage.getItem('token'));
		// const email = sessionStorage.getItem('email');
		// console.log(email);
	
		// Validar si el token es nulo
		if (!token || !token.JWTtoken) {
		  // Si el token es nulo, redireccionar al login
			navigate('/login');
		}
	  }, []); // Dependencia a 'navigate' para evitar advertencias
	
	function handleWeekendsToggle() {
		setWeekendsVisible(!weekendsVisible)
	}

	// funcion para agregar evento
	async function handleDateSelect(selectInfo) {
		console.log('clic', JSON.parse(sessionStorage.getItem('token')).JWTtoken);

		
		const response = await fetch(`http://localhost:8080/usuario/email/${sessionStorage.getItem('email')}`, {
			method: 'GET',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
				'Authorization': `Bearer ${JSON.parse(sessionStorage.getItem('token')).JWTtoken}`
			}
		});

		if (!response.ok) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		// Convertir la respuesta a JSON
		const userData = await response.json();
		console.log('Datos del usuario:', userData); // Para debug
		// return userData;
		// } catch (error) {
		// 	console.error('Error:', error);
		// 	throw error;
		// }
		
		
		// Obtener la fecha actual real
		const today = new Date();
		today.setHours(0, 0, 0, 0); // Resetear las horas para comparar solo fechas
		
		const selectedDate = new Date(selectInfo.startStr);

		if (selectedDate < today) {
			alert('No puedes seleccionar fechas pasadas.');
			return;
		}

		let calendarApi = selectInfo.view.calendar;
		calendarApi.unselect(); // clear date selection

		const event = {
			id: createEventId(),
			title: 'Nueva cita', // Agregar un título es importante
			nombre: userData.nombre +' '+ userData.apellido,
			email: userData.email,
			dia: selectInfo.startStr, // Usar 'start' en lugar de 'dia'
			hora: "",
		};

		// Agregar el evento al calendario
		calendarApi.addEvent(event);
		
		// Navegar a la página de horario
		navigate('/paciente/horario', { state: { event } });
	}

	// funcion para borrar un evento
	function handleEventClick(clickInfo) {
		if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
			clickInfo.event.remove()
		}
	}

	function handleEvents(events) {
		setCurrentEvents(events)
	}

	return (
		<div className='calendar-container'>
			<FullCalendar
				plugins={[multiMonthPlugin, dayGridPlugin, interactionPlugin]}
				initialView='multiMonthYear'
				editable={true}
				selectable={true}
				selectMirror={true}
				dayMaxEvents={true}
				weekends={weekendsVisible}
				select={handleDateSelect}
				eventClick={handleEventClick}
				// eventsSet={handleEvents}
				views={{
					multiMonthYear: {
						type: 'multiMonth',
						duration: { months: 6 },
						multiMonthMaxColumns: 3,
						multiMonthMinWidth: 300,
						selectable: true
					},
					dayGridMonth: {
						type: 'dayGrid',
						duration: { months: 1 },
						selectable: true
					}
				}}
				headerToolbar={{
					left: 'prev,next today',
					center: 'title',
					right: 'multiMonthYear,dayGridMonth'
				}}
				height="auto"
				locale="es" // Para español
				events={[
					{
						title: 'Evento de ejemplo',
						start: '2024-10-31'
					}
				]}
				validRange={{
					start: new Date()
				}}
				dayCellClassNames={(arg) => {
					const today = new Date();
					return arg.date < today ? ['disabled-day'] : [];
				}}
				eventAdd={function(){}}
				eventChange={function(){}}
				eventRemove={function(){}}
			/>
		</div>
	);
};

export default Calendar;