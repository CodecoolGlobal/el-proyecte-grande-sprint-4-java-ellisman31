import './Header.css';
import WorkerCard from "./worker_card/WorkerCard";

const WorkerFeed = (props) => {

    return (
        <div>
            {props.workers.data?.length === 0 ?
                <div > <p id="no-worker-found">No worker found!</p> </div> :
                <div id="worker-card-container">
                    {props.workers.data?.map((worker) => (
                    <WorkerCard key={worker.user.id} worker={worker} />))}
                </div>
            }
        </div>

    )
}


export default WorkerFeed