import React from 'react';

const Card1 = () => {
  return (
    <div className="card mb-3 mt-3" style={{ maxWidth: '540px' }}>
      <div className="row g-0">
        <div className="col-md-4">
          <img src="/telemedicina.jpg" className="img-fluid rounded-start" alt="..." />
        </div>
        <div className="col-md-8">
          <div className="card-body">
            <h5 className="card-title text-start">Prácticas Médicas preventivas, prescritas y curativas de primer nivel</h5>
            <p className="card-text text-start">
              Al trabajar en el sector de salud, consideramos que es nuestro deber primordial
              garantizar la seguridad de nuestros pacientes, la eficacia de nuestros tratamientos,
              la transparencia de nuestras prácticas y una atención absolutamente oportunaaaaaaaaaaaa.
            </p>
            <div className="text-start"> {/* Clase para alinear el botón a la izquierda */}
              <button className="btn btn-primary">Contactarnos</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Card1;