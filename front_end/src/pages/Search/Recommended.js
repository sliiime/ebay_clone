import React, {useEffect, useState} from 'react';
import SearchItemCard from "./SearchItemCard";
import axios from "axios";
import NavBar from "../MainMenu/Navbar";

const Recommended = () => {

    const [recommendedItems,setRecommendedItems] = useState([])

    useEffect(() => {
        axios
            .get("http://localhost:8080/ebay_clone/api/item/recommendations",{
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                }
            })
            .then((response) => {
                console.log(response?.data?.content)
                setRecommendedItems([])
                setRecommendedItems(response?.data?.content)
                //console.log(response.data.content.images===undefined ? "null" : "undefined")
            })
            .catch((error) => {
                console.log(error)
            })
    },[])

    return (
        <div>
            <NavBar/>
            <div className="items-panel">
                {
                    recommendedItems.length ?
                        recommendedItems.map( item =>
                            <div key={item.id}>
                                <SearchItemCard item={item}/>
                            </div>
                        ) : <p>No items found!</p>
                }
            </div>
        </div>
    );
};

export default Recommended;