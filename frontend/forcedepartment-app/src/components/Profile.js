import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import profilePic from '.././profile-icon-empty.png';
import './Profile.css'
import Header from './Header';
import Footer from './Footer';

const Profile = () => {

    useEffect(()=> {
        document.title = "Special Department |  "+ userId;
    },[]);

    const { userId } = useParams();
    const [currentWorker, setCurrentWorker] = useState([]);
    const currentProfessions = currentWorker.profession;

    useEffect(() => {
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
    }

    return (
        <>
        <Header />
        <div className="user-profile-container">
            <div className="user-container">
                <div className="user-personal">
                    <img src={profilePic} alt={currentWorker.imageName}></img>
                    <h1>{`${currentWorker.firstName} ${currentWorker.lastName} ( ${currentWorker.age} )`} </h1>
                    <h2>Phone number: </h2>
                    <p>{currentWorker.phoneNumber}</p>
                </div>
            </div>
            <div className="user-description-container">
                <div className="user-description">
                    <h2>Description: </h2>
                    <p className="user-description-content">{currentWorker.description}</p>
                    <h2 id="h2-profession">Profession(s): </h2>
                    <span className="professions">
                        {currentProfessions ? currentProfessions.map((profession, i, arr) => 
                        <span key={profession}>
                            {arr.length - 1 === i ? profession : `${profession}, `}
                        </span>) : ''}
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
