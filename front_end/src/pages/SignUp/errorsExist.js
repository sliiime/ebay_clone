const errorsExist = (submitButtonPressed, errors) => {
    let errorExist = true
    if( submitButtonPressed
        && errors.email === ""
        && errors.username === ""
        && errors.firstName === ""
        && errors.lastName === ""
        && errors.afm === ""
        && errors.phoneNumber === ""
        && errors.address === ""
        && errors.country === ""
        && errors.password === "" ) {errorExist = false}
    return errorExist
}

export default errorsExist;