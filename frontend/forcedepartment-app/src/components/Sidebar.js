import './Header.css';
import './MenuPoint';
import { useState, useEffect } from 'react'
import MenuPoint from './MenuPoint';


const Sidebar = () => {
    const [showProfessions, setShowProfessions] = useState(false);

    const [showWorkObjects, setShowWorkObjects] = useState(false);

    const [professions, setProfessions] = useState([]);

    const [workObjects, setWorkObjects] = useState([]);

    useEffect(() => {
        const getMenuPoints = async() => {
            const professionsFromServer = await fetchMenuPoint("Profession");
            setProfessions(professionsFromServer);

            const workObjectsFromServer = await fetchMenuPoint("WorkObject");
            setWorkObjects(workObjectsFromServer);
        }
        getMenuPoints();
    }, [])

    const fetchMenuPoint = async(menuPoint) => {
        const response = await(fetch(`http://localhost:8080/api/getAll${menuPoint}`));
        const data = await response.json();
        console.log(data);
        return data;
    }

    return (
        <div id="sidebar" className="sidenav">
            <MenuPoint  
                menuTitle="Professions"
                allMenuPoints={professions} 
                showMenuPoint={showProfessions}
                listMenuPoint={() => setShowProfessions(!showProfessions)}
            />
            <MenuPoint  
                menuTitle="Work Object"
                allMenuPoints={workObjects} 
                showMenuPoint={showWorkObjects}
                listMenuPoint={() => setShowWorkObjects(!showWorkObjects)}
            />
      
            <div>Extra Search</div>
        </div>
    )
}


export default Sidebar