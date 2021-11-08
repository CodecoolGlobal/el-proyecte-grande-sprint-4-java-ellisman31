import './Header.css';
import { useState, useEffect } from 'react'


const Sidebar = () => {
    const [professions, setProfessions] = useState([]);

    useEffect(() => {
        const getProfessions = async() => {
            const professionsFromServer = await fetchProfessions();
            setProfessions(professionsFromServer);
        }
        getProfessions();
    }, [])

    const fetchProfessions = async() => {
        const response = await(fetch("http://localhost:8080/api/getAllProfession"));
        const data = await response.json();
        console.log(data);
        return data;
    }


    return (
        <div id="sidebar" className="sidenav">
            <div>Professions</div>
            <ul>
                {professions.map((profession) => (
                    <li>{profession}</li>
                ))}
            </ul>
            <div>Work Object</div>
            <div>Extra Search</div>
        </div>
    )
}


export default Sidebar
