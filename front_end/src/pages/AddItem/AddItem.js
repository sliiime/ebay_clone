import React, {useEffect, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import './addItem.css'
import validation from "./validation";
import errorsExist from "./errorsExist";
import axios from "axios";
import {useNavigate} from "react-router-dom";
const AddItem = () => {

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

    let navigate = useNavigate()

    const [errors,setErrors] = useState({});
    const addItemError = "Something went wrong. Try again."
    const [disableButton, setDisableButton] = useState(false);
    const [submitButtonPressed,setSubmitButtonPressed] = useState(false)
    const [isCorrectSubmission,setIsCorrectSubmission] = useState(0)
    const [images, setImages] = useState([]);
    const [previewImages, setPreviewImages] = useState([])

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
        if(!errorsExist(submitButtonPressed,errors)) {
            let formData = new FormData()
            const endDateValue = item.endDate.replaceAll("-","/")
            const startDateValue = item.startDate.replaceAll("-","/")
            formData.append("name",item.name)
            formData.append("buyPrice",item.buyPrice)
            formData.append("description", item.description)
            formData.append("category", item.categories)
            formData.append("minBid",item.minBid)
            formData.append("startDate",startDateValue)
            formData.append("endDate",endDateValue)
            formData.append("longitude",item.longitude)
            formData.append("latitude",item.latitude)
            images.forEach((image)=>formData.append("images[]",image))
            try {
                const response = axios ({
                    method: "post",
                    url: "http://localhost:8080/ebay_clone/api/item",
                    data: formData,
                    headers: {
                        'Authorization': JSON.parse(localStorage.getItem('accessToken')),
                        'Accept' : '*/*',
                        'Content-Type': "multipart/form-data"
                    },
                })
            } catch (error){
                console.log(error)
            }
            setDisableButton(true)
            setTimeout(()=>{navigate('..')},2000)
        }
    }, [submitButtonPressed,errors]);

    const handleImages = (event) => {
        for (const file of event.target.files) {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                setPreviewImages((imgs) => [...imgs, reader.result]);
            };
            reader.onerror = () => {
                console.log(reader.error);
            };
        }
        let image_as_files = event.target.files[0];
        let temp = images
        temp.push(image_as_files)
        setImages(temp)
    };

    const removeImages = () => {
        setImages([])
        setPreviewImages([])
    }

    return (
        <div>
            <NavBar/>
            <p className="add-item-welcome-text">Hello! Here you can add a new item! Please fill the blank spaces.</p>
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
                <div className="item-attributes">
                    <label className="item-item-label">Add photos</label>
                    <input formEncType="multipart/form-data" onChange={(e)=>handleImages(e)}/*onChange={handleImages}*/ type="file" name="file" /*multiple*/ />
                    {
                        previewImages.map((previewImage) => (
                            <img className="add-item-image" src={previewImage} key={previewImage} />
                        ))
                    }
                    {
                        images.length>0 ? <button onClick={removeImages}>Remove</button> : null
                    }
                </div>
            </div>
            <div className="add-item-div-btn">
                <button className="add-item-submit-btn" onClick={handleSubmit} disabled={disableButton}>Place item</button>
            </div>
            <div className="add-item-submit-message">
                {
                    isCorrectSubmission===1 &&
                    <p className="add-item-correct-registration">
                        Great, your item is placed in the marketplace.
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

export default AddItem;