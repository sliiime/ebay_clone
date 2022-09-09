import React, {useContext} from 'react';
import market from '../../images/market.png'
import './home.css'
import {Link} from "react-router-dom";
import AuthContext from "../../context/AuthProvider";

const NavBar = () => {

    const { setAuth } = useContext(AuthContext)

    const handleLogout = () => {
        setAuth({});
        localStorage.clear()
    }

    return (
        <div>
            <nav className="navbar" >
                <div className="navbar-left-side-items">
                    <img className="marketPlaceIcon" src={market} alt=""/>
                    <Link className="navbar-left-link" to="/">Home</Link>
                    <Link className="navbar-left-link" to="/myitems">My Items</Link>
                    <Link className="navbar-left-link" to="/search">Search</Link>
                </div>
                {   localStorage.getItem("accessToken")
                    ?
                    <div className="navbar-right-side-items">
                            <Link className="navbar-right-link" to="/" onClick={handleLogout}>Logout</Link>
                    </div>
                    :
                    <div className="navbar-right-side-items">
                        <Link className="navbar-right-link" to="/login">Login</Link>
                        <Link className="navbar-right-link" to="/signup">Sign Up</Link>
                    </div>
                }
            </nav>
        </div>
    );
};

export default NavBar;