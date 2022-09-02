import React, {useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import './search.css'

const Search = () => {

    const [search,setSearch] = useState({
        text: "",
        minPrice: "",
        maxPrice: "",
        category: "",
        country: ""
    })

    const [extendedSearch, setExtendedSearch] = useState(false)
    const handleExtendButton = () => {
        setExtendedSearch(!extendedSearch)
    }

    const handleSearchChange = (event) => {
        setSearch({
            ...search,
            [event.target.name]: event.target.value,
        });
    };

    return (
        <div>
            <NavBar/>
            <div className="search-div">
                <div className="search-bar">
                    <button className="search-bar-more-options-btn" onClick={handleExtendButton}>☰</button>
                    <input className="search-bar-input" placeholder="Search" type="text" name="text" value={search.text} onChange={handleSearchChange}/>
                    <button className="search-bar-input-button">🔍︎</button>
                </div>
                {   extendedSearch ?
                    <div className="search-all-options">
                        <div className="search-option">
                            <label>Min Price:   </label>
                            <input className="search-option-input" placeholder="Minimum price" type="number" name="minPrice" value={search.minPrice} onChange={handleSearchChange}/>
                        </div>
                        <div className="search-option">
                            <label>Max Price:   </label>
                            <input className="search-option-input" placeholder="Maximum price" type="number" name="maxPrice" value={search.maxPrice} onChange={handleSearchChange}/>
                        </div>
                        <div className="search-option">
                            <label>Country: </label>
                            <input className="search-option-input" placeholder="Country" type="text" name="country" value={search.country} onChange={handleSearchChange}/>
                        </div>
                        <div className="search-option">
                            <label>Categories:   </label>
                            <input className="search-option-input" placeholder="Starting price" type="number" name="minPrice" value={search.minPrice} onChange={handleSearchChange}/>
                        </div>
                    </div>
                    : null
                }
            </div>
            <div>

            </div>
        </div>
    );
};

export default Search;