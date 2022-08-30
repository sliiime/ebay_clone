import React, {useEffect, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import axios from "axios";

const MyItems = () => {

    const [paging, setPaging] = useState(0)

    useEffect( () => {
        axios
            .get('http://localhost:8080/ebay_clone/api/item/user/?p=' + String(paging),{
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                }
            })
            .then((response) => {
                //registrationStatus
                console.log(response?.data)
            })
            .catch((error) => {
                console.log(error)
            })
    }, []);

    return (
        <div>
            <NavBar/>
        </div>
    );
};

export default MyItems;