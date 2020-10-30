
  // ----------------------------------------- Used to keep manager inside same tab when completing a reimb -----------------------------------------

  let whichTab = 0;

 // ----------------------------------------- Rendering table -----------------------------------------

function renderTable(reimbursements) {
    for (const reimbursement of reimbursements) {
      // Creating new boxes in table to be filled
      const tr = document.createElement("tr");
      const buttonTd = document.createElement("td");
      const idTd = document.createElement("td");
      const amountTd = document.createElement("td");
      const submissionTd = document.createElement("td");
      const resolutionTd = document.createElement("td");
      const descriptionTd = document.createElement("td");
      const receiptTd = document.createElement("td");
      const authorTd = document.createElement("td");
      const resolverTd = document.createElement("td");
      const statusTd = document.createElement("td");
      const categoryTd = document.createElement("td");

      // Approve button
      const approveButton = document.createElement("button");
      approveButton.innerHTML = "A";
      approveButton.addEventListener('click', function(){approveReimbursement(reimbursement.id)});
      approveButton.className = "btn btn-success btn-sm";
      approveButton.id = reimbursement.id;
      if(reimbursement.status!=="Pending") {
        approveButton.setAttribute("hidden", "true");
      }
      buttonTd.append(approveButton);

      // Deny button
      const denyButton = document.createElement("button");
      denyButton.innerHTML = "D";
      denyButton.addEventListener('click', function(){denyReimbursement(reimbursement.id)});
      denyButton.className = "btn btn-danger btn-sm";
      denyButton.id = reimbursement.id;
      if(reimbursement.status!=="Pending") {
        denyButton.setAttribute("hidden", "true");
      }
      buttonTd.append(denyButton);

      // Filling in rest of table
      idTd.innerText = reimbursement.id;
      amountTd.innerText = "$"+reimbursement.amount;

      // This is super ugly, but adds a zero in front of entries that require it.
      submissionTd.innerText = `${reimbursement.submitted.month} ${reimbursement.submitted.dayOfMonth}, ${reimbursement.submitted.year} `;
      if(reimbursement.submitted.hour<10) {
        submissionTd.innerText += `0${reimbursement.submitted.hour}:`
      } else {
        submissionTd.innerText += `${reimbursement.submitted.hour}:`
      }
      if(reimbursement.submitted.minute<10) {
        submissionTd.innerText += `0${reimbursement.submitted.minute}:`
      } else {
        submissionTd.innerText += `${reimbursement.submitted.minute}:`
      }
      if(reimbursement.submitted.second<10) {
        submissionTd.innerText += `0${reimbursement.submitted.second}`
      } else {
        submissionTd.innerText += `${reimbursement.submitted.second}`
      }
      if(reimbursement.resolved===null) {
        resolutionTd.innerText = reimbursement.resolved;
      } else {
          resolutionTd.innerText = `${reimbursement.resolved.month} ${reimbursement.resolved.dayOfMonth}, ${reimbursement.resolved.year} ${reimbursement.resolved.hour}:${reimbursement.resolved.minute}:${reimbursement.resolved.second}`;
      }
    descriptionTd.innerText = reimbursement.description;
    receiptTd.innerText = reimbursement.receipt;
    authorTd.innerText = reimbursement.author;
    resolverTd.innerText = reimbursement.resolver;
    const stringThing = reimbursement.status;
    statusTd.innerText = reimbursement.status;
    if(reimbursement.status==="Pending") {
      statusTd.style.color = "#888800";
    }
    if(reimbursement.status==="Approved") {
      statusTd.style.color = "#009900";
    }
    if(reimbursement.status==="Denied") {
      statusTd.style.color = "#BB0000";
    }
    categoryTd.innerText = reimbursement.type;
      tr.append(buttonTd, idTd, amountTd, submissionTd, resolutionTd, descriptionTd, receiptTd, authorTd, resolverTd, statusTd, categoryTd);
      document.getElementById("reimbTableBody").append(tr);
    }
  };

  async function asyncFetch(url, expression){
    const response = await fetch(url);
    const json = await response.json();
    expression(json);
  };
  
  asyncFetch(
  "http://localhost:8080/reimbursement/allR.json",
  renderTable
  );

  // ----------------------------------------- Render table buttons -----------------------------------------
  document.getElementById('sortAll').addEventListener('click', sortTableAll);
  document.getElementById('sortPending').addEventListener('click', sortTablePending);
  document.getElementById('sortCompleted').addEventListener('click', sortTableCompleted);

  async function sortTableAll() {
    document.getElementById('notification').innerHTML='  ';
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/reimbursement/allR.json", renderTable);
    whichTab = 0;
  }

  async function sortTablePending() {
    document.getElementById('notification').innerHTML='  ';
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/reimbursement/showPending.json", renderTable);
    whichTab = 1;
  }

  async function sortTableCompleted() {
    document.getElementById('notification').innerHTML='  ';
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/reimbursement/showCompleted.json", renderTable);
  }

  // ----------------------------------------- Employee registration -----------------------------------------
document.getElementById('registerU').addEventListener('click', registerUser);
async function registerUser() {
  document.getElementById('notification').innerHTML='  ';
    const user = {
      username:document.getElementById('usernameNU').value,
      password:document.getElementById('passwordNU').value,
      firstname:document.getElementById('firstnameNU').value,
      lastname:document.getElementById('lastnameNU').value,
      email:document.getElementById('emailNU').value
    }
    const fetched = await fetch('http://localhost:8080/reimbursement/register.json', {
      method:'post',
      body: JSON.stringify(user)
    });
    const json = await fetched.text();
    document.getElementById('submission').innerHTML='  ';
    document.getElementById('submission').append(json);
    document.getElementById('submission').style.color = "#FFFF00";
    document.getElementById('submission').style.fontSize = "larger";
  }
  
  // ----------------------------------------- Approve/Deny Reimbursements -----------------------------------------
  // document.getElementById('approveButton').addEventListener('click', approveReimbursement);
  // document.getElementById('denyButton').addEventListener('click', denyReimbursement);

// Approve
async function approveReimbursement(approveB) {
    const reimbursement = {
      id:approveB,
      resolver:localStorage.getItem("key")
    }
    const fetched = await fetch('http://localhost:8080/reimbursement/approved.json', {
      method:'post',
      body: JSON.stringify(reimbursement)
    });
    const json = await fetched.text();
    document.getElementById('notification').innerHTML='  ';
    document.getElementById('notification').append(json);
    document.getElementById('notification').style.color = "#00FF00";
    document.getElementById('notification').style.fontSize = "larger";
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    if(whichTab === 1) {
      asyncFetch("http://localhost:8080/reimbursement/showPending.json", renderTable);
    } else {
      asyncFetch("http://localhost:8080/reimbursement/allR.json", renderTable);
    }
}

// Deny
async function denyReimbursement(denyB) {
  const reimbursement = {
    id:denyB,
    resolver:localStorage.getItem("key")
  }
  const fetched = await fetch('http://localhost:8080/reimbursement/denied.json', {
    method:'post',
    body: JSON.stringify(reimbursement)
  });
  const json = await fetched.text();
  document.getElementById('notification').innerHTML='  ';
  document.getElementById('notification').append(json);
  document.getElementById('notification').style.color = "#FFFF00";
  document.getElementById('notification').style.fontSize = "larger";
  const rows = document.getElementById('reimbTableBody').innerHTML='';
  if(whichTab === 1) {
    asyncFetch("http://localhost:8080/reimbursement/showPending.json", renderTable);
  } else {
    asyncFetch("http://localhost:8080/reimbursement/allR.json", renderTable);
  }
}

// Generating text above employee table
const tableTop = document.getElementById("empName");
const text = document.createTextNode(`${localStorage.getItem("key3")} ${localStorage.getItem("key4")}!`);
tableTop.appendChild(text);

console.log('howdy')