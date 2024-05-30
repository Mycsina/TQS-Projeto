export default function Navbar() {

  return (
    <nav className="h-14 w-full bg-slate-800 dark:bg-gray-950 flex items-center justify-center px-5 shadow-xl fixed">
      <h1 className="text-white font-semibold text-xl"><a href="/">QuickServe</a></h1>
      <div className="flex-grow"></div>
      <div className="flex items-center">
        <a href="cart" className="text-white hover:text-gray-200">Cart</a>
        <a href="login" className="text-white hover:text-gray-200 ml-5">Login</a>
        <a href="signup" className="text-white hover:text-gray-200 ml-5">Sign up</a>
      </div>
    </nav>
  );
}
