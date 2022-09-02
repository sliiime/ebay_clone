const validation = (item) => {

    let errors={}

    if(!credentials.email){
        errors.email="Email is required."
    } else if(!/\S+@\S+\.\S+/.test(credentials.email)) {
        errors.email="Email is invalid."
    } else { errors.email = "" }
    if(!credentials.username){
        errors.username="Username is required."
    } else { errors.username = "" }
    if(!credentials.firstName){
        errors.firstName="First name is required."
    } else { errors.firstName = "" }
    if(!credentials.lastName){
        errors.lastName="Last name is required."
    } else { errors.lastName = "" }
    if(!credentials.afm){
        errors.afm="ΑΦΜ is required."
    } else { errors.afm = "" }
    if(!credentials.phoneNumber){
        errors.phoneNumber="Phone number is required."
    } else { errors.phoneNumber = "" }
    if(!credentials.address){
        errors.address="Address is required."
    } else { errors.address = "" }
    if(!credentials.password) {
        errors.password="Password is required."
    } else if (credentials.password.length < 8) {
        errors.password="Password must be more than 8 characters."
    } else { errors.password = "" }
    if(!credentials.country){
        errors.country="Country is required."
    } else { errors.country = "" }
    if(!credentials.confirmPassword) {
        errors.confirmPassword = "Confirm password is required"
    } else { errors.confirmPassword = "" }
    if(!errors.password && !errors.confirmPassword && (credentials.password !== credentials.confirmPassword)) {
        errors.password = "Passwords do not match!"
        errors.confirmPassword = "Passwords do not match!"
    }

    return errors;
}


export default validation;