import profilePic from '.././profile-icon-empty.png';
    
const WorkerCard = (props) => {
    return (
        <div className="worker-card">
            <div  className="worker-detail">
                <img src={profilePic}
                    alt={props.worker.imageName}></img> <br />
                    <h3>{`${props.worker.firstName} ${props.worker.lastName} (${props.worker.age})`}</h3><br />
                    <h4>Professions(s):</h4><br />
                    <span className="professions">
                        {props.worker.profession.map((profession, i, arr) => {
                            if (arr.length - 1 === i) {
                                return <span>{profession}</span>
                            } else {
                                return <span>{`${profession}, `}</span>
                            }
                        })}
                    </span>
            </div>
        </div>
    )
}



export default WorkerCard
