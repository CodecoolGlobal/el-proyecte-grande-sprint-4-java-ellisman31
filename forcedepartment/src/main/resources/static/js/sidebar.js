const container = document.getElementById('worker-container');
const sidebar = document.getElementById('sidebar');

const sidebarProfession = document.getElementById('profession-search');

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


sidebarProfession.addEventListener('click', (e) => {
    e.preventDefault();

    if (sidebarProfession.childNodes.length < 2) {




        fetch('/api/getAllProfession')
            .then(response => response.json())
            .then(data => {
                for (const element of data) {
                    let node = document.createElement("LI");
                    let textNode = document.createTextNode(element);
                    node.appendChild(textNode);
                    node.style.fontSize = '18px'
                    node.style.marginLeft = '20px';
                    sidebarProfession.appendChild(node)
                }
            })

    } else {
        let nodeAmount = sidebarProfession.childNodes.length;
        for (let i = 1; i <= nodeAmount; i++) {
            sidebarProfession.removeChild(sidebarProfession.childNodes[1]);
        }

    }

})