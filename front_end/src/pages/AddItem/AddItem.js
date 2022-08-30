import React, {useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import './addItem.css'
import Select, {
    components,
    MultiValueGenericProps,
    MultiValueProps,
    OnChangeValue,
    Props,
} from 'react-select';

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
        let value = Array.from(
            event.target.selectedOptions,
            (option) => option.value
        );
        console.log(value)
        setItem({
            ...item,
            [event.target.name]: value
        })
    }

    const handleTest = () => {
        console.log(item)
    }

    return (
        <div>
            <NavBar/>
            <p className="add-item-welcome-text">Hello! Here you can add a new item! Please fill the blank spaces.</p>
            <div className="items-panel">
                <label>Name</label>
                    <input maxLength={45} placeholder="Name" type="text" name="name" value={item.name} onChange={handleChange}></input>
                <label>Description</label>
                    <input maxLength={200} placeholder="Description" type="text" name="description" value={item.description} onChange={handleChange}></input>
                <label>Buyout Price</label>
                    <input placeholder="Buyout Price" type="number" name="buyPrice" value={item.buyPrice} onChange={handleChange}></input>
                <label>Minimum Bid</label>
                    <input placeholder="Minimum Bid" type="number" name="minBid" value={item.minBid} onChange={handleChange}></input>
                <label>Start Date</label>
                    <input type="date" name="startDate" value={item.startDate} onChange={handleChange}></input>
                <label>End Date</label>
                    <input type="date" name="endDate" value={item.endDate} onChange={handleChange}></input>
                <label>Category</label>
                    <Select isMulti/>
            </div>
            <button onClick={handleTest}>test</button>
        </div>
    );
};

export default AddItem;