import React from 'react';
import {NavLink} from "react-router-dom";

const PreviewContainer = ({user}) => {
    return (
        <div>
            <NavLink style={{ textDecoration: 'none'}} to={'/chat/'+String(user.id)+"/"+String(user.username)}>
                <button className='chat-preview-user-btn' >
                    <p>{user.username}</p>
                </button>
            </NavLink>
        </div>
    );
};

export default PreviewContainer;