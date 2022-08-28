import React, {useContext} from 'react';
import market from '../../images/market.png'
import '../../css/home.css'
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
                <img className="marketPlaceIcon" src={market} alt=""/>
                <div className="left-side-items">
                    <Link className="navbar--homeLink" to="./home">Home</Link>
                    <Link className="navbar--searchLink" to="./search">Search</Link>
                </div>
                {   localStorage.getItem("accessToken")
                    ?
                    <div className="right-side-items">
                            <Link className="navbar--logoutLink" to="/" onClick={handleLogout}>Logout</Link>
                    </div>
                    :
                    <div className="right-side-items">
                        <Link className="navbar--loginLink" to="../login">Login</Link>
                        <Link className="navbar--signupLink" to="../signup">Sign Up</Link>
                    </div>
                }
            </nav>
        </div>
    );
};

export default NavBar;