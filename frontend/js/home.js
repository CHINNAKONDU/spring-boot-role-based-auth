if (!getToken()) {
  window.location.href = "login.html";
}

document.getElementById("logoutBtn").addEventListener("click", logout);
document.getElementById("roleBadge").textContent = localStorage.getItem("role") || "User";

axios
  .get(API_BASE + "/api/home", { headers: authHeaders() })
  .then((response) => {
    document.getElementById("welcome").textContent = response.data.message;
  })
  .catch((error) => {
    if (error.response && (error.response.status === 401 || error.response.status === 403)) {
      logout(); // token missing, expired or not allowed
    } else {
      document.getElementById("welcome").textContent = "Cannot reach the server.";
    }
  });
