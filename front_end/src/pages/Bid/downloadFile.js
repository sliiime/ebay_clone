import axios from "axios";
import downloadJSON from "./downloadJSON";
import downloadXML from "./downloadXML";

const  downloadFile = (item,type) => {
    let auction = {}
    axios
        .get("http://localhost:8080/ebay_clone/api/user/"+String(item.sellerId), {
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('accessToken')),
            }
        })
        .then((responseSeller) => {
            axios
                .get("http://localhost:8080/ebay_clone/api/bid/item/"+String(item.id), {
                    headers: {
                        'Authorization': JSON.parse(localStorage.getItem('accessToken')),
                    }
                })
                .then((responseBid) => {
                    auction["Seller"] = {
                        Rating: responseSeller?.data?.rating,
                        Username: responseSeller?.data?.username
                    }
                    auction["Bids"] = []
                    responseBid?.data?.forEach(bid => {
                        auction["Bids"].push({
                            BidderId: bid.bidderId,
                            Time: bid.submissionDate,
                            Amount: bid.price
                        })
                    })
                    auction["ItemId"] = item.id
                    auction["Name"] = item.name
                    auction["Categories"] = item.category
                    auction["CurrentPrice"] = item.current_price
                    auction["MinBid"] = item.minBid
                    auction["Number_of_Bids"] = item.numOfBids
                    auction["Country"] = item.country
                    auction["Started"] = item.startDate
                    auction["Ends"] = item.endDate
                    auction["Description"] = item.description
                    if (type==="json") {
                        downloadJSON({
                            data: JSON.stringify(auction),
                            fileName: "auction_item_"+String(item.id)+".json",
                            fileType: 'text/json',
                        })
                    } else {
                        downloadXML(auction,item.id)
                    }
                })
                .catch((error) => {
                    console.log(error)
                })

        })
        .catch((error) => {
            console.log(error)
        })
}

export default downloadFile