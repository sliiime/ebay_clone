import React from 'react';
import missingImage from './missingImage.png'
import {useNavigate} from "react-router-dom";

function MyItemCard ({item}) {

    const endDate = new Date(item.endDate)

    let navigate = useNavigate()

    const handleEditButton = () => {
        localStorage.setItem("itemID", JSON.stringify(item.id));
        navigate('./editItem')
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
                    <p className="card-item-info">Buy price: {item.buyPrice}€</p>
                    <p className="card-item-info">End date: {endDate.getDate()}-{endDate.getMonth()}-{endDate.getFullYear()}</p>
                </div>
            </div>
            <div className="card-editButton">
                {
                    item.numOfBids===0 ?
                    <button onClick={handleEditButton}>
                        Edit
                    </button>
                    : null
                }
            </div>
        </div>
    );
};

export default MyItemCard;