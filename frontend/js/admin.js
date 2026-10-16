if (!getToken() || localStorage.getItem("role") !== "ADMIN") {
  logout();
}

document.getElementById("logoutBtn").addEventListener("click", logout);

axios
  .get(API_BASE + "/api/admin/dashboard", { headers: authHeaders() })
  .then((response) => {
    document.getElementById("heading").textContent = response.data.message;
    document.getElementById("totalUsers").textContent = response.data.totalUsers;
  })
  .catch((error) => {
    if (error.response && (error.response.status === 401 || error.response.status === 403)) {
      logout();
    } else {
      document.getElementById("heading").textContent = "Cannot reach the server.";
    }
  });
