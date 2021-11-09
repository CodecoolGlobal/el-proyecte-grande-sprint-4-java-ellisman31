

const MenuPoint = (props) => {
    return (
        <>
            <div className="mainMenuPoint" onClick={props.listMenuPoint}>{props.menuTitle}</div>
            {props.showMenuPoint && 
                <ul>
                    {props.allMenuPoints.map((menuPoint) => (
                        <li className="menuPoint">{menuPoint}</li>))}
                </ul>
            }
        </>
    )
}

export default MenuPoint            
