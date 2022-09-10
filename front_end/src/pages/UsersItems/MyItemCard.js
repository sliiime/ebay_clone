import React from 'react';
import missingImage from './missingImage.png'
import {NavLink} from "react-router-dom";

function MyItemCard ({item}) {

    const endDate = new Date(item.endDate)

    return (
        <div className="card-container">
            <div className="card-image-container">
                { item.images.length===0
                    ?
                    <img src={missingImage} alt=""/>
                    :
                    <img className='show-img-test' src={"data:"+String(item.images[0].contentType)+";base64,"+String(item.images[0].content)}/>
                }
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
                        <NavLink style={{ textDecoration: 'none'}} to={'/myitems/editItem/'+String(item.id)}>
                            <button>
                                Check
                            </button>
                        </NavLink>
                    : null
                }
            </div>
        </div>
    );
};

export default MyItemCard;