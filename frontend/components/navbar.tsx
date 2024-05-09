export default function Navbar() {

    return (
        <nav className="h-14 w-full bg-indigo-800 flex items-center justify-center px-5">
            <h1 className="text-white font-semibold text-xl">Le Burger Restaurant</h1>
            <div className="flex-grow"></div>
            <div className="flex items-center">
                <a href="#" className="text-white hover:text-gray-200">Login</a>
                <a href="#" className="text-white hover:text-gray-200 ml-5">Sign up</a>
            </div>
        </nav>
    );
}
