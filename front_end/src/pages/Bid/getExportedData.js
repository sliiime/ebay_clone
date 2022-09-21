import {useState} from "react";
import axios from "axios";

function getExportedData (item) {

    let auction = {}
    auction["Name"] = item.name
    auction["Category"] = item.category
    auction["CurrentPrice"] = item.current_price
    auction["MinBid"] = item.minBid
    auction["Number_of_Bids"] = item.numOfBids
    auction["Country"] = item.country
    auction["Started"] = item.startDate
    auction["Ends"] = item.endDate
    auction["Description"] = item.description
    axios
        .get("http://localhost:8080/ebay_clone/api/user/"+String(item.sellerId), {
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('accessToken')),
            }
        })
        .then((response) => {
            auction["Seller"] = {
                Rating: response?.data?.rating,
                Username: response?.data?.username
            }
        })
        .catch((error) => {
            console.log(error)
        })
    axios
        .get("http://localhost:8080/ebay_clone/api/bid/item/"+String(item.id), {
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('accessToken')),
            }
        })
        .then((response) => {
            auction["Bids"] = []
            response?.data?.forEach(bid => {
                auction["Bids"].push({
                    BidderId: bid.bidderId,
                    Time: bid.submissionDate,
                    Amount: bid.price
                })
            })
        })
        .catch((error) => {
            console.log(error)
        })
    //console.log(auction)
    return auction
}

export default getExportedData