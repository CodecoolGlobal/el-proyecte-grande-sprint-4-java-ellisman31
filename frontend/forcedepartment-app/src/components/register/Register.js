import './Register.css';
import {useEffect, useState} from "react";
import {useNavigate} from 'react-router';
import RegisterForEveryUser from "./RegisterForEveryUser";
import RegisterForWorker from "./RegisterForWorker";

//TODO: sent data to the database, get data from database
function Register() {

    const userTypeData = ["USER", "WORKER"];
    const [userType, setUserType] = useState('');
    const emailExist = false;

    useEffect(() => {
        document.title = "Special Department | Registration";
    }, []);

    const navigate = useNavigate();

    const userTypeHandler = (user) => {
        setUserType(user)
    }

    return (
        <div className="container">
            <div className="register-form">
                {userType === "WORKER" ?
                    <RegisterForWorker
                        navigate={navigate}/> :
                    <RegisterForEveryUser userTypeData={userTypeData}
                                          userTypeHandler={userTypeHandler} navigate={navigate}
                                          isEmailExist={emailExist}/>}
            </div>
        </div>
    )
}

export default Register