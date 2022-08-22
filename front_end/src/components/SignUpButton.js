import React from "react";

function SignUpButton() {
    const handleFormSubmit = (event) => {
        event.preventDefault();
    };

    return (
        <div className="login-register">
            <button className="login-register--button" onClick={handleFormSubmit}>
                Register!
            </button>
        </div>
    )
}

export default SignUpButton;