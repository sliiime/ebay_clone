import React, {useEffect, useState} from 'react';
import axios from "axios";
import SearchItemCard from "./SearchItemCard";
import filterSearch from "./filterSearch";

const SearchedItems = ({search}) => {
    const [numOfPages, setNumOfPages] = useState()
    const [currentPage,setCurrentPage] = useState(1)
    const [itemsShown, setItemsShown] = useState([])
    const [filters,setFilters] = useState([])

    useEffect(() => {
        setFilters(filterSearch(search))
        setNumOfPages(0)
        setCurrentPage(1)
    },[search])

    useEffect(() => {
        axios
            .post("http://localhost:8080/ebay_clone/api/item/search/?p=" + (currentPage - 1), {
                filters: {filters}
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
    },[currentPage,filters])

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

export default SearchedItems;