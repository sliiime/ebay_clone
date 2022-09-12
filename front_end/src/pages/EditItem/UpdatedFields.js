const getFormData = (item, updatedItem, markerPos) => {

    let formData = new FormData()

    const endDateValue = item.endDate.replaceAll("-", "/")
    const startDateValue = item.startDate.replaceAll("-", "/")

    if(item.name !== updatedItem.name) {
        formData.append("name", updatedItem.name)
    }
    if(item.description !== updatedItem.description) {
        formData.append("description", updatedItem.description)
    }
    if(parseFloat(item.buyPrice) !== parseFloat(updatedItem.buyPrice)) {
        fields.push("BUY_PRICE")
        data["buyPrice"] = updatedItem.buyPrice
    }
    if(parseFloat(item.minBid) !== parseFloat(updatedItem.minBid)) {
        fields.push("MIN_BID")
        data["minBid"] = updatedItem.minBid
    }
    if(item.longitude !== updatedItem.longitude) {
        fields.push("LONGITUDE")
        data["longitude"] = updatedItem.longitude
    }
    if(item.latitude !== updatedItem.latitude) {
        fields.push("LATITUDE")
        data["latitude"] = updatedItem.latitude
    }

    data["toUpdate"] = fields

    return data
}

export default getFormData;