import React, {useEffect, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import {useParams} from "react-router-dom";
import refreshIcon from "./refresh-icon.png";
import './chat.css'
import axios from "axios";

const Chat = () => {

    const { talkerId , talkerName } = useParams()

    const [numOfPages, setNumOfPages] = useState(1)
    const [currentPage,setCurrentPage] = useState(0)
    const [userInput,setUserInput] = useState("")
    const [chat,setChat] = useState([])
    const [otherUser,setOtherUser] = useState([])

    useEffect(() => {
        const url = "http://localhost:8080/ebay_clone/api/message/users/" + talkerId + "?p=" + currentPage
        console.log(url)
        axios.get(url, {
            headers: {
                'Authorization': JSON.parse(localStorage.getItem('accessToken'))
            }
        })
        .then((response) => {
            console.log(response?.data)
            if(response?.data?.totalPages===0) {
                setNumOfPages(1)
            } else {
                setNumOfPages(response?.data?.totalPages)
            }
            setChat([])
            setChat(response?.data?.content.reverse())
            console.log(chat)
        })
        .catch((error) => {
            console.log(error)
        })
    }, [currentPage])

    const handleRefreshButton = () => {
        window.location.reload(false)
    }

    const handleUserInput = (event) => {
        setUserInput(event.target.value)
    }

    const handleSendMessageButton = (event) => {
        event.preventDefault()
        if (userInput.length===0) {return}
        axios
            .post("http://localhost:8080/ebay_clone/api/message",{
                receiverId: talkerId,
                body: userInput
            }, {
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                }
            })
            .then((response) => {
                console.log(response?.data)
                window.location.reload(false)
            })
            .catch((error) => {
                console.log(error)
            })
    }

    const handleNextClick = () => {
        setCurrentPage(currentPage+1)
    }

    const handlePrevClick = () => {
        setCurrentPage(currentPage-1)
    }

    return (
        <div>
            <NavBar/>
            <div className='chat-preview-body'>
                <button className='chat-preview-refresh-btn' onClick={handleRefreshButton}>
                    <img src={refreshIcon}/>
                </button>
                <h4 className='talking-to'>Talking with: {talkerName}</h4>
                <div className='chat-preview-panel'>
                    {
                        chat.length ?
                            chat.reverse().map(msg =>
                                <div className='chat-div' key={msg.id}>
                                    <label className='chat-label'>{msg.receiverId===talkerId ? talkerName+":" : "you:"}</label>
                                    <p className='chat-text'>{msg.body}</p>
                                </div>
                            ) : <p>No messages yet!</p>
                    }
                </div>
                {
                    <div className="change-page-container">
                        <button className="change-page-btn" disabled={currentPage === 0} onClick={handlePrevClick}>←</button>
                        <p className="current-page">{currentPage + 1}</p>
                        <button className="change-page-btn" disabled={currentPage === numOfPages - 1}
                                onClick={handleNextClick}>→
                        </button>
                    </div>
                }
                <div className="user-input-and-btn">
                    <input className='user-input' type='text' value={userInput} onChange={handleUserInput}/>
                    <button className='send-msg-btn' onClick={handleSendMessageButton}>➤</button>
                </div>
            </div>
        </div>
    );
};

export default Chat;