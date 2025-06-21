import { useState } from 'react'

import './App.css'
import Inicio from './components/inicio.jsx'
import ImagenTelemedicina from './components/ImagenTelemedicina.jsx'
import { RouterProvider } from 'react-router-dom'
import { AppRouter } from './core/routes/AppRouter.jsx'
import { AuthProvider } from './core/auth/providers/authProvider.jsx'
import Layout from './core/layouts/Layout.jsx'
//import '@fullcalendar/common/main.css'; // Importa estilos comunes
//import '@fullcalendar/daygrid/main.css'; // Importa estilos del plugin daygrid

function App() {

  return (
    <>
      
      {/* <Inicio>TELEMEDICINA</Inicio>
      <ImagenTelemedicina></ImagenTelemedicina> */}
      

      
      <AuthProvider>
      {/* <Layout> */}
        <RouterProvider router={AppRouter}></RouterProvider>
       {/*  </Layout> */}
      </AuthProvider>
      
      
    </>
  )
}

export default App
