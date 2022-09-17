import React, {useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import {useParams} from "react-router-dom";
import refreshIcon from "./refresh-icon.png";
import './chat.css'

const Chat = () => {

    const { name } = useParams()

    const [userInput,setUserInput] = useState("")

    const handleRefreshButton = () => {
        window.location.reload(false)
    }

    const handleUserInput = (event) => {
        setUserInput(event.target.value)
    }

    const handleSendMessageButton = (event) => {
        event.preventDefault()
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
                <div className="user-input-and-btn">
                    <input className='user-input' type='text' value={userInput} onChange={handleUserInput}/>
                    <button className='send-msg-btn' onClick={handleSendMessageButton}>➤</button>
                </div>
            </div>
        </div>
    );
};

export default Chat;