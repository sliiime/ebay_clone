import React, {useEffect, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import {useNavigate, useParams} from "react-router-dom";
import validation from "../AddItem/validation";
import errorsExist from "../AddItem/errorsExist";
import axios from "axios";

const EditItem = () => {

    const [item,setItem] = useState({
        name: "",
        buyPrice: "",
        description: "",
        categories: [],
        minBid: "",
        endDate: "",
        startDate: "",
        longitude: "",
        latitude: ""
    })

    const { id } = useParams()

    let navigate = useNavigate()

    const [errors,setErrors] = useState({});
    const addItemError = "Something went wrong. Try again."
    const [disableButton, setDisableButton] = useState(false);
    const [submitButtonPressed,setSubmitButtonPressed] = useState(false)
    const [isCorrectSubmission,setIsCorrectSubmission] = useState(0)

    const [images,setImages] = useState([])

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
        setErrors(validation(item))
        setSubmitButtonPressed(true)
    }

    useEffect( () => {
        axios
            .get("http://localhost:8080/ebay_clone/api/item/"+String(id),{
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                }
            })
            .then((response) => {
                console.log(response?.data)
                setItem({
                    name: response?.data?.name,
                    buyPrice: response?.data?.buyPrice,
                    description: response?.data?.description,
                    categories: [],
                    minBid: response?.data?.minBid,
                    endDate: "",
                    startDate: "",
                    longitude: response?.data?.longitude ? response?.data?.longitude : "",
                    latitude: response?.data?.latitude ? response?.data?.latitude : ""
                })
                setImages(response?.data?.images)
            })
            .catch((error) => {
                console.log(error)
            })
    },[])

    useEffect( () => {
        if(!errorsExist(submitButtonPressed,errors)) {
            const itemID = localStorage.getItem('itemID')
            axios
                .put("http://localhost:8080/ebay_clone/api/item/" + String(itemID),{
                    name: item.name,
                    buyPrice: item.buyPrice,
                    description: item.description,
                    categories: item.categories,
                    minBid: item.minBid,
                    startDate: item.startDate.replaceAll("-","/"),
                    endDate: item.endDate.replaceAll("-","/"),
                    longitude: item.longitude,
                    latitude: item.latitude
                }, {
                    headers: {
                        'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                    }
                })
                .then((response) => {
                    localStorage.removeItem('itemID')
                    setIsCorrectSubmission(1);
                    setDisableButton(true);
                    const timer = setTimeout(() => navigate('../'), 2000);
                    return () => clearTimeout(timer);
                })
                .catch((error) => {
                    console.log(error)
                    setIsCorrectSubmission(2);
                });
        }
    }, [submitButtonPressed,errors]);

    return (
        <div>
            <NavBar/>
            <p className="add-item-welcome-text">Feel free to edit your item.</p>
            <div className="add-item-panel">
                <div className="item-attributes">
                    <label className="item-item-label">Name</label>
                    {errors.name && <p className="add-item-input-error">{errors.name}</p>}
                    <input className="add-item-input-box" maxLength={45} placeholder="Name" type="text" name="name" value={item.name} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Description</label>
                    {errors.description && <p className="add-item-input-error">{errors.description}</p>}
                    <input className="add-item-input-box" maxLength={200} placeholder="Description" type="text" name="description" value={item.description} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Buyout Price</label>
                    {errors.buyPrice && <p className="add-item-input-error">{errors.buyPrice}</p>}
                    <input className="add-item-input-box" placeholder="Buyout Price" type="number" name="buyPrice" value={item.buyPrice} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Minimum Bid</label>
                    {errors.minBid && <p className="add-item-input-error">{errors.minBid}</p>}
                    <input className="add-item-input-box" placeholder="Minimum Bid" type="number" name="minBid" value={item.minBid} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Start Date</label>
                    {errors.startDate && <p className="add-item-input-error">{errors.startDate}</p>}
                    <input className="add-item-input-box" type="date" name="startDate" value={item.startDate} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">End Date</label>
                    {errors.endDate && <p className="add-item-input-error">{errors.endDate}</p>}
                    <input className="add-item-input-box" type="date" name="endDate" value={item.endDate} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Category</label>
                    {errors.categories && <p className="add-item-input-error">{errors.categories}</p>}
                    <select multiple={true} name="categories" value={item.categories} onChange={handleCategory} className="select-category-box">
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
                <div className="item-attributes">
                    <label className="item-item-label">Longitude</label>
                    <input className="add-item-input-box" placeholder="Longitude" type="number" name="longitude" value={item.longitude} onChange={handleChange}></input>
                </div>
                <div className="item-attributes">
                    <label className="item-item-label">Latitude</label>
                    <input className="add-item-input-box" placeholder="Latitude" type="number" name="latitude" value={item.latitude} onChange={handleChange}></input>
                </div>
                <a href='https://support.google.com/maps/answer/18539?hl=en&co=GENIE.Platform%3DDesktop' target='_blank' rel="noopener noreferrer">Click here to see how to find your longitude and latitude!</a>
            </div>
            <div className="add-item-div-btn">
                <button className="add-item-submit-btn" onClick={handleSubmit} disabled={disableButton}>Place item</button>
            </div>
            <div className="add-item-submit-message">
                {
                    isCorrectSubmission===1 &&
                    <p className="add-item-correct-registration">
                        Great, you successfully edited your item.
                    </p>
                }
                {
                    isCorrectSubmission===2 &&
                    <p className="add-item-wrong-registration">
                        {addItemError}
                    </p>
                }
            </div>
        </div>
    );
};

export default EditItem;