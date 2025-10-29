const form = document.getElementById("loginForm");

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  const user = { email, password };

  try {
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(user)
    });

    const data = await response.text();

    if (data == "Logado com sucesso!") {
      window.location.href = "../../index.html"
    }

  } catch (error) {
    console.error("Erro:", error);
    alert("Erro ao conectar ao servidor.");
  }
});