import WelcomeLoginBox from "../../components/WelcomeLoginBox";
import React, {useState , useEffect} from "react";
import {useNavigate} from "react-router-dom";
import regexValidation from "./regexValidation";
import errorsExist from "./errorsExist";
import axios from "axios";

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
        country:""
    });

    let navigate = useNavigate();

    const [errors,setErrors] = useState({});
    const [submitButtonPressed,setSubmitButtonPressed] = useState(false)
    const [isCorrectSubmition,setIsCorrectSubmition] = useState(0)

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
        if(errorsExist(submitButtonPressed,errors)) {
            return;
        } else {
            axios
                .post("http://localhost:8080/ebay_clone/api/user/", {
                    username : credentials.username,
                    password : credentials.password,
                    name     : credentials.firstName,
                    surname  : credentials.lastName,
                    address  : credentials.address,
                    email    : credentials.email,
                    afm      : credentials.afm,
                    phone    : credentials.phoneNumber
                })
                .then((response) => {
                    console.log("response")
                })
                .catch((error) => console.log("error"));
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
            </form>
            <div className="signup--register">
                <button className="login-register--button" onClick={handleFormSubmit}>
                    Register!
                </button>
                {isCorrectSubmition===1 &&
                    <p className="correct--signup">
                        Great, you registered! Now you should wait for the admin approval.
                    </p>}
                {isCorrectSubmition===2 &&
                    <p className="wrong--signup">
                        Something went wrong! Try again.
                    </p>}
            </div>
        </div>
    )
}

export default SignUp;