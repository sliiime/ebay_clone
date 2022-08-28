import React from 'react';
import {Link} from "react-router-dom";

const Missing = () => {
    return (
        <div>
            <h1>ERROR: There is no such page!</h1>
            <Link to="/">Redirect to Home!</Link>
        </div>
    );
};

export default Missing;