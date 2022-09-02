const errorsExist = (submitButtonPressed, errors) => {
    let errorExist = true
    if( submitButtonPressed
        && errors.name === ""
        && errors.buyPrice === ""
        && errors.description === ""
        && errors.categories === ""
        && errors.minBid === ""
        && errors.endDate === ""
        && errors.startDate === "") {errorExist = false}
    return errorExist
}

export default errorsExist;