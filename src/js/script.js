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

        users.forEach(element => {
            const card = document.createElement("div")
            card.className = "card"
            card.innerHTML = `
                <p>Id: ${element.id}</p>
                <p>Nome: ${element.name}</p>
                <p>Email: ${element.email}</p>
                <p>Senha: ${element.password}</p>
                `
            userContainer.appendChild(card)
        });

    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao conectar ao servidor.");
    };
}

async function deleteUser() {
    try {
        const response = await fetch("http://localhost:8080/delete/usuario/{id}", {
            method: "DELETE",
            headers: { "Content-Type": "application/json" }
        });

        if (!response.ok) {
            throw new Error(`HTTP error ${response.status}`);
        }



    } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao conectar ao servidor.");
    }
}

loadUsers();