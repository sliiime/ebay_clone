const regexValidation = (credentials) => {

    let errors={}

    if(!credentials.username){
        errors.username="Username is required."
    } else { errors.username = "" }
    if(!credentials.password) {
        errors.password="Password is required."
    } else { errors.password = "" }

    return errors;
}


export default regexValidation;