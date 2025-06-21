import React from 'react';
import { useAuth } from '../../../core/auth/hook/useAuth';
// import Layout from '../../../core/layouts/Layout';
// import Card1 from '../../../core/components/Card1';
// import Card2 from '../../../core/components/Card2';
import Banner from '../../../core/components/Banner';
import Body from '../../../core/components/Body';

const HomeView = () => {
  /* const { logOut, isLoggedIn } = useAuth(); 
  console.log("isLoggedIn:" + isLoggedIn);*/

  return (
    <>
      <Banner />
      <Body />
    </>
  );
}

export default HomeView;