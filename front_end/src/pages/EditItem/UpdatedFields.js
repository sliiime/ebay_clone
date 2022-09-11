const UpdatedData = (item,updatedItem) => {

    let data = {
        startDate: updatedItem.startDate,
        endDate: updatedItem.endDate,
        category: updatedItem.categories
    }

    let fields = ["START_DATE","END_DATE","CATEGORY"]

    if(item.name !== updatedItem.name) {
        fields.push("NAME")
        data["name"] = updatedItem.name
    }
    if(item.description !== updatedItem.description) {
        fields.push("DESCRIPTION")
        data["description"] = updatedItem.description
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

export default UpdatedData;