import { useState, useEffect } from "react";
import './Header.css';
import WorkerCard from "./WorkerCard";


const WorkerFeed = (props) => {

    

    return (
        <div id="worker-card-container">
                {props.workers.map((worker) => (
                <WorkerCard key={worker.userId} worker={worker} />))} 
        </div>
    )
}


export default WorkerFeed
