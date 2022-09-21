import React, {useEffect, useState, useSyncExternalStore} from 'react';
import NavBar from "../MainMenu/Navbar";
import {useParams} from "react-router-dom";
import axios from "axios";
import './bid.css'
import { MapContainer, TileLayer, Marker } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import marker from './marker.svg';
import getExportedData from "./getExportedData";
import downloadXML from "./downloadXML";
import downloadJSON from "./downloadJSON";

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

    const [images,setImages] = useState([])
    const [currentImage,setCurrentImage] = useState(0)

    const [startDate,setStartDate] = useState(null)
    const [endDate,setEndDate] = useState(null)


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
                    minBid: response?.data?.minBid,
                    status: response?.data?.status,
                    numOfBids: response?.data?.numOfBids,
                    country: response?.data?.country,
                    sellerId: response?.data?.sellerId,
                    id: response?.data?.id
                })
                //console.log(response?.data?.category)
                setItemCategories(response?.data?.category.join(', '))
                setImages(response?.data?.images)
                /*if ((response?.data?.longitude!==null)&&(response?.data?.latitude!==null)) {
                    setPosition([response?.data?.longitude,response?.data?.latitude])
                }*/
                setPosition([37.983810, 23.727539])
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
        } else if (item.status==='BOUGHT_BUYOUT'){
            setError("This item is already bought.")
        }else {
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

    const handleNextClick = () => {
        const temp = currentImage
        currentImage===images.length-1 ? setCurrentImage(0) : setCurrentImage(temp+1)
    }

    const handlePrevClick = () => {
        const temp = currentImage
        currentImage===0 ? setCurrentImage(images.length-1) : setCurrentImage(temp-1)    }
    //[37.983810, 23.727539]
    const [position,setPosition] = useState([])

    const handleExportJson = (event) => {
        event.preventDefault()
        let auction = getExportedData(item)
        downloadJSON(auction,id)
    }

    const handleExportXML = (event) => {
        event.preventDefault()
        const auction = getExportedData(item)
        downloadXML(auction,id)
    }

    return (
        <div>
            <NavBar/>
            <div className='bid-body'>
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
                                <p className="bid-item-text" >{new Date(item.startDate).getDate()}-{new Date(item.startDate).getMonth()+1}-{new Date(item.startDate).getFullYear()}</p>
                            </div>
                            <div>
                                <label className="bid-item-label">End date</label>
                                <p className="bid-item-text" >{new Date(item.endDate).getDate()}-{new Date(item.endDate).getMonth()+1}-{new Date(item.endDate).getFullYear()}</p>
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
                            <div className='bid-export-auction'>
                                <button className='bid-export-json' onClick={handleExportJson}>Export to JSON</button>
                                <button className='bid-export-xml' onClick={handleExportXML}>Export to XML</button>
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
                        (images.length!==0) ?
                            <div className='bid-img-component'>
                                <img className='bid-show-img' src={"data:"+String(images[currentImage].contentType)+";base64,"+String(images[currentImage].content)}/>
                                <div className="bid-change-image-btn">
                                    <button className="change-image-btn" onClick={handlePrevClick}>←</button>
                                    <button className="change-image-btn" onClick={handleNextClick}>→</button>
                                </div>
                            </div>
                            : null
                    }
                    {
                        (position.length!==0) ?
                        <div className='bid-item-map'>
                            <link
                                rel="stylesheet"
                                href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
                                integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
                                crossOrigin=""
                            />
                            <MapContainer center={position} zoom={13} scrollWheelZoom={true}>
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
        </div>
    );
};

export default Bid;