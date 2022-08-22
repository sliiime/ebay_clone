import React from "react";

function CredentialsInput() {
    return (
        <div className="credentialsInput">
            <EmailInput/>
            <PasswordInput/>
        </div>
    )
}

function EmailInput() {
    return (
        <div>
            <p className="overInputText">Email</p>
            <div>
                <input className="login--inputBox" placeholder="Enter Email" type="email" name="email" id="email"/>
            </div>
        </div>
    )
}

function PasswordInput() {
    return (
        <div>
            <p className="overInputText">Password</p>
            <div>
                <input className="login--inputBox" placeholder="Enter Password" type="password" name="password" id="password"/>
            </div>
        </div>
    )
}

export default CredentialsInput;