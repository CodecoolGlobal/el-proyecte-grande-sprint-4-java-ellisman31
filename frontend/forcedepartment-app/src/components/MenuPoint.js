

const MenuPoint = (props) => {
    return (
        <>
            <div onClick={props.listMenuPoint}>{props.menuTitle}</div>
            {props.showMenuPoint && 
                <ul>
                    {props.allMenuPoints.map((menuPoint) => (
                        <li>{menuPoint}</li>))}
                </ul>
            }
        </>
    )
}

export default MenuPoint            
