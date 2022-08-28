import React, {useEffect, useState} from 'react';
import axios from "axios";
import market from "../../images/market.png";
import '../../css/admin.css'

const Admin = () => {

    const [users,setUsers] = useState([])
    const handleAuthClick = (user) => {
        console.log(user)
    }

    useEffect( () => {
        axios
            .get("http://localhost:8080/ebay_clone/api/user/")
            .then((response) => {
                setUsers(response.data)
                console.log(response.data)
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
                <button className="admin-applyChanges-button">
                    Apply changes
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