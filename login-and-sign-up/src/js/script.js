let userContainer = document.getElementById("user-container")
async function loadUsers() {
    try {
        const response = await fetch("http://localhost:8080/listar/usuarios", {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        if (!response.ok) {
            throw new Error(`HTTP error ${response.status}`);
        }

        const users = await response.json();

        users.array.forEach(element => {
            const card = document.createElement("div")
            card.className = "card"
            card.innerHTML = `
                <h2>${element.name}</h2>
                <p>nome: ${element.name}</p>
                <p>email: ${element.email}</p>
                <p>senha: ${element.password}</p>
                `
            userContainer.appendChild(card)
        });

        console.log(users);
        alert(users);
    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao conectar ao servidor.");
    };
}

loadUsers();