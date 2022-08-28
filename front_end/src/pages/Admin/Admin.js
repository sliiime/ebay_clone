import React, {useEffect, useState} from 'react';
import axios from "axios";
import market from "../../images/market.png";
import '../../css/admin.css'
import useAuth from "../../context/useAuth";
import {useNavigate} from "react-router-dom";

const Admin = () => {

    const { setAuth } = useAuth()

    const [users,setUsers] = useState([])

    const handleLogoutClick = () => {
        setAuth({})
        localStorage.clear()
    }

    const handleAuthClick = (user) => {
        
    }

    useEffect( () => {
        axios
            .get("http://localhost:8080/ebay_clone/api/user/")
            .then((response) => {
                setUsers(response.data)
                (response.data)
            })
            .catch((error) => {
                console.log(error)
            })
    },[])

    return (
        <div>
            <nav className="navbar">
                <img className="marketPlaceIcon" src={market} alt=""/>
                <h1 className="admin-text">~ADMIN~</h1>
                <button className="admin-applyChanges-button" onClick={handleLogoutClick}>
                    Logout
                </button>
            </nav>
            {
                users.length ?
                    users.map(user =>
                        <div className="admin-userInfo" key={user.id}>
                            <a className="admin-userInfo-text">
                                {user.username}, {user.afm}, {user.country}
                            </a>
                            {
                                (user.id === 1) ?
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