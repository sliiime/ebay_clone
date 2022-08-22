import React from "react";
import {useNavigate} from "react-router-dom";

function UnderButtonContent() {
    let navigate = useNavigate();
    const routeChange = () => {
        let path = '../signup'
        navigate(path)
    }

    return (
        <div className="underButtonContent">
            <button className="forgetPasswordButton">Forget password</button>
            <button className="createAccButton" onClick={routeChange}>Create an account</button>
        </div>
    )
}

export default UnderButtonContent;