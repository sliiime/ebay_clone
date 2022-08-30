import React from 'react';
import missingImage from './missingImage.png'

function ItemCard ({item}) {
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
                    <p className="card-item-info">info2</p>
                </div>
            </div>
            <div className="card-editButton">
                <button>
                        Edit
                </button>
            </div>
        </div>
    );
};

export default ItemCard;