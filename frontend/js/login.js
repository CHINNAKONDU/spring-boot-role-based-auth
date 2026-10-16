const form = document.getElementById("loginForm");
const message = document.getElementById("message");
const title = document.getElementById("title");
const subtitle = document.getElementById("subtitle");
const submitBtn = document.getElementById("submitBtn");
const tabs = document.querySelectorAll(".tab");

let mode = "user";

tabs.forEach((tab) => {
  tab.addEventListener("click", () => {
    mode = tab.dataset.mode;
    tabs.forEach((t) => t.classList.toggle("active", t === tab));

    if (mode === "admin") {
      title.textContent = "Admin login";
      subtitle.textContent = "Sign in to open the admin dashboard.";
    } else {
      title.textContent = "User login";
      subtitle.textContent = "Sign in to open your home page.";
    }
    message.textContent = "";
  });
});

form.addEventListener("submit", async (event) => {
  event.preventDefault();
  message.textContent = "";
  submitBtn.disabled = true;

  const url = mode === "admin"
    ? API_BASE + "/api/auth/admin/login"
    : API_BASE + "/api/auth/login";

  try {
    const response = await axios.post(url, {
      username: document.getElementById("username").value.trim(),
      password: document.getElementById("password").value,
    });

    localStorage.setItem("token", response.data.token);
    localStorage.setItem("username", response.data.username);
    localStorage.setItem("role", response.data.role);

    window.location.href = response.data.role === "ADMIN" ? "admin.html" : "home.html";
  } catch (error) {
    if (error.response) {
      message.textContent = error.response.data.message || "Login failed. Check your details and try again.";
    } else {
      message.textContent = "Cannot reach the server. Start the Spring Boot backend on port 8080.";
    }
  } finally {
    submitBtn.disabled = false;
  }
});
