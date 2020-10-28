
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
      submissionTd.innerText = `${reimbursement.submitted.month} ${reimbursement.submitted.dayOfMonth}, ${reimbursement.submitted.year} ${reimbursement.submitted.hour}:${reimbursement.submitted.minute}:${reimbursement.submitted.second}`;
      if(reimbursement.resolved===null) {
        resolutionTd.innerText = reimbursement.resolved;
      } else {
          resolutionTd.innerText = `${reimbursement.resolved.month} ${reimbursement.resolved.dayOfMonth}, ${reimbursement.resolved.year} ${reimbursement.resolved.hour}:${reimbursement.resolved.minute}:${reimbursement.resolved.second}`;
      }
    descriptionTd.innerText = reimbursement.description;
    receiptTd.innerText = reimbursement.receipt;
    authorTd.innerText = reimbursement.author;
    resolverTd.innerText = reimbursement.resolver;
    statusTd.innerText = reimbursement.status;
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
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/reimbursement/allR.json", renderTable);
  }

  async function sortTablePending() {
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/reimbursement/showPending.json", renderTable);
  }

  async function sortTableCompleted() {
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/reimbursement/showCompleted.json", renderTable);
  }

  // ----------------------------------------- Employee registration -----------------------------------------
document.getElementById('registerU').addEventListener('click', registerUser);
async function registerUser() {
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
    console.log(json);
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/reimbursement/allR.json", renderTable);
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
    console.log(json);
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/reimbursement/allR.json", renderTable);
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
  console.log(json);
  const rows = document.getElementById('reimbTableBody').innerHTML='';
  asyncFetch("http://localhost:8080/reimbursement/allR.json", renderTable);
}

// Generating text above employee table
const tableTop = document.getElementById("empName");
const text = document.createTextNode(`${localStorage.getItem("key3")} ${localStorage.getItem("key4")}!`);
tableTop.appendChild(text);

console.log('howdy')