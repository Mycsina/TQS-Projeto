"use client";

import { useEffect, useState } from "react";

export default function Navbar() {

  const [logged, setLogged] = useState(false);

  useEffect(() => {
    const logged = localStorage.getItem("loggedIn") == "true";
    setLogged(logged);
  }, []);

  return (
    <nav className="h-14 w-full bg-slate-800 dark:bg-gray-950 flex items-center justify-center px-5 shadow-xl fixed">
      <h1 className="text-white font-semibold text-xl"><a href="/">QuickServe</a></h1>
      <div className="flex-grow"></div>
      {logged ? (
        <div className="flex items-center">
          <a href="dashboard" className="text-white hover:text-gray-200">Dashboard</a>
          <a href="cart" className="text-white hover:text-gray-200">Cart</a>
          <a href="logout" className="text-white hover:text-gray-200 ml-5">Logout</a>
        </div>
      ) : (
        <div className="flex items-center">
          <a href="login" className="text-white hover:text-gray-200">Login</a>
          <a href="signup" className="text-white hover:text-gray-200 ml-5">Sign up</a>
        </div>
      )}
    </nav>
  );
}
