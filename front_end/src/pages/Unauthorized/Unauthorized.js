import React from 'react';
import {Link} from "react-router-dom";

const Unauthorized = () => {
    return (
        <div>
            <h1>ERROR: You are not authorized!</h1>
            <Link to="/">Redirect to Home!</Link>
        </div>
    );
};

export default Unauthorized;