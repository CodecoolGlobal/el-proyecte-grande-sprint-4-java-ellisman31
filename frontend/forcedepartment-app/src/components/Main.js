import Header from './Header';
import Footer from './Footer';
import Sidebar from './Sidebar';
import WorkerFeed from './WorkerFeed';
import { useState, useEffect } from "react";

const Main = () => {
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

    const fetchMenuPoint = async(mainMenuName, menuName) => {
        const response = await fetch(`http://localhost:8080/api/getAllWorkerBy${mainMenuName}/${menuName}`);
        const data = await response.json();
        setWorkers(data);    
    }


    return (
        <>
           <Header />
            <Sidebar sideBarHandler={fetchMenuPoint} />
            <WorkerFeed workers={workers} />
           <Footer />
        </>
    )
}

export default Main
