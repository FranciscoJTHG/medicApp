
let eventGuid = 0
let todayStr = new Date().toISOString().replace(/T.*$/, '') // YYYY-MM-DD of today

export const INITIAL_EVENTS = [
  {
    id: createEventId(),
    title: 'esteban quito',
    nombre: 'esteban quito',
    start: todayStr,
    dia:'30/10/2024',
    hora:'11.00 AM'
  },
  {
    id: createEventId(),
    title: 'Armando casas',
    nombre: 'Armando casas',
    start: todayStr ,
    dia:'30/10/2024',
    hora:'12.00 PM'
  }
]

export function createEventId() {
  return String(eventGuid++)
}