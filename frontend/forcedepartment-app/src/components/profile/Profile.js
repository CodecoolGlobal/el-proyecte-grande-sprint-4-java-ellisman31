import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import profilePic from '../img/profile-icon-empty.png';
import './Profile.css'
import Header from '../header/Header';
import Footer from '../footer/Footer';
import axios from 'axios';

const Profile = () => {

    const { userId } = useParams(0);
    const [currentWorker, setCurrentWorker] = useState([]);
    const [currentUser, setCurrentUser] = useState([]);
    const [currentProfessions, setCurrentProfessions] = useState([]);
    const [user, setUser] = useState([]);

    useEffect(()=> {
        document.title = "Special Department |  Profile";
    },[userId]);

    useEffect(() => {
            const getWorker = async () => {
                const response = await fetch(`http://localhost:8080/api/getWorkerById/${userId}`);
                const data = await response.json();
                console.log(data);
                setCurrentProfessions(data.user.workerExperience);
                setCurrentUser(data.user);
                setCurrentWorker(data);
        }
            getWorker();
        }, [userId]);

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

    const workerAge = Math.floor(Math.floor(Math.abs(new Date - Date.parse(currentUser.birth_date))/ (24*60*60*1000)) / 365);

    return (
        <>
        <Header loggedUser={user}/>
        <div className="user-profile-container">
            <div className="user-container">
                <div className="user-personal">
                    <img src={profilePic} alt={currentWorker.imageName}></img>
                    <h1>{`${currentUser.first_name} ${currentUser.last_name} ( ${workerAge} )`}</h1>
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
                        {currentProfessions ? currentProfessions.map((profession, i, arr) => 
                        <span key={profession.experience_years}>
                            {arr.length - 1 === i ? profession.profession.profession_name : `${profession.profession.profession_name}, `}
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
