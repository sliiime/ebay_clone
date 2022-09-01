import WelcomeLoginBox from "../../components/WelcomeLoginBox";
import React, {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import regexValidation from "./regexValidation";
import loginErrorsExist from "./errorsExist";
import axios from "axios";
import useAuth from "../../context/useAuth";
import './login.css';

function Login() {

    const { setAuth } = useAuth();

    const [credentials, setCredentials] = useState({
        username:"",
        password:""
    });

    let navigate = useNavigate();

    const location = useLocation();
    const from = location.state?.from?.pathname || "/";

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
        if(!loginErrorsExist(submitButtonPressed,errors)) {
            axios
                .post("http://localhost:8080/ebay_clone/api/auth", {
                    username : credentials.username,
                    password : credentials.password
                })
                .then((response) => {
                    console.log(response)
                    console.log(response.data)
                    const username = credentials.username;
                    const roleResponse = response?.data?.status//response?.data?.role;
                    let roles = []
                    console.log(roleResponse)
                    if (roleResponse === "admin") {
                        roles = [5150]
                    } else if (roleResponse === "approved") {
                        roles = [2001]
                    } else {
                        setLoginError("Your account has not been accepted yet!");
                        setIsCorrectSubmission(2);
                        return;
                    }
                    const accessToken = response?.data?.token//response?.data?.accessToken;
                    setAuth({ username, roles, accessToken });
                    localStorage.setItem("username", JSON.stringify(credentials.username));
                    localStorage.setItem("roles", JSON.stringify(roles));
                    localStorage.setItem("accessToken", JSON.stringify(accessToken));
                    setCredentials({
                        username: "",
                        password: ""
                    })
                    setIsCorrectSubmission(1);
                    setDisableButton(true);
                    roleResponse==="admin" ? navigate('../admin') : navigate(from, {replace: true})
                })
                .catch((error) => {
                    console.log(error)
                    console.log(error.response.status)
                    if (error.response.status === 401) {
                        setLoginError("Either username or password is incorrect!");
                    } else {
                        setLoginError(error.response.data)
                    }
                    setIsCorrectSubmission(2);
                });
        }
    }, [submitButtonPressed, errors]);

    useEffect(() => {
        if(localStorage.getItem("username")) {
            const username = JSON.parse(localStorage.getItem("username"))
            const roles = JSON.parse(localStorage.getItem("roles"))
            const accessToken = JSON.parse(localStorage.getItem("accessToken"))
            console.log(username)
            console.log(roles)
            console.log(accessToken)
            setAuth({username, roles, accessToken})

            navigate(from, {replace: true})
        }
    })

    return (
        <div className="login">
            {/* WELCOME */}
            <WelcomeLoginBox underWelcomeText="Login to your account"/>
            {/* CREDENTIALS */}
            <div className="credentialsInput">
                <p className="overInputText">Email</p>
                <div className="loginInputDiv">
                    <input className="login--inputBox" placeholder="Enter Username" type="username" name="username" value={credentials.username} onChange={handleChange} />
                    {errors.username && <p className="input--error">{errors.username}</p>}
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