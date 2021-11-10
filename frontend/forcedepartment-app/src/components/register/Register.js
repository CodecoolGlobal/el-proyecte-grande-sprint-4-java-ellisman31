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
    const [getDataFromDatabase, setGetDataFromDatabase] = useState([]);
    const current = new Date();
    const date = `${current.getFullYear()}-${current.getMonth()+1}-${current.getDate()} ${current.getHours()}:${current.getMinutes()}:${current.getSeconds()}:${current.getMilliseconds()}`;

    useEffect(() => {
        document.title = "Special Department | Registration";
        //getUsersFromDatabase();
    }, []);

    const navigate = useNavigate();

    const saveDataIntoTheDatabase = async () => {
        const response = await fetch('', {
            method: "POST",
            body: JSON.stringify(previousData)//sendData
        })
    }

    const getUsersFromDatabase = () => {
        fetch(''
        )
            .then((res) => res.json())
            .then((res) => {
                setGetDataFromDatabase(res);
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