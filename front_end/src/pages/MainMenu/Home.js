import React from 'react';
import NavBar from "./Navbar";
import {Link, useNavigate} from "react-router-dom";
import './home.css'
import '../../welcomeScreen.css'

const Home = () => {

    let navigate = useNavigate()

    const loginRouteChange = () => {
        let path = '/login'
        navigate(path)
    }

    const signUpRouteChange = () => {
        let path = '/signup'
        navigate(path)
    }

    return (
        <div>
            <NavBar/>
            <div className="welcomeScreen">
                <div className="welcomeBox">
                    <p className="welcomeBox-welcomeText">Welcome to MarketPlace!</p>
                    <p className="welcomeBox-text">The best place to buy and sell brand new and used products!</p>
                    <p className="welcomeBox-text">How would you like to continue?</p>
                    <div className="homescreen-continue-links">
                        <Link className="homescreen-continue-links-button" to="myitems">My Items</Link>
                        <Link className="homescreen-continue-links-button" to="search">Search items</Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Home;