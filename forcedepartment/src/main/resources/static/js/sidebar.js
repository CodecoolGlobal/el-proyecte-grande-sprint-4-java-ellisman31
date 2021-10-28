const container = document.getElementById('worker-container');
const sidebar = document.getElementById('sidebar');

const professionList = document.getElementById('profession-list');
const sidebarProfession = document.getElementById('profession-search');
const workObjectList = document.getElementById('work-object-list');
const sidebarWorkObject = document.getElementById('work-objects-search');
const sidebarButtons = [sidebarProfession, sidebarWorkObject];

const specificProfessionUrl = '/api/getAllWorkerByProfession/';
const allProfessionUrl = '/api/getAllProfession';
const specificWorkObjectUrl = '/api/getAllWorkerByWorkObject/';
const allWorkObjectUrl = '/api/getAllWorkObject';


const workerCardContainer = document.getElementById('worker-card-container');

function sidebarHeight() {
    console.log(container.scrollHeight);
    sidebar.style.height = `${container.scrollHeight-200}px`
}

sidebarHeight();


function activateSideBarButton(button, url, listDiv, url2) {
    button.addEventListener('click', (e) => {
        e.preventDefault();
        if (button.style.color === 'white') {
            button.style.color = '#818181';
        } else {
            button.style.color = 'white';
        }
        if (listDiv.childNodes.length < 2) {
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    for (const element of data) {
                        let node = document.createElement("LI");
                        let textNode = document.createTextNode(element);
                        node.appendChild(textNode);
                        node.style.fontSize = '18px'
                        node.style.marginLeft = '20px';
                        node.style.color = '#818181';
                        node.style.cursor = 'pointer';
                        node.addEventListener('mouseover', ()=> {
                            node.style.color = 'white';
                        })
                        node.addEventListener('mouseleave', ()=> {
                            node.style.color = '#818181';
                        })
                        node.addEventListener('click', (e) => {
                                node.style.color = 'white'
                                fetchWorkerCards(`${url2}${node.innerText}`);

                        })
                        listDiv.appendChild(node)

                    }
                })
        } else {
            listDiv.innerHTML = "";
        }
    })
}

activateSideBarButton(sidebarProfession, allProfessionUrl, professionList, specificProfessionUrl);
activateSideBarButton(sidebarWorkObject, allWorkObjectUrl, workObjectList, specificWorkObjectUrl);

function fetchWorkerCards(url) {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            let currentContent = "";
            for (let worker of data) {
                currentContent += `
                    <div class="worker-card">
                        <div class="worker-detail">
                            <a href="#">
                                <img src="/img/${worker.image}">
                                <br>
                                <h3>
                                    ${worker.firstName} ${worker.lastName} ( ${worker.age} )
                                </h3><br>
                                <h4>Profession(s): </h4>
                                <span class="professions">
                                <span>
                                    ${worker.profession}
                                </span>
                            </span><br><br>
                            </a>
                        </div>
                    </div>
                `
                workerCardContainer.innerHTML = currentContent;
            }
        })
    sidebarHeight();
}