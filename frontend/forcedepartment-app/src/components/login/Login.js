import './Login.css';
import React from 'react';
import LoginDesign from "./LoginDesign";
import {useEffect} from "react";
import {useNavigate} from 'react-router';

function Login() {

    useEffect(() => {
        document.title = "Special Department | Login";
    }, []);

    const navigation = useNavigate();

    return (
        <div className="container">
            <LoginDesign navigation={navigation}/>
        </div>
    )

}

export default Login;