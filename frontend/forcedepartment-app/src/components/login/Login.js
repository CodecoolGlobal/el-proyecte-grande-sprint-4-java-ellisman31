import '../css/Login.css';
import LoginDesign from "./LoginDesign";
import {useEffect, useState} from "react";
import {useNavigate} from 'react-router';


function Login() {

    useEffect( () => {
        document.title = "Special Department | Login";
        //getUsersFromDatabase();
    },[]);

    const navigation = useNavigate();
    const [getData, setGetData] = useState([]);

    const getUsersFromDatabase = () => {
        fetch(''
        )
            .then((res) => res.json())
            .then((res) => {
                setGetData(res)
            })
    }

    console.log(getData);

    return (
        <div className="container">
            <LoginDesign navigation={navigation}/>
        </div>
    )

}

export default Login;