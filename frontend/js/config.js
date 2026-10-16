// Change this if your backend runs on a different address or port
const API_BASE = "http://localhost:8080";

function getToken() {
  return localStorage.getItem("token");
}

function authHeaders() {
  return { Authorization: "Bearer " + getToken() };
}

function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("username");
  localStorage.removeItem("role");
  window.location.href = "login.html";
}
