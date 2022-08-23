const regexValidation = (credentials) => {

    let errors={}

    if(!credentials.email){
        errors.email="Email is required."
    } else { errors.email = "" }
    if(!credentials.password) {
        errors.password="Password is required."
    } else { errors.password = "" }

    return errors;
}


export default regexValidation;