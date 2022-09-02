import React, {useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import './addItem.css'

const AddItem = () => {

    const [item,setItem] = useState({
        name: "",
        buyPrice: "",
        description: "",
        categories: [],
        minBid: "",
        endDate: "",
        startDate: "",
        ownerId: ""
    })

    const handleChange = (event) => {
        setItem({
            ...item,
            [event.target.name]: event.target.value,
        });
    };

    const handleCategory = (event) => {
        if (event.target.value === "") {
            setItem({
                ...item,
                [event.target.name]: []
            })
            return
        }
        let temp = item.categories
        if (item.categories.includes(event.target.value)) {
            temp = temp.filter(e => e !== event.target.value)
        } else {
            temp.push(event.target.value)
        }
        console.log(temp)
        setItem({
            ...item,
            [event.target.name]: temp
        })
    }

    const handleSubmit = (event) => {
        event.preventDefault()
    }

    return (
        <div>
            <NavBar/>
            <p className="add-item-welcome-text">Hello! Here you can add a new item! Please fill the blank spaces.</p>
            <div className="add-item-panel">
                <div className="item-attributes">
                    <label className="item-item-label">Name</label>
                    <input className="add-item-input-box" maxLength={45} placeholder="Name" type="text" name="name" value={item.name} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Description</label>
                    <input className="add-item-input-box" maxLength={200} placeholder="Description" type="text" name="description" value={item.description} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                <label className="item-item-label">Buyout Price</label>
                    <input className="add-item-input-box" placeholder="Buyout Price" type="number" name="buyPrice" value={item.buyPrice} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Minimum Bid</label>
                    <input className="add-item-input-box" placeholder="Minimum Bid" type="number" name="minBid" value={item.minBid} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Start Date</label>
                    <input className="add-item-input-box" type="date" name="startDate" value={item.startDate} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">End Date</label>
                    <input className="add-item-input-box" type="date" name="endDate" value={item.endDate} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Category</label>
                    <select multiple={true} name="categories" value={item.categories} onChange={handleCategory} className="select-category-box">
                        <option className="add-item-option" value="">~Empty~</option>
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
            <div className="add-item-div-btn">
                <button className="add-item-submit-btn" onClick={handleSubmit}>Place item</button>
            </div>
        </div>
    );
};

export default AddItem;