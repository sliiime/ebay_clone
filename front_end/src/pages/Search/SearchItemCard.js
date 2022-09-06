import React from 'react';
import './search.css'
import {NavLink, useNavigate} from "react-router-dom";
import missingImage from "../UsersItems/missingImage.png";

const SearchItemCard = ({item}) => {

    const endDate = new Date(item.endDate)

    let navigate = useNavigate()

    const handleEditButton = () => {
        //localStorage.setItem("itemID", JSON.stringify(item.id));
        //navigate('./checkItem')
    }

    return (
        <div className="card-container">
            <div className="card-image-container">
                <img src={missingImage} alt=""/>
            </div>
            <div className="card-content">
                <div className="card-title">
                    <h3>{item.name}</h3>
                </div>
                <div className="card-body">
                    <p className="card-item-info">Description: {item.description}</p>
                    <p className="card-item-info">Current bid: {item.bestBid ? String(item.bestBid) : "~"} €</p>
                    <p className="card-item-info">Minimum bid: {item.minBid} €</p>
                    <p className="card-item-info">Buyout price: {item.buyPrice} €</p>
                    <p className="card-item-info">End date: {endDate.getDate()}-{endDate.getMonth()+1}-{endDate.getFullYear()}</p>
                    <p className="card-item-info">Status: {item.status==="ONGOING" ? "On going" : "Finished"}</p>
                </div>
            </div>
            <div className="card-editButton">
                {
                    item.status === "ONGOING" ?
                        <NavLink style={{ textDecoration: 'none'}} to={'/search/bid/'+String(item.id)}>
                            <button onClick={handleEditButton}>
                                Check
                            </button>
                        </NavLink>
                        : null
                }
            </div>
        </div>
    );
};

export default SearchItemCard;