import React from "react";

function SignUpInput({credentials,setCredentials}) {
    return (
        <form className="signUpInput">
            <input className="signUpInputBox" placeholder="Email" type="email"/>
            <input className="signUpInputBox" placeholder="Username" type="username"/>
            <input className="signUpInputBox" placeholder="First name" type="text"/>
            <input className="signUpInputBox" placeholder="Last name" type="text"/>
            <input className="signUpInputBox" placeholder="ΑΦΜ" type="number"/>
            <input className="signUpInputBox" placeholder="Phone number" type="number"/>
            <input className="signUpInputBox" placeholder="Address" type="text"/>
            <input className="signUpInputBox" placeholder="Password" type="password"/>
            <input className="signUpInputBox" placeholder="Confirm password" type="password"/>
        </form>
    )
}

export default SignUpInput;