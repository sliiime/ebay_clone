import React, {useEffect, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import {useParams} from "react-router-dom";
import axios from "axios";
import './bid.css'

const Bid = () => {

    const { id } = useParams()

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

    return (
        <div>
            <NavBar/>
            <div className='bid-item-panel'>
                <div className='bid-item-container'>
                    <label className="bid-item-label">Name</label>
                    <p className="bid-item-text" >{item.name}</p>
                </div>
                <p className='bid-divider'>--------------------------------------------------------------------------</p>
                <div>
                    <label className="bid-item-label">Description</label>
                    <p className="bid-item-text" >{item.description}</p>
                </div>
                <p className='bid-divider'>--------------------------------------------------------------------------</p>
                <div>
                    <label className="bid-item-label">Start date</label>
                    <p className="bid-item-text" >{item.startDate}</p>
                </div>
                <p className='bid-divider'>--------------------------------------------------------------------------</p>
                <div>
                    <label className="bid-item-label">End date</label>
                    <p className="bid-item-text" >{item.endDate}</p>
                </div>
                <p className='bid-divider'>--------------------------------------------------------------------------</p>
                <div>
                    <label className="bid-item-label">Categories</label>
                    <p className="bid-item-text" >{item.category}</p>
                </div>
                <p className='bid-divider'>--------------------------------------------------------------------------</p>
                <div>
                    <label className="bid-item-label">Minimum Bid</label>
                    <p className="bid-item-text" >{item.minBid} €</p>
                </div>
                <p className='bid-divider'>--------------------------------------------------------------------------</p>
                <div>
                    <label className="bid-item-label">Buyout Price</label>
                    <p className="bid-item-text" >{item.buy_price} €</p>
                </div>
                <p className='bid-divider'>--------------------------------------------------------------------------</p>
                <div>
                    <label className="bid-item-label">Current Price</label>
                    <p className="bid-item-text" >{item.current_price>0 ? item.current_price : "~ €"}</p>
                </div>
            </div>
            <div>
                <input placeholder='Insert bid'/>
                <button>place bid</button>
            </div>
        </div>
    );
};

export default Bid;