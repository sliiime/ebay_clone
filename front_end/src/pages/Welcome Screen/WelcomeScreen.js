import React from "react";
import {useNavigate} from "react-router-dom";

function WelcomeScreen() {
    let navigate = useNavigate();

    const loginRouteChange = () => {
        let path = '/login'
        navigate(path)
    }

    const signUpRouteChange = () => {
        let path = '/signup'
        navigate(path)
    }

    return (
        <div className="welcomeScreen">
            <div className="welcomeBox">
                <p className="welcomeBox-welcomeText">Welcome to MarketPlace!</p>
                <p className="welcomeBox-text">The best place to buy and sell brand new and used products!</p>
                <p className="welcomeBox-text">How would you like to continue?</p>
            </div>
            <div className="welcome--login-register">
                <button className="welcome--login-register-button" onClick={loginRouteChange}>Login</button>
                <button className="welcome--login-register-button" onClick={signUpRouteChange}>Register</button>
            </div>
            <button className="welcome--guest-button">Continue as guest</button>
        </div>
    )
}

export default WelcomeScreen;