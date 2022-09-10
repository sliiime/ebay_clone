import React from 'react';
import NavBar from "../MainMenu/Navbar";
import './chat.css'
import refreshIcon from './refresh-icon.png'
import PreviewContainer from "./PreviewContainer";

const ChatPreview = () => {

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
                <div className='chat-preview-panel'>
                    <PreviewContainer user='kostis'/>
                    <PreviewContainer user='panos'/>
                    <PreviewContainer user='bill'/>
                </div>
            </div>
        </div>
    );
};

export default ChatPreview;