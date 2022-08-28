const errorsExist = (submitButtonPressed, errors) => {
    let errorExist = true
    if( submitButtonPressed
        && errors.username === ""
        && errors.password === "" ) {errorExist = false}
    return errorExist
}

export default errorsExist;