import { useState, useEffect } from "react";
import './Header.css';
import WorkerCard from "./WorkerCard";


const WorkerFeed = () => {

    const [workers, setWorkers] = useState([]);

    useEffect(() => {
        const getWorkers = async () => {
            const workersByRate = await fetchWorkers();
            setWorkers(workersByRate);
        }
        getWorkers();
    },[])

    const fetchWorkers = async () => {
        const response = await fetch("http://localhost:8080/api/getWorkersByRating");
        const data = await response.json();
        return data;
    }

    return (
        <div id="worker-card-container">
                {workers.map((worker) => (
                <WorkerCard worker={worker} />))} 
        </div>
    )
}



export default WorkerFeed
