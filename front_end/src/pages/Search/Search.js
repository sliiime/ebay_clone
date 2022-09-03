import React, {useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import './search.css'
import {Link} from "react-router-dom";
import AllItems from "./AllItems";
import CategoryItems from "./CategoryItems";
import SearchedItems from "./SearchedItems";

const Search = () => {

    const [search,setSearch] = useState({
        text: "",
        minPrice: "",
        maxPrice: "",
        categories: [],
        country: ""
    })

    const [allItems,setAllItems] = useState(true)
    const [categoryItems, setCategoryItems] = useState(false)
    const [searchedItems, setSearchedItems] = useState(false)

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

    const handleCategory = (event) => {
        if (event.target.value === "") {
            setSearch({
                ...search,
                [event.target.name]: []
            })
            return
        }
        let temp = search.categories
        if (search.categories.includes(event.target.value)) {
            temp = temp.filter(e => e !== event.target.value)
        } else {
            temp.push(event.target.value)
        }
        console.log(temp)
        setSearch(
            {
            ...search,
            [event.target.name]: temp
        })
    }


    const [currentCategory, setCurrentCategory] = useState("")
    const handleCategoryLink = (category) => {
        setCurrentCategory(category)
        setAllItems(false)
        setSearchedItems(false)
        setCategoryItems(true)
    }

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
                            <label>Min Price</label>
                            <input className="search-option-input" placeholder="Minimum price" type="number" name="minPrice" value={search.minPrice} onChange={handleSearchChange}/>
                        </div>
                        <div className="search-option">
                            <label>Max Price</label>
                            <input className="search-option-input" placeholder="Maximum price" type="number" name="maxPrice" value={search.maxPrice} onChange={handleSearchChange}/>
                        </div>
                        <div className="search-option">
                            <label>Country</label>
                            <input className="search-option-input" placeholder="Country" type="text" name="country" value={search.country} onChange={handleSearchChange}/>
                        </div>
                        <div className="search-option">
                            <label>Categories</label>
                            <select multiple={true} name="categories" value={search.categories} onChange={handleCategory} className="select-category-box">
                                <option className="add-item-option" value="">~Empty selection~</option>
                                <option className="add-item-option" value="Technology">Technology</option>
                                <option className="add-item-option" value="Home & Kitchen">Home & Kitchen</option>
                                <option className="add-item-option" value="Beauty & Personal Care">Beauty & Personal Care</option>
                                <option className="add-item-option" value="Toys & Games">Toys & Games</option>
                                <option className="add-item-option" value="Clothing, Shoes & Jewelry">Clothing, Shoes & Jewelry</option>
                                <option className="add-item-option" value="Sports & Outdoors">Sports & Outdoors</option>
                                <option className="add-item-option" value="Books">Books</option>
                                <option className="add-item-option" value="Other">Other</option>
                            </select>
                        </div>
                    </div>
                    : null
                }
                <div className="search-option-links">
                    <Link className="search-link" to="" onClick={() => handleCategoryLink("Technology")}>Technology</Link>
                    <Link className="search-link" to="" onClick={() => handleCategoryLink("Home & Kitchen")}>Home & Kitchen</Link>
                    <Link className="search-link" to="" onClick={() => handleCategoryLink("Beauty & Personal Care")}>Beauty & Personal Care</Link>
                    <Link className="search-link" to="" onClick={() => handleCategoryLink("Toys & Games")}>Toys & Games</Link>
                    <Link className="search-link" to="" onClick={() => handleCategoryLink("Clothing, Shoes & Jewelry")}>Clothing, Shoes & Jewelry</Link>
                    <Link className="search-link" to="" onClick={() => handleCategoryLink("Sports & Outdoors")}>Sports & Outdoors</Link>
                    <Link className="search-link" to="" onClick={() => handleCategoryLink("Books")}>Books</Link>
                </div>
            </div>
            <div className="search-show-items">
                { allItems && <AllItems/> }
                { categoryItems && <CategoryItems category={currentCategory}/> }
                { searchedItems && <SearchedItems/> }
            </div>
        </div>
    );
};

export default Search;