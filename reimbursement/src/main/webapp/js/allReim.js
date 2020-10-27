function renderTable(reimbursements) {
    for (const reimbursement of reimbursements) {
      const tr = document.createElement("tr");
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
      tr.append(idTd, amountTd, submissionTd, resolutionTd, descriptionTd, receiptTd, authorTd, resolverTd, statusTd, categoryTd);
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
  document.getElementById('approveButton').addEventListener('click', approveReimbursement);
  document.getElementById('denyButton').addEventListener('click', denyReimbursement);

async function approveReimbursement() {
    const reimbId = document.getElementById('reimbIdApprove').value
    const fetched = await fetch("http://localhost:8080/reimbursement/approved.json?approve="+reimbId, {
      method:'post'
    });
    const json = await fetched.text();
    console.log(json);
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/reimbursement/allR.json", renderTable);
}

async function denyReimbursement() {
  const reimbId = document.getElementById('reimbIdDeny').value
  const fetched = await fetch("http://localhost:8080/reimbursement/denied.json?deny="+reimbId, {
    method:'post'
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