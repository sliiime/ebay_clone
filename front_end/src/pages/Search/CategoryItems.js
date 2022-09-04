import React, {useEffect, useState} from 'react';
import axios from "axios";
import SearchItemCard from "./SearchItemCard";

const CategoryItems = ({category}) => {
    const [numOfPages, setNumOfPages] = useState(0)
    const [currentPage,setCurrentPage] = useState(1)
    const [itemsShown, setItemsShown] = useState([])
    const [currentCategory,setCurrentCategory] = useState([])

    useEffect(() => {
        setCurrentCategory([category])
        setNumOfPages(0)
        setCurrentPage(1)
    },[category])

    useEffect(() => {
        console.log("will request for " + currentCategory[0])
        axios
            .post("http://localhost:8080/ebay_clone/api/item/search/?p=" + (currentPage - 1), {
                filters: [{
                    field: "category",
                    operator: "IN",
                    values: ["Category 1"]
                }]
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
    },[currentPage,currentCategory])

    const handleNextClick = () => {
        setCurrentPage(currentPage+1)
    }

    const handlePrevClick = () => {
        setCurrentPage(currentPage-1)
    }

    return (
        <div>
            <div className="items-panel">
                {
                    itemsShown.length ?
                        itemsShown.map( item =>
                            <div key={item.id}>
                                <SearchItemCard item={item}/>
                            </div>
                        ) : null
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

export default CategoryItems;