import React from 'react';
import {Link} from "react-router-dom";
import '../Missing/problem.css'

const Unauthorized = () => {
    return (
        <div className="problem">
            <h1>ERROR: You are not authorized!</h1>
            <Link to="/">Redirect to Home!</Link>
        </div>
    );
};

export default Unauthorized;