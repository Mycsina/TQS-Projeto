export default function Navbar() {

    return (
        <nav className="h-14 w-full bg-slate-800 dark:bg-gray-950 flex items-center justify-center px-5 shadow-xl fixed">
            <h1 className="text-white font-semibold text-xl">QuickServe</h1>
            <div className="flex-grow"></div>
            <div className="flex items-center">
                <a href="#" className="text-white hover:text-gray-200">Login</a>
                <a href="#" className="text-white hover:text-gray-200 ml-5">Sign up</a>
            </div>
        </nav>
    );
}
