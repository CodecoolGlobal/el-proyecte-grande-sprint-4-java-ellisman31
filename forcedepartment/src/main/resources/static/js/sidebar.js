const container = document.getElementById('worker-container');
const sidebar = document.getElementById('sidebar');


function myTimer() {
    sidebar.style.height = `${container.scrollHeight-200}px`
}

myTimer();