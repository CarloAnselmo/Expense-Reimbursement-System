function renderTable(reimbursements) {
    for (const reimbursement of reimbursements) {
      const tr = document.createElement("tr");
      const idTd = document.createElement("td");
      const amountTd = document.createElement("td");
      const submissionTd = document.createElement("td");
      const resolutionTd = document.createElement("td");
      const descriptionTd = document.createElement("td");
      const receiptTd = document.createElement("td");
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
    resolverTd.innerText = reimbursement.resolver;
    statusTd.innerText = reimbursement.status;
    categoryTd.innerText = reimbursement.type;
      tr.append(idTd, amountTd, submissionTd, resolutionTd, descriptionTd, receiptTd, resolverTd, statusTd, categoryTd);
      document.getElementById("reimbTableBody").append(tr);
    }
  };

  // async function asyncFetch(url, expression){
  //   const response = await fetch(url);
  //   const json = await response.json();
  //   expression(json);
  // };
  
  populateTable();

  async function populateTable() {
    const currentUserName = localStorage.getItem("key");
    const fetched = await fetch("http://localhost:8080/reimbursement/specR.json?name="+currentUserName, {
      method:'post'
    });
    const json = await fetched.json();
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    renderTable(json);
  }
  
document.getElementById('submitR').addEventListener('click', addReimbursement);

async function addReimbursement() {
    const reimbursement = {
      amount:document.getElementById('amountF').value,
      description:document.getElementById('descriptionF').value,
      type_id:document.getElementById('categoriesF').value,
      author:localStorage.getItem("key")
    }
    const fetched = await fetch('http://localhost:8080/reimbursement/empReim.json', {
      method:'post',
      body: JSON.stringify(reimbursement)
    });
    const json = await fetched.text();
    console.log(json);
    populateTable();
  }
  
  document.getElementById('deleteButton').addEventListener('click', deleteReimbursement);

async function deleteReimbursement() {
    const reimbId = document.getElementById('reimbId').value
    const fetched = await fetch("http://localhost:8080/reimbursement/removed.json?id="+reimbId, {
      method:'post'
    });
    const json = await fetched.text();
    console.log(json);
    populateTable();
}

// Generating text above employee table
const tableTop = document.getElementById("empName");
const text = document.createTextNode(`${localStorage.getItem("key3")} ${localStorage.getItem("key4")}!`);
tableTop.appendChild(text);

console.log("howdy");