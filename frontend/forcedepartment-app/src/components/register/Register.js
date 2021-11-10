import '../css/Register.css';
import {useEffect, useState} from "react";
import  {useNavigate } from 'react-router';
import RegisterForEveryUser from "./RegisterForEveryUser";
import RegisterForWorker from "./RegisterForWorker";

//TODO: sent data to the database, get data from database
function Register() {

    const userTypeData = ["USER", "WORKER"];
    const [userType, setUserType] = useState('');
    const [previousData, setPreviousData]= useState([]);
    const [workerData, setWorkerData] = useState([]);
    const current = new Date();
    const date = `${current.getFullYear()}-${current.getMonth()+1}-${current.getDate()} ${current.getHours()}:${current.getMinutes()}:${current.getSeconds()}:${current.getMilliseconds()}`;

    useEffect(() => {
        document.title = "Registration";
    }, []);

    const navigate = useNavigate();

    const saveDataIntoTheDatabase = async () => {
        const response = await fetch('', {
            method: "POST",
            body: JSON.stringify(previousData)//sendData
        })
    }
    //in the useEffect
    const getUsersFromDatabase = () => {
        fetch(''
        )
            .then((res) => res.json())
            .then((res) => {
                //setGetData(res);
            })
    }

    const userTypeHandler = (user) => {
        setUserType(user)
    }

    const previousDataHandler = (data) => {
        setPreviousData(data);
    }

    const workerDataHandler = (data) => {
        setWorkerData(data);
    }

    console.log(previousData);
    console.log(date);

    return (
        <div className="container">
            <div className="register-form">
                {userType === "WORKER" ? <RegisterForWorker previousData={previousData}
                                                            workerDataHandler={workerDataHandler} navigate={navigate}/> :
                    <RegisterForEveryUser userTypeData={userTypeData} previousDataHandler={previousDataHandler}
                                          userTypeHandler={userTypeHandler} navigate={navigate}/>}
            </div>
        </div>
    )
}

export default Register