// document.getElementById('loginButton').addEventListener('click', loginFunction);

// function loginFunction() {
//     window.location.pathname = 'html/login.html';
// }

// export default username;

let username;
let role;

async function logout() {
  alert("You have logged out. Goodbye!");
  localStorage.setItem("key", null);
  localStorage.setItem("key2", null);
  document.getElementById("employeePortal").setAttribute("hidden", "true");
  document.getElementById("financeTools").setAttribute("hidden", "true");
}

async function loginFunction() {
  const user = {
    username: document.getElementById("nameU").value,
    password: document.getElementById("passU").value,
  };
  const fetched = await fetch(
    "http://localhost:8080/reimbursement/verify.json",
    {
      method: "post",
      body: JSON.stringify(user),
    }
  );
  const json = await fetched.text();
  const currentUser = JSON.parse(json);
  if (currentUser !== null) {
    username = currentUser.username;
    role = currentUser.role;
    firstname = currentUser.firstname;
    lastname = currentUser.lastname;
    localStorage.setItem("key", username);
    localStorage.setItem("key2", role);
    localStorage.setItem("key3", firstname);
    localStorage.setItem("key4", lastname);
    alert(
      "Welcome, " + currentUser.username + ".\nYour role: " + currentUser.role
    );

    if (currentUser.role === "employee") {
      document.getElementById("employeePortal").removeAttribute("hidden");
      document.getElementById('financeTools').setAttribute("hidden", "true");
    }

    if (currentUser.role === "finance_manager") {
      document.getElementById("financeTools").removeAttribute("hidden");
      document.getElementById('employeePortal').setAttribute("hidden", "true");
    }
  } else {
    alert("Invalid credentials!");
  }
}

document
  .getElementById("validateUser")
  .addEventListener("click", loginFunction);

console.log("howdy");
