const getFormData = (item, updatedItem, markerPos) => {

    let formData = new FormData()

    const endDateValue = updatedItem.endDate.replaceAll("-", "/")
    const startDateValue = updatedItem.startDate.replaceAll("-", "/")

    formData.append("startDate", startDateValue)
    formData.append("endDate", endDateValue)

    if(item.name !== updatedItem.name) {
        formData.append("name", updatedItem.name)
    }
    if(item.description !== updatedItem.description) {
        formData.append("description", updatedItem.description)
    }
    if(parseFloat(item.buyPrice) !== parseFloat(updatedItem.buyPrice)) {
        formData.append("buyPrice", updatedItem.buyPrice)
    }
    if(parseFloat(item.minBid) !== parseFloat(updatedItem.minBid)) {
        formData.append("minBid", updatedItem.minBid)
    }
    if(item.longitude !== markerPos.lng) {
        formData.append("longitude", markerPos.lng)
    }
    if(item.latitude !== markerPos.lat) {
        formData.append("latitude", markerPos.lat)
    }

    return formData
}

export default getFormData;