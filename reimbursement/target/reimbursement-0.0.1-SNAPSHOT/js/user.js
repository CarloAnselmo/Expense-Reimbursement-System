import myScripts from "./myScripts";

document.getElementById('tester').addEventListener('click', testa);

async function testa() {
    alert(`Welcome, ${myScripts.username}`);
}