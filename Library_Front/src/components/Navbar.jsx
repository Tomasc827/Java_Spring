import { Link } from "react-router"

export const Navbar = () => {
  return (
    <>
    <nav className="bg-gradient-to-b from-purple-500 to-sky-500">
        <div className="flex justify-end py-10">
            <Link to="/">
            <button className="super-btn">
            Home
            </button>
            </Link>
            <button className="super-btn">
            <p>About us</p>
            </button>
            <Link to="/registration">
            <button className="super-btn">
            <p>Register</p>
            </button>
            </Link>
            <Link to="/login">
            <button className="super-btn">
                <p>Login</p>
            </button>
            </Link>
        </div>
    </nav>
    </>
  )
}

