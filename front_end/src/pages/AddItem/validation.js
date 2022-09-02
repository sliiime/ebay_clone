const validation = (item) => {

    let errors = {}
    const today = new Date()

    if (!item.name) {
        errors.name = "Name is required."
    } else {
        errors.name = ""
    }

    if (!item.description) {
        errors.description = "Description is required."
    } else {
        errors.description = ""
    }

    if (!item.minBid) {
        errors.minBid = "Minimum bid is required."
    } else if (item.minBid <= 0) {
        errors.minBid = "Enter a valid Minimum bid."
    } else if (item.minBid >= item.buyPrice) {
        errors.minBid = "Minimum bid must be less than Buyout price."
    } else { errors.minBid = "" }

    if(!item.buyPrice){
        errors.buyPrice="Buyout price is required."
    } else if (item.buyPrice <= 0){
        errors.buyPrice="Enter a valid Buyout price."
    } else if (item.buyPrice <= item.minBid){
        errors.buyPrice="Buyout price must be greater than Minimum bid."
    } else { errors.buyPrice = "" }

    if(!item.startDate){
        errors.startDate="Starting date is required."
    } else if (Date.parse(item.startDate) < Date.parse(today)) {
        errors.startDate="Enter a valid Starting date."
    } else if (Date.parse(item.startDate) > Date.parse(item.endDate)) {
        errors.startDate="Starting date must be before End date."
    } else { errors.startDate = "" }

    if(!item.endDate){
        errors.endDate="End date is required."
    } else if (Date.parse(item.endDate) < Date.parse(today)) {
        errors.endDate="Enter a valid End date."
    } else if (Date.parse(item.endDate) <= Date.parse(item.startDate)) {
        errors.endDate="End date must be before Starting date."
    } else { errors.endDate = "" }

    if(item.categories.length === 0){
        errors.categories="Item should belong to at least one category."
    } else { errors.categories = "" }


    return errors;
}


export default validation;