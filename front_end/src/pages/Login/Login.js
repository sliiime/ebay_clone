import WelcomeLoginBox from "../../components/WelcomeLoginBox";
import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import regexValidation from "./regexValidation";
import loginErrorsExist from "./errorsExist";
import axios from "axios";

function Login() {

    const [credentials, setCredentials] = useState({
        email:"",
        password:""
    });

    let navigate = useNavigate();

    const [errors,setErrors] = useState({});
    const [disableButton, setDisableButton] = useState(false);
    const [loginError,setLoginError] = useState("");
    const [submitButtonPressed,setSubmitButtonPressed] = useState(false)
    const [isCorrectSubmission,setIsCorrectSubmission] = useState(0)

    const routeChange = () => {
        let path = '../signup'
        navigate(path)
    }

    const handleChange = (event) => {
        setCredentials({
            ...credentials,
            [event.target.name]: event.target.value,
        });
    };

    const handleFormSubmit = (event) => {
        event.preventDefault();
        setErrors(regexValidation(credentials));
        setSubmitButtonPressed(true)
    };

    useEffect(() => {
        if(loginErrorsExist(submitButtonPressed,errors)) {
            return;
        } else {
            axios
                .post("http://localhost:8080/ebay_clone/api/user/", {
                    email    : credentials.email,
                    password : credentials.password
                })
                .then((response) => {
                    setIsCorrectSubmission(1);
                    setDisableButton(true);
                    const timer = setTimeout(() => navigate('../'), 3000);
                    return () => clearTimeout(timer);
                })
                .catch((error) => {
                    setLoginError(error.response.data);
                    setIsCorrectSubmission(2);
                });
        }
    }, [submitButtonPressed, errors]);

    return (
        <div className="login">
            {/* WELCOME */}
            <WelcomeLoginBox underWelcomeText="Login to your account"/>
            {/* CREDENTIALS */}
            <div className="credentialsInput">
                <p className="overInputText">Email</p>
                <div className="loginInputDiv">
                    <input className="login--inputBox" placeholder="Enter Email" type="email" name="email" value={credentials.email} onChange={handleChange}/>
                    {errors.email && <p className="input--error">{errors.email}</p>}
                </div>
                <p className="overInputText">Password</p>
                <div className="loginInputDiv">
                    <input className="login--inputBox" placeholder="Enter Password" type="password" name="password" value={credentials.password} onChange={handleChange}/>
                    {errors.password && <p className="input--error">{errors.password}</p>}
                </div>
            </div>
            {/* LOGIN BUTTON */}
            <div className="login-register">
                <button className="login-register--button" onClick={handleFormSubmit} disabled={disableButton}>
                    Login
                </button>
            </div>
            {/* FORGET PASSWORD & CREATE ACCOUNT BUTTONS */}
            <div className="underButtonContent">
                <button className="forgetPasswordButton">Forget password</button>
                <button className="createAccButton" onClick={routeChange}>Create an account</button>
            </div>
            <div className="login--afterSubmissionInfo">
                {
                    isCorrectSubmission===1 &&
                    <p className="correct--signup--login">
                        You logged in! Have fun in the Marketplace!
                    </p>
                }
                {
                    isCorrectSubmission===2 &&
                    <p className="wrong--signup--login">
                        {loginError}
                    </p>
                }
            </div>
        </div>
    )
}

export default Login;