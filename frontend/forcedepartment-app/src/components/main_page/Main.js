import Header from '../header/Header';
import Footer from '../footer/Footer';
import Sidebar from '../sidebar/Sidebar';
import WorkerFeed from '../worker_feed/WorkerFeed';
import './Main.css'
import axios from 'axios';
import {useState, useEffect, Component} from "react";

const Main = () => {
    const [workers, setWorkers] = useState([]);
    const [user, setUser] = useState([]);

    useEffect(async () => {
        document.title = "Special Department";
        const getWorkers = async () => {
            const workersByRate = await fetchWorkers();
            setWorkers(workersByRate);
        }
        getWorkers();

    }, [])

    useEffect(async() => {

        const config = {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token')
            }
        }

        axios.get("http://localhost:8080/api/getUser", config).then(
            res => {
                setUser(res.data);
            },
            err => {
                console.log(err);
            }
        )
    },[])

    const fetchWorkers = async () => {
        const response = await axios.get("http://localhost:8080/api/getWorkersByRating", {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        });
        const data = await response;
        return data;
    }

    const fetchMenuPoint = async (mainMenuName, menuName) => {
        const response = await axios.get(`http://localhost:8080/api/getAllWorkerBy${mainMenuName}/${menuName}`,{
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        });
        const data = await response;
        setWorkers(data);
    }

    const fetchWorkersExtraSearch = async(name, workObject, profession, rate) => {
        const response = await axios.get(`http://localhost:8080/api/getWorkerByExtraSearch/name?name=${name}&workObject=${workObject}&profession=${profession}&rating=${rate}`,{
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        });
        const data = await response;
        setWorkers(data);
    }

    return (
        <div className="grid-container">
            <div className="item1">
                <Header  loggedUser={user}/>
            </div>
            <div className="item2">
                <Sidebar sideBarHandler={fetchMenuPoint} extraSearch={fetchWorkersExtraSearch}/>
            </div>
            <div className="item3">
                 <WorkerFeed workers={workers} />
            </div>
            <div className="item4 main-page-footer">
                <Footer />
            </div>
        </div>
    )
}


export default Main