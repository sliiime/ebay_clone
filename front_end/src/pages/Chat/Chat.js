import React from 'react';
import NavBar from "../MainMenu/Navbar";
import {useParams} from "react-router-dom";
import refreshIcon from "./refresh-icon.png";
import './chat.css'

const Chat = () => {

    const { name } = useParams()

    const handleRefreshButton = () => {
        window.location.reload(false)
    }

    return (
        <div>
            <NavBar/>
            <div className='chat-preview-body'>
                <button className='chat-preview-refresh-btn' onClick={handleRefreshButton}>
                    <img src={refreshIcon}/>
                </button>
                <h4 className='talking-to'>Talking with: {name}</h4>
                <div className='chat-preview-panel'>
                    <div className='chat-div'>
                        <label>you:</label>
                        <p className='chat-text'>hello</p>
                    </div>
                    <div className='chat-div'>
                        <label>you:</label>
                        <p className='chat-text'>hello</p>
                    </div>
                    <div className='chat-div'>
                        <label>you:</label>
                        <p className='chat-text'>hello</p>
                    </div>
                    <div className='chat-div'>
                        <label>you:</label>
                        <p className='chat-text'>hello</p>
                    </div>
                    <div className='chat-div'>
                        <label>you:</label>
                        <p className='chat-text'>hello</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Chat;