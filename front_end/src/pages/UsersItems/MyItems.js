import React, {useEffect, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import axios from "axios";
import './myitems.css'
import MyItemCard from "./MyItemCard";
import {Link} from "react-router-dom";

const MyItems = () => {

    const [numOfPages, setNumOfPages] = useState()
    const [currentPage,setCurrentPage] = useState(1)
    const [itemsShown, setItemsShown] = useState([])

    useEffect( () => {
        axios
            .get('http://localhost:8080/ebay_clone/api/item/user/?p=' + (currentPage-1),{
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                }
            })
            .then((response) => {
                console.log(response?.data)
                const num = response?.data?.totalPages
                setNumOfPages(num);
                setItemsShown([])
                setItemsShown(response?.data?.content)
            })
            .catch((error) => {
                console.log(error)
            })
    }, [currentPage]);

    const handleNextClick = () => {
        setCurrentPage(currentPage+1)
    }

    const handlePrevClick = () => {
        setCurrentPage(currentPage-1)
    }

    return (
        <div>
            <NavBar/>
            <div className="add-item">
                <Link className="add-item-button" to="addItem">Add an item!</Link>
            </div>
            <div className="items-panel">
                {
                    itemsShown.length ?
                        itemsShown.map( item =>
                            <div key={item.id}>
                                <MyItemCard item={item}/>
                            </div>
                        ) : <p>You have no items!</p>
                }
            </div>
            <div className="change-page-container">
                <button className="change-page-btn" disabled={currentPage === 1} onClick={handlePrevClick}>←</button>
                <p className="current-page">{currentPage}</p>
                <button className="change-page-btn" disabled={currentPage === numOfPages} onClick={handleNextClick}>→</button>
            </div>
        </div>
    );
};

export default MyItems;