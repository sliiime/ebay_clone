const getFormData = (item, updatedItem, markerPos,images) => {

    let formData = new FormData()
    let toUpdate = []
    toUpdate.push("START_DATE")
    toUpdate.push("END_DATE")
    toUpdate.push("CATEGORIES")

    const endDateValue = updatedItem.endDate.replaceAll("-", "/")
    const startDateValue = updatedItem.startDate.replaceAll("-", "/")

    formData.append("startDate", startDateValue)
    formData.append("endDate", endDateValue)
    updatedItem.categories.forEach(category => formData.append("categories",category))
    //formData.append("categories",updatedItem.categories)

    // formData.append("toUpdate","START_DATE")
    // formData.append("toUpdate","END_DATE")
    // formData.append("toUpdate","CATEGORY")

    if(item.name !== updatedItem.name) {
        formData.append("name", updatedItem.name)
        toUpdate.push("NAME")
    }
    if(item.description !== updatedItem.description) {
        formData.append("description", updatedItem.description)
        toUpdate.push("DESCRIPTION")
    }
    if(parseFloat(item.buyPrice) !== parseFloat(updatedItem.buyPrice)) {
        formData.append("buyPrice", updatedItem.buyPrice)
        toUpdate.push("BUY_PRICE")
    }
    if(parseFloat(item.minBid) !== parseFloat(updatedItem.minBid)) {
        formData.append("minBid", updatedItem.minBid)
        toUpdate.push("MIN_BID")
    }
    if(item.longitude !== markerPos.lng) {
        formData.append("longitude", markerPos.lng)
        toUpdate.push("LONGITUDE")
    }
    if(item.latitude !== markerPos.lat) {
        formData.append("latitude", markerPos.lat)
        toUpdate.push("LATITUDE")
    }
    /*if(images.length>0) {
        images.forEach(image => formData.append("images",image))
        toUpdate.push("IMAGES")
    }*/

    toUpdate.forEach(update => formData.append("toUpdate",update))
    return formData
}

export default getFormData;