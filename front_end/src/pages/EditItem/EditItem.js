import React, {useEffect, useRef, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import {useNavigate, useParams} from "react-router-dom";
import validation from "../AddItem/validation";
import errorsExist from "../AddItem/errorsExist";
import axios from "axios";
import getFormData from "./UpdatedFields";
import L from "leaflet";
import marker from "../Bid/marker.svg";
import {MapContainer, Marker, TileLayer} from "react-leaflet";

const EditItem = () => {

    const [item, setItem] = useState({
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

    const [updatedItem, setUpdatedItem] = useState({
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

    const myIcon = new L.Icon({
        iconUrl: marker,
        iconRetinaUrl: marker,
        popupAnchor: [-0, -0],
        iconSize: [20, 20],
    });

    const {id} = useParams()

    let navigate = useNavigate()

    const [errors, setErrors] = useState({});
    const addItemError = "Something went wrong. Try again."
    const [disableButton, setDisableButton] = useState(false);
    const [submitButtonPressed, setSubmitButtonPressed] = useState(false)
    const [isCorrectSubmission, setIsCorrectSubmission] = useState(0)

    const [images, setImages] = useState([]);
    const [previewImages, setPreviewImages] = useState([])

    const [position, setPosition] = useState([37.983810, 23.727539])
    const [finalPos, setFinalPos] = useState([])
    const markerRef = useRef();

    const [markerPos, setMarkerPos] = useState({
        lat: 37.983810,
        lng: 23.727539,
    })

    const handleChange = (event) => {
        setUpdatedItem({
            ...updatedItem,
            [event.target.name]: event.target.value,
        });
    };

    const handleCategory = (event) => {
        if (event.target.value === "") {
            setUpdatedItem({
                ...updatedItem,
                [event.target.name]: []
            })
            return
        }
        let temp = updatedItem.categories
        if (updatedItem.categories.includes(event.target.value)) {
            temp = temp.filter(e => e !== event.target.value)
        } else {
            temp.push(event.target.value)
        }
        console.log(temp)
        setUpdatedItem({
            ...updatedItem,
            [event.target.name]: temp
        })
    }

    const handleSubmit = (event) => {
        event.preventDefault()
        setErrors(validation(updatedItem))
        setSubmitButtonPressed(true)
    }

    useEffect(() => {
        axios
            .get("http://localhost:8080/ebay_clone/api/item/" + String(id), {
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
                setUpdatedItem({
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
                //[37.983810, 23.727539]
                setMarkerPos({
                    lat: response?.data?.latitude ? response?.data?.latitude : 37.983810,
                    lng: response?.data?.longitude ? response?.data?.longitude : 23.727539
                })
            })
            .catch((error) => {
                console.log(error)
            })
    }, [])

    useEffect(() => {
        if (!errorsExist(submitButtonPressed, errors)) {
            const formData = getFormData(item, updatedItem,markerPos)
            for (var pair of formData.entries()) {
                console.log(pair[0]+ ', ' + pair[1]);
            }
            //return
            try {
                const response = axios({
                    method: "put",
                    url: "http://localhost:8080/ebay_clone/api/item"+String(id),
                    data: formData,
                    headers: {
                        'Authorization': JSON.parse(localStorage.getItem('accessToken')),
                        'Accept': '*/*',
                        'Content-Type': "multipart/form-data"
                    },
                })
            } catch (error) {
                console.log(error)
            }
            setDisableButton(true)
            setTimeout(() => {
                navigate('..')
            }, 2000)
            /*axios
                .put("http://localhost:8080/ebay_clone/api/item/" + String(id),
                    data
                    , {
                        headers: {
                            'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                        }
                    })
                .then((response) => {
                    setIsCorrectSubmission(1);
                    setDisableButton(true);
                    const timer = setTimeout(() => navigate('../'), 2000);
                    return () => clearTimeout(timer);
                })
                .catch((error) => {
                    console.log(error)
                    setIsCorrectSubmission(2);
                });*/
        }
    }, [submitButtonPressed, errors]);

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

    const onMapClick = (event) => {
        const marker = markerRef.current
        console.log("marker" + marker._latlng)
        setMarkerPos({
            lat: marker._latlng.lat,
            lng: marker._latlng.lng
        })
    }

    return (
        <div>
            <NavBar/>
            <p className="add-item-welcome-text">Hello! Here you can add a new item! Please fill the blank spaces.</p>
            <div className='add-item-full-body'>
                <div className="add-item-panel">
                    <div className="item-attributes">
                        <label className="item-item-label">Name</label>
                        {errors.name && <p className="add-item-input-error">{errors.name}</p>}
                        <input className="add-item-input-box" maxLength={45} placeholder="Name" type="text" name="name"
                               value={updatedItem.name} onChange={handleChange}></input>
                    </div>
                    <div className="item-attributes">
                        <label className="item-item-label">Description</label>
                        {errors.description && <p className="add-item-input-error">{errors.description}</p>}
                        <input className="add-item-input-box" maxLength={200} placeholder="Description" type="text"
                               name="description" value={updatedItem.description} onChange={handleChange}></input>
                    </div>
                    <div className="item-attributes">
                        <label className="item-item-label">Buyout Price</label>
                        {errors.buyPrice && <p className="add-item-input-error">{errors.buyPrice}</p>}
                        <input className="add-item-input-box" placeholder="Buyout Price" type="number" name="buyPrice"
                               value={updatedItem.buyPrice} onChange={handleChange}></input>
                    </div>
                    <div className="item-attributes">
                        <label className="item-item-label">Minimum Bid</label>
                        {errors.minBid && <p className="add-item-input-error">{errors.minBid}</p>}
                        <input className="add-item-input-box" placeholder="Minimum Bid" type="number" name="minBid"
                               value={updatedItem.minBid} onChange={handleChange}></input>
                    </div>
                    <div className="item-attributes">
                        <label className="item-item-label">Start Date</label>
                        {errors.startDate && <p className="add-item-input-error">{errors.startDate}</p>}
                        <input className="add-item-input-box" type="date" name="startDate" value={updatedItem.startDate}
                               onChange={handleChange}></input>
                    </div>
                    <div className="item-attributes">
                        <label className="item-item-label">End Date</label>
                        {errors.endDate && <p className="add-item-input-error">{errors.endDate}</p>}
                        <input className="add-item-input-box" type="date" name="endDate" value={updatedItem.endDate}
                               onChange={handleChange}></input>
                    </div>
                    <div className="item-attributes">
                        <label className="item-item-label">Category</label>
                        {errors.categories && <p className="add-item-input-error">{errors.categories}</p>}
                        <select multiple={true} name="categories" value={updatedItem.categories} onChange={handleCategory}
                                className="select-category-box">
                            <option className="add-item-option" value="">~Empty selection~</option>
                            <option className="add-item-option" value="Technology">Technology</option>
                            <option className="add-item-option" value="Home & Kitchen">Home & Kitchen</option>
                            <option className="add-item-option" value="Beauty & Personal Care">Beauty & Personal Care
                            </option>
                            <option className="add-item-option" value="Toys & Games">Toys & Games</option>
                            <option className="add-item-option" value="Clothing, Shoes & Jewelry">Clothing, Shoes &
                                Jewelry
                            </option>
                            <option className="add-item-option" value="Sports & Outdoors">Sports & Outdoors</option>
                            <option className="add-item-option" value="Books">Books</option>
                            <option className="add-item-option" value="Other">Other</option>
                        </select>
                    </div>
                    <div className="item-attributes">
                        <label className="item-item-label">Longitude</label>
                        <p className="add-item-longlat-box" placeholder="Longitude">{markerPos.lng}</p>
                    </div>
                    <div className="item-attributes">
                        <label className="item-item-label">Latitude</label>
                        <p className="add-item-longlat-box" placeholder="Latitude">{markerPos.lat}</p>
                    </div>
                    <h5>Move the marker in the map to match the location you want!</h5>
                    <div className="item-attributes">
                        <label className="item-item-label">Add photos</label>
                        <input formEncType="multipart/form-data" onChange={(e) => handleImages(e)} type="file"
                               name="file"/>
                        {
                            previewImages.map((previewImage) => (
                                <img className="add-item-image" src={previewImage} key={previewImage}/>
                            ))
                        }
                        {
                            images.length > 0 ? <button onClick={removeImages}>Remove</button> : null
                        }
                    </div>
                    <div className="add-item-div-btn">
                        <button className="add-item-submit-btn" onClick={handleSubmit} disabled={disableButton}>Place
                            item
                        </button>
                    </div>
                </div>

                <div className='add-item-map'>
                    <link
                        rel="stylesheet"
                        href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
                        integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
                        crossOrigin=""
                    />
                    <MapContainer center={[markerPos.lat,markerPos.lng]} zoom={13} scrollWheelZoom={true}>
                        <TileLayer
                            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                        />
                        <Marker
                            position={[markerPos.lat, markerPos.lng]}
                            draggable={true}
                            eventHandlers={{dragend: onMapClick}}
                            ref={markerRef}
                            icon={myIcon}/>
                    </MapContainer>
                </div>
            </div>
        </div>
    );
};

export default EditItem;