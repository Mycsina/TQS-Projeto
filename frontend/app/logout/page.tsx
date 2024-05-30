"use client";

import { useEffect } from 'react';

const LogoutPage: React.FC = () => {

  useEffect(() => {
    localStorage.removeItem("loggedIn");
    window.location.href = "/login";
  }
    , []);

  return (
    <div>
      <h1>Logging out...</h1>
      {/* You can add a loading spinner or any other UI elements here */}
    </div>
  );
};

export default LogoutPage;