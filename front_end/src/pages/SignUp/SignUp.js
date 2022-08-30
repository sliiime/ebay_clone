import WelcomeLoginBox from "../../components/WelcomeLoginBox";
import React, {useState , useEffect} from "react";
import {useNavigate} from "react-router-dom";
import regexValidation from "./regexValidation";
import errorsExist from "./errorsExist";
import axios from "axios";
import './signup.css';

function SignUp() {

    const [credentials,setCredentials] = useState({
        email:"",
        username:"",
        firstName:"",
        lastName:"",
        afm:"",
        phoneNumber:"",
        address:"",
        password:"",
        confirmPassword:"",
        country:""
    });

    let navigate = useNavigate();

    const [errors,setErrors] = useState({});
    const [signUpError,setSignUpError] = useState("");
    const [disableButton, setDisableButton] = useState(false);
    const [submitButtonPressed,setSubmitButtonPressed] = useState(false)
    const [isCorrectSubmission,setIsCorrectSubmission] = useState(0)

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
        if(!errorsExist(submitButtonPressed,errors)) {
            axios
                .post("http://localhost:8080/ebay_clone/api/user/", {
                    username : credentials.username,
                    password : credentials.password,
                    name     : credentials.firstName,
                    surname  : credentials.lastName,
                    address  : credentials.address,
                    email    : credentials.email,
                    afm      : credentials.afm,
                    phone    : credentials.phoneNumber,
                    country  : credentials.country
                })
                .then((response) => {
                    setIsCorrectSubmission(1);
                    setDisableButton(true);
                    const timer = setTimeout(() => navigate('../login'), 3000);
                    return () => clearTimeout(timer);
                })
                .catch((error) => {
                    setSignUpError(error.response.data);
                    setIsCorrectSubmission(2);
                });
        }
    }, [submitButtonPressed, errors]);

    return (
        <div className="signUp">
            <WelcomeLoginBox underWelcomeText="Create a new account"/>
            <form className="signUpInput">
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="Email" type="email" name="email" value={credentials.email} onChange={handleChange}/>
                    {errors.email && <p className="input--error">{errors.email}</p>}
                </div>
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="Username" type="username" name="username" value={credentials.username} onChange={handleChange}/>
                    {errors.username && <p className="input--error">{errors.username}</p>}
                </div>
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="First name" type="text" name="firstName" value={credentials.firstName} onChange={handleChange}/>
                    {errors.firstName && <p className="input--error">{errors.firstName}</p>}
                </div>
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="Last name" type="text" name="lastName" value={credentials.lastName} onChange={handleChange}/>
                    {errors.lastName && <p className="input--error">{errors.lastName}</p>}
                </div>
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="ΑΦΜ" type="number" name="afm" value={credentials.afm} onChange={handleChange}/>
                    {errors.afm && <p className="input--error">{errors.afm}</p>}
                </div>
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="Phone number" type="number" name="phoneNumber" value={credentials.phoneNumber} onChange={handleChange}/>
                    {errors.phoneNumber && <p className="input--error">{errors.phoneNumber}</p>}
                </div>
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="Address" type="text" name="address" value={credentials.address} onChange={handleChange}/>
                    {errors.address && <p className="input--error">{errors.address}</p>}
                </div>
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="Country" type="text" name="country" value={credentials.country} onChange={handleChange}/>
                    {errors.country && <p className="input--error">{errors.country}</p>}
                </div>
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="Password" type="password" name="password" value={credentials.password} onChange={handleChange}/>
                    {errors.password && <p className="input--error">{errors.password}</p>}
                </div>
                <div className="signUpInputDiv">
                    <input className="signUpInputBox" placeholder="Confirm password" type="password" name="confirmPassword" value={credentials.confirmPassword} onChange={handleChange}/>
                    {errors.confirmPassword && <p className="input--error">{errors.confirmPassword}</p>}
                </div>
            </form>
            <div className="signup--register">
                <button className="login-register--button" onClick={handleFormSubmit} disabled={disableButton}>
                    Register!
                </button>
                <div>
                    {
                        isCorrectSubmission===1 &&
                        <p className="correct--signup--login">
                            Great, you registered! Now you should wait for the admin approval.
                        </p>
                    }
                    {
                        isCorrectSubmission===2 &&
                        <p className="wrong--signup--login">
                            {signUpError}
                        </p>
                    }
                </div>
            </div>
        </div>
    )
}

export default SignUp;