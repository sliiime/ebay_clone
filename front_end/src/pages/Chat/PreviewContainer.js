import React from 'react';
import {NavLink} from "react-router-dom";

const PreviewContainer = ({user}) => {
    return (
        <div>
            <NavLink style={{ textDecoration: 'none'}} to={'/chat/'+String(user)}>
                <button className='chat-preview-user-btn' >
                    <p>{user}</p>
                </button>
            </NavLink>
        </div>
    );
};

export default PreviewContainer;