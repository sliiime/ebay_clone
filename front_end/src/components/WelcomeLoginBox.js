import React from "react";

function WelcomeLoginBox({underWelcomeText}) {
    return (
        <div className="welcomeLoginBox">
            <p className="welcomeLoginBox--title">
                Welcome to MarketPlace!
            </p>
            <p className="welcomeLoginBox--text">
                {underWelcomeText}
            </p>
        </div>
    )
}

export default WelcomeLoginBox;