import React, {useEffect, useState} from 'react';
import axios from "axios";
import market from "../../images/market.png";
import './admin.css'
import useAuth from "../../context/useAuth";

const Admin = () => {

    const { setAuth } = useAuth()

    const [users,setUsers] = useState([])

    let detectChanges = true

    const handleLogoutClick = () => {
        setAuth({})
        localStorage.clear()
    }

    const handleAuthClick = (user) => {
        const endpoint = 'http://localhost:8080/ebay_clone/api/user/approve/' + String(user.id)
        axios
            .put(endpoint,{}, {
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                }
            })
            .then((response) => {
                console.log(response)
                window.location.reload(false);
            })
            .catch((error) => {
                console.log(error)
            })
    }

    useEffect( () => {
        axios
            .get("http://localhost:8080/ebay_clone/api/user", {
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken')),
                }
            })
            .then((response) => {
                //registrationStatus
                setUsers(response?.data);
                console.log(users)
                console.log(response?.data)
            })
            .catch((error) => {
                console.log(error)
            })
    },[detectChanges])

    return (
        <div>
            <nav className="admin-navbar">
                <img className="admin-marketPlaceIcon" src={market} alt=""/>
                <h1 className="admin-text">~ADMIN~</h1>
                <button className="admin-logout-button" onClick={handleLogoutClick}>
                    Logout
                </button>
            </nav>
            {
                users.length ?
                    users.map(user =>
                        <div className="admin-userInfo" key={user.id}>
                            <p className="admin-userInfo-text">
                                {user.username}, {user.afm}, {user.country}
                            </p>
                            {
                                (user.registrationStatus !== "ACCEPTED") ?
                                <button className="admin-userInfo-button" onClick={() => handleAuthClick(user)}>
                                    AUTHORIZE
                                </button> : null
                            }
                        </div>
                    ) : null
            }
        </div>
    );
};

export default Admin;