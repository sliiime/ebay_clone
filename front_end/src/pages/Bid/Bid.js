import React, {useEffect, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import {useParams} from "react-router-dom";
import axios from "axios";
import './bid.css'
import { MapContainer, TileLayer, Marker } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import marker from './marker.svg';
import categoryItems from "../Search/CategoryItems";



const Bid = () => {

    const myIcon = new L.Icon({
        iconUrl: marker,
        iconRetinaUrl: marker,
        popupAnchor:  [-0, -0],
        iconSize: [20,20],
    });

    const { id } = useParams()

    const [usersBid, setUsersBid] = useState(0)
    const [placeBidButtonDisabled,setPlaceBidButtonDisabled] = useState(false)
    const [confirmBidButtonShowing,setConfirmBidButtonShowing] = useState(false)
    const [error,setError] = useState("")
    const [itemCategories, setItemCategories] = useState("")

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
                setPosition([response?.data?.longitude,response?.data?.latitude])
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
                //console.log(response?.data?.category)
                setItemCategories(response?.data?.category.join(', '))
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
            setError("")
            setPlaceBidButtonDisabled(true)
            setConfirmBidButtonShowing(true)
        }
    }

    const handleConfirmButton = (event) => {
        event.preventDefault()
        console.log(id)
        console.log(usersBid)
        axios
            .post('http://localhost:8080/ebay_clone/api/bid',{
                itemId: String(id),
                price: String(usersBid)
            }, {
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                }
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

    //[37.983810, 23.727539]
    const [position,setPosition] = useState([])

    return (
        <div>
            <NavBar/>
            <div className='bid-item-body'>
                <div>
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
                            <p className="bid-item-text" >{itemCategories}</p>
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
                    </div>
                    <div className='bid-input-btn'>
                        <input className='bid-input' placeholder='Insert bid' type='number' value={usersBid} onChange={handleUsersBid}/>
                        <button className='bid-btn' disabled={placeBidButtonDisabled} onClick={handlePlaceBidButton}>place bid</button>
                        {error!=="" && <p className="bid-input-error">{error}</p>}
                        {confirmBidButtonShowing && <button className='bid-btn-confirm' onClick={handleConfirmButton}>Confirm</button>}

                    </div>
                </div>
                {
                    (position.length!==0) ?
                    <div className='bid-item-map'>
                        <link
                            rel="stylesheet"
                            href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
                            integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
                            crossOrigin=""
                        />
                        <MapContainer center={position} zoom={14} scrollWheelZoom={true}>
                            <TileLayer
                                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                            />
                            <Marker key={`marker-${id}`} position={position} icon={myIcon}/>
                        </MapContainer>
                    </div> : null
                }
            </div>
        </div>
    );
};

export default Bid;