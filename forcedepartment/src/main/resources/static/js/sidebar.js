const container = document.getElementById('worker-container');
const sidebar = document.getElementById('sidebar');

const sidebarProfession = document.getElementById('profession-search');

function myTimer() {
    sidebar.style.height = `${container.scrollHeight-200}px`
}

myTimer();

sidebarProfession.addEventListener('click', (e) => {
    e.preventDefault();
    let node = document.createElement("LI");
    let textnode = document.createTextNode("Water");
    node.appendChild(textnode);
    node.style.fontSize = '18px'
    node.style.marginLeft = '20px';
    if (sidebarProfession.childNodes.length < 2) {
        sidebarProfession.appendChild(node)
    } else {

        sidebarProfession.removeChild(sidebarProfession.childNodes[1]);
    }

})