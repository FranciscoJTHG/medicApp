import React from 'react';

const Card2 = () => {
  return (
    <div className="card mb-3" style={{ maxWidth: '540px' }}>
      <div className="row g-0">
        <div className="col-md-8">
          <div className="card-body">
            <h5 className="card-title text-start">Nuestros valores estelares</h5>
            <p className="card-text text-start">
              La piedra angular de nuestro establecimiento es "Hacer que 
              los beneficios de los servicios médicos excepcionales lleguen
              a las personas sin discriminación". Nos esforzamos por estar a la 
              altura de esta filosofía a través de nuestros valores estelares, que
              son los pilares de cada servicio que ofrecemos.
            </p>
            <div className="text-start"> {/* Clase para alinear el botón a la izquierda */}
              <button className="btn btn-primary">Contactarnos</button>
            </div>
          </div>
        </div>
        <div className="col-md-4">
          <img src="/telemedicina.jpg" className="img-fluid rounded-start" alt="..." />
        </div>
      </div>
    </div>
  );
}

export default Card2;