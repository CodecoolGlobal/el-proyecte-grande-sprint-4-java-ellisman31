import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import profilePic from '../img/profile-icon-empty.png';
import './Profile.css'
import Header from '../header/Header';
import Footer from '../footer/Footer';

const Profile = () => {

    const { userId } = useParams(0);
    const [currentWorker, setCurrentWorker] = useState([]);
    const [userData, setUserData] = useState([]);
    const currentProfessions = currentWorker.workerExperience;

    useEffect(()=> {
        document.title = "Special Department |  "+ userId;
    },[userId]);

/*     useEffect(() => {
        const getWorker = async () => {
            const worker = await fetchSingleWorker();
            setCurrentWorker(worker);
        }
        getWorker();
    })

    const fetchSingleWorker = async() => {
        const response = await fetch(`http://localhost:8080/api/getUserById/${userId}`)
        const data = await response.json();
        return data;
    } */

    useEffect(() => {
        const getWorker = async () => {
            const response = await fetch(`http://localhost:8080/api/getUserById/${userId}`);
            const data = await response.json();
            setUserData(data.user);
            setCurrentWorker(data);
        }
        getWorker();
        
        }, [userId]);

    const fetchWorkerById = async () => {

    }

    //const workerAge = Math.floor(Math.floor(Math.abs(new Date - Date.parse(currentWorker.user.birth_date))/ (24*60*60*1000)) / 365);

    

    return (
        <>
        <Header />
        <div className="user-profile-container">
            <div className="user-container">
                <div className="user-personal">
                    <img src={profilePic} alt={currentWorker.imageName}></img>
                    <h1>{`${userData.first_name} ${userData.last_name}`}</h1>
                    <h2>Phone number: </h2>
                    <p>{currentWorker.phone_number}</p>
                </div>
            </div>
            <div className="user-description-container">
                <div className="user-description">
                    <h2>Description: </h2>
                    <p className="user-description-content">{currentWorker.description}</p>
                    <h2 id="h2-profession">Profession(s): </h2>
                    <span className="professions">
                        {/* {currentProfessions ? currentProfessions.map((profession, i, arr) => 
                        <span key={profession}>
                            {arr.length - 1 === i ? profession : `${profession}, `}
                        </span>) : ''} */}
                    </span>
                </div>
            </div>
        </div>
        <div className="item4 profile-page-footer">
                <Footer />
        </div>
        </>
    )
}

export default Profile
