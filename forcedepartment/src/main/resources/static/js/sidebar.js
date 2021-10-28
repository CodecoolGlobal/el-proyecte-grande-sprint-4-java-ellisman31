const container = document.getElementById('worker-container');
const sidebar = document.getElementById('sidebar');

const sidebarProfession = document.getElementById('profession-search');
const sidebarWorkObject = document.getElementById('work-objects-search');
const sidebarButtons = [sidebarProfession, sidebarWorkObject];

const allProfessionUrl = '/api/getAllProfession';
const allWorkObjectUrl = '/api/getAllWorkObject';

function myTimer() {
    sidebar.style.height = `${container.scrollHeight-200}px`
}

myTimer();

function getAllType() {
    fetch('/api/getAllProfession')
        .then(response => response.json())
        .then(data => console.log(data))
}

getAllType();


function activateSideBarButton(button, url) {
    button.addEventListener('click', (e) => {
        e.preventDefault();

        if (button.childNodes.length < 2) {
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    for (const element of data) {
                        let node = document.createElement("LI");
                        let textNode = document.createTextNode(element);
                        node.appendChild(textNode);
                        node.style.fontSize = '18px'
                        node.style.marginLeft = '20px';
                        node.addEventListener('mouseover', ()=> {
                            node.style.color = 'white';
                        })
                        node.addEventListener('mouseleave', ()=> {
                            node.style.color = '#818181';
                        })
                        button.appendChild(node)
                    }
                })
        } else {
            let nodeAmount = button.childNodes.length;
            for (let i = 1; i <= nodeAmount; i++) {
                button.removeChild(button.childNodes[1]);
            }
        }
        console.log(sidebar.childNodes);
    })
}

activateSideBarButton(sidebarProfession, allProfessionUrl);
activateSideBarButton(sidebarWorkObject, allWorkObjectUrl);

