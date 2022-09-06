import React, {useEffect, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import {useParams} from "react-router-dom";
import axios from "axios";
import './bid.css'
import {MapContainer, TileLayer} from "react-leaflet";
import 'leaflet'
import {MarkerDrag} from "leaflet/src/layer/marker/Marker.Drag";

const Bid = () => {

    const { id } = useParams()

    const [usersBid, setUsersBid] = useState(0)
    const [placeBidButtonDisabled,setPlaceBidButtonDisabled] = useState(false)
    const [confirmBidButtonShowing,setConfirmBidButtonShowing] = useState(false)
    const [error,setError] = useState("")

    const [item,setItem] = useState({
        name: "",
        description: "",
        latitude: "",
        longitude: "",
        startDate: "",
        endDate: "",
        category: [],
        buy_price: "",
        current_price: "",
        minBid: ""
    })

    useEffect(() => {
        axios
            .get('http://localhost:8080/ebay_clone/api/item/'+String(id),{
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                }
            })
            .then((response) => {
                console.log(response?.data)
                setItem({
                    name: response?.data?.name,
                    description: response?.data?.description,
                    latitude: response?.data?.latitude,
                    longitude: response?.data?.longitude,
                    startDate: response?.data?.startDate,
                    endDate: response?.data?.endDate,
                    category: response?.data?.category,
                    buy_price: response?.data?.buyPrice,
                    current_price: response?.data?.bestBid,
                    minBid: response?.data?.minBid
                })
            })
    },[id])

    const handleUsersBid = (event) => {
        setPlaceBidButtonDisabled(false)
        setConfirmBidButtonShowing(false)
        setUsersBid(event.target.value)
    }

    const handlePlaceBidButton = (event) => {
        event.preventDefault()
        if (usersBid<=0) {
            setError("Invalid bid.")
        } else if (usersBid<item.minBid) {
            setError("Your bid must be greater or equal than the minimum bid.")
        } else if (usersBid<=item.current_price) {
            setError("Your bid must be greater than the current price.")
        } else {
            setPlaceBidButtonDisabled(true)
            setConfirmBidButtonShowing(true)
        }
    }

    const handleConfirmButton = (event) => {
        event.preventDefault()
        axios
            .post('http://localhost:8080/ebay_clone/api/bid',{
                itemId: id,
                price: usersBid
            })
            .then((response) => {
                console.log(response)
                window.location.reload(false)
            })
            .catch((error) => {
                console.log(error)
                window.location.reload(false)
            })

    }

    return (
        <div>
            <NavBar/>
            <div className='bid-item-panel'>
                <div className='bid-item-container'>
                    <label className="bid-item-label">Name</label>
                    <p className="bid-item-text" >{item.name}</p>
                </div>
                <div>
                    <label className="bid-item-label">Description</label>
                    <p className="bid-item-text" >{item.description}</p>
                </div>
                <div>
                    <label className="bid-item-label">Start date</label>
                    <p className="bid-item-text" >{item.startDate}</p>
                </div>
                <div>
                    <label className="bid-item-label">End date</label>
                    <p className="bid-item-text" >{item.endDate}</p>
                </div>
                <div>
                    <label className="bid-item-label">Categories</label>
                    <p className="bid-item-text" >{item.category}</p>
                </div>
                <div>
                    <label className="bid-item-label">Minimum Bid</label>
                    <p className="bid-item-text" >{item.minBid} €</p>
                </div>
                <div>
                    <label className="bid-item-label">Buyout Price</label>
                    <p className="bid-item-text" >{item.buy_price} €</p>
                </div>
                <div>
                    <label className="bid-item-label">Current Price</label>
                    <p className="bid-item-text" >{item.current_price>0 ? item.current_price : "~ €"}</p>
                </div>
                <MapContainer
                    center={[37.983810, 23.727539]}
                    zoom={13}
                    scrollWheelZoom={true}>
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />
                </MapContainer>
            </div>
            <div className='bid-input-btn'>
                <input className='bid-input' placeholder='Insert bid' type='number' value={usersBid} onChange={handleUsersBid}/>
                <button className='bid-btn' disabled={placeBidButtonDisabled} onClick={handlePlaceBidButton}>place bid</button>
                {confirmBidButtonShowing && <button className='bid-btn-confirm' onClick={handleConfirmButton}>Confirm</button>}
            </div>
        </div>
    );
};

export default Bid;