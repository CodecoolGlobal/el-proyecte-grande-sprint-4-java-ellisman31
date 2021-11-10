
import './Sidebar.css';
import './MenuPoint';
import { useState, useEffect } from 'react'
import MenuPoint from './MenuPoint';


const Sidebar = (props) => {
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
        return data;
    }

    return (
        <div id="sidebar" className="sidenav">
            <MenuPoint  
                menuTitle="Professions"
                allMenuPoints={professions} 
                showMenuPoint={showProfessions}
                listMenuPoint={() => setShowProfessions(!showProfessions)}
                sideBarHandler={props.sideBarHandler}
                fetchRoutePart={"Profession"}
            />
            <MenuPoint  
                menuTitle="Work Object"
                allMenuPoints={workObjects} 
                showMenuPoint={showWorkObjects}
                listMenuPoint={() => setShowWorkObjects(!showWorkObjects)}
                sideBarHandler={props.sideBarHandler}
                fetchRoutePart={"WorkObject"}
            />
            <MenuPoint 
                menuTitle="Extra Search"
                allMenuPoints=""
                showMenuPoint=""
                listMenuPoint=""
            />
        </div> 
    )
}


export default Sidebar