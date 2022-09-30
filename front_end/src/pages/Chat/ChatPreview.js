import React, {useEffect, useState} from 'react';
import NavBar from "../MainMenu/Navbar";
import './chat.css'
import refreshIcon from './refresh-icon.png'
import PreviewContainer from "./PreviewContainer";
import axios from "axios";

const ChatPreview = () => {

    const [numOfPages, setNumOfPages] = useState()
    const [currentPage,setCurrentPage] = useState(0)
    const [usersToChat,setUsersToChat] = useState([])

    useEffect(() => {
        axios
            .get("http://localhost:8080/ebay_clone/api/message/users/?page=" + currentPage, {
                headers: {
                    'Authorization': JSON.parse(localStorage.getItem('accessToken'))
                }
            })
            .then((response) => {
                setUsersToChat([])
                setUsersToChat(response?.data?.content)
                console.log(usersToChat)
                const num = response?.data?.totalPages
                setNumOfPages(num);
            })
            .catch((error) => {
                console.log(error)
            })
    }, [currentPage])

    const handleNextClick = () => {
        setCurrentPage(currentPage+1)
    }

    const handlePrevClick = () => {
        setCurrentPage(currentPage-1)
    }

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
                    {
                        usersToChat.length ?
                            usersToChat.map(user =>
                            <div key={user.id}>
                                <PreviewContainer user={user}/>
                            </div>
                            ) : <p>None to chat with!</p>
                    }
                </div>
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
        </div>
    );
};

export default ChatPreview;