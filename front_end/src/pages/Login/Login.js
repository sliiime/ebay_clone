import WelcomeLoginBox from "../../components/WelcomeLoginBox";
import CredentialsInput from "../../components/CredentialsInput";
import LoginButton from "../../components/LoginButton";
import UnderButtonContent from "../../components/UnderButtonContent";
import React from "react";

function Login() {
    return (
        <div className="login">
            <WelcomeLoginBox underWelcomeText="Login to your account"/>
            <CredentialsInput/>
            <LoginButton/>
            <UnderButtonContent/>
        </div>
    )
}

export default Login;