import { useState, useEffect } from 'react'
import './ExtraSearch.css';

const ExtraSearch = (props) => {

    const [currentNamePart, setCurrentNamePart] = useState("");
    const [currentMinimumRate, setCurrentMinimumRate] = useState(0);
    const [currentProfession, setCurrentProfession] = useState("");
    const [showCurrentProfessionMenu, setShowCurrentProfessionMenu] = useState(false);
    const [currentWorkObject, setCurrentWorkObject] = useState("");
    const [showCurrentWorkObjectMenu, setShowCurrentWorkObjectMenu] = useState(false);

    useEffect(() => {
        props.listWorkers(currentNamePart, currentWorkObject, currentProfession, currentMinimumRate);
    }, [currentProfession, currentWorkObject, currentNamePart, currentMinimumRate])

    return (
        <div>
            <div className="mainMenuPoint" onClick={() => {
                props.listMenuPoint();
                setCurrentNamePart("");
                setCurrentMinimumRate(0);
                setCurrentProfession("");
                setCurrentWorkObject("");
            }}>Extra Search</div>
            {props.showMenuPoint && 
            <div>
                <div id="nameExtraSearch">
                    <label htmlFor="nameSearch" className="extraMenuPoint">Name Search</label>
                    <input 
                        onChange={(event) => {
                            event.target.value === '' ? setCurrentNamePart("") : setCurrentNamePart(event.target.value);
                        }}
                        type="text" id="nameSearch" name="nameSearch" className="nameSearch" value={
                            currentNamePart === "" ? '' : currentNamePart}>
                    </input>
                </div>
                <div>
                    <label htmlFor="minimumRate" className="extraMenuPoint">Minimum Rating</label>
                    <input
                        onChange={(event) => {
                            event.target.value === '' ? setCurrentMinimumRate(0) :
                            setCurrentMinimumRate(event.target.value)}}
                        type="number" id="minimumRate" name="minimumRate" className="minimumRate"
                        min="0" max="10"></input>
                </div>
                <div id="professionExtraSearch">
                    <h4 className="extraMenuPoint" onClick={() => {
                        setShowCurrentProfessionMenu(!showCurrentProfessionMenu);
                        setCurrentProfession("");
                    }}>Professions</h4>
                    {showCurrentProfessionMenu &&
                        <div> 
                            {props.allProfessions.data.map((profession) => (
                            <div key={profession}> 
                                <label className="menuPoint" htmlFor={profession}>
                                    <input 
                                        
                                        onClick={() => {
                                            setCurrentProfession(profession);
                                        }}
                                        type="radio" id={profession} value={profession} name="profession" />
                                        {" " + profession}
                                </label>
                            </div>))}
                        </div>
                    }
                </div>
                <div id="workObjectExtraSearch">
                    <h4 className="extraMenuPoint" onClick={() => {
                        setShowCurrentWorkObjectMenu(!showCurrentWorkObjectMenu);
                        setCurrentWorkObject("");
                    }}>Work Object</h4>
                    {showCurrentWorkObjectMenu && 
                        <div>
                            {props.allWorkObjects.data.map((workObject) => (
                                <div key={workObject}>
                                    <label className="menuPoint" htmlFor={workObject}>
                                        <input 
                                        
                                        onClick={() => {
                                            setCurrentWorkObject(workObject);
                                
                                        }}
                                        type="radio" id={workObject} value={workObject} name="workObject" />
                                        {" " + workObject}</label>
                                </div>
                            ))}
                        </div>
                    }
                </div>
            </div>
            }
        </div>
    )
}

export default ExtraSearch
