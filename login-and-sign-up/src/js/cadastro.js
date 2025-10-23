const form = document.getElementById("loginForm");

    form.addEventListener("submit", async (event) => {
      event.preventDefault();

      const name = document.getElementById("name").value
      const email = document.getElementById("email").value;
      const password = document.getElementById("password").value;
        
      const user = { name, email, password };

      try {
        const response = await fetch("http://localhost:8080/usuario/cadastro", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(user)
        });

        const data = await response.text();
        alert(data);
      } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao conectar ao servidor.");
      }
    });