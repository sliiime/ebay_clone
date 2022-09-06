const errorChecking = (item, bid) => {
    console.log(item)
    console.log(bid)
    if (bid<=0) {
        console.log(1)
        const error = "Invalid bid."
        return error
    }
    if (bid<item.minBid) {
        console.log(2)
        const error = "Your bid must be greater or equal than the minimum bid."
        return error
    }

    if(bid<=item.current_price) {
        console.log(3)
        const error = "Your bid must be greater than the current price."
        return error
    }
    console.log(4)
    return ""
}

export default errorChecking;