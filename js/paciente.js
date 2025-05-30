async function getPatientByEmail(email) {
    const url = `http://localhost:8080/api/v1/patient/email/${email}`;

    let headersList = {
        "Accept": "*/*",
        "User-Agent": "web",
        "Content-Type": "application/json"
    };

    try {
        const response = await fetch(url, {
            method: "GET",
            headers: headersList
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const usuarios = await response.json();
        console.log(usuarios);

        return usuarios.length === 0 ? "crear" : "denegado";

    } catch (error) {
        console.error("Error al obtener el paciente:", error);
        return "error";
    }
}

document.getElementById('signupForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const emailValue = document.getElementById('email').value;
    const accion = await getPatientByEmail(emailValue); 

    if (accion === "denegado") {
        alert("El paciente ya está registrado.");
        return;
    } else if (accion === "error") {
        alert("Ocurrió un error al verificar el paciente. Por favor, inténtelo de nuevo más tarde.");
        return; 
    }

    const container = document.querySelector('.container');
    const submitBtn = document.querySelector('.submit-btn');
    const nameInput = document.getElementById('name');
    const emailInput = document.getElementById('email');

    if (nameInput.value.trim() === '' || emailInput.value.trim() === '') {
        alert("Por favor, complete todos los campos.");
        container.style.animation = 'shake 0.5s ease-in-out';
        setTimeout(() => {
            container.style.animation = 'slideIn 0.8s ease-out';
        }, 500);
        return;
    }

    try {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        };

        let bodyContent = JSON.stringify({
            "name": nameInput.value,
            "email": emailInput.value,
        });

        const response = await fetch("http://localhost:8080/api/v1/patient/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        if (!response.ok) {
            console.error("Error al registrar el paciente:", response.status);
            alert("Ocurrió un error al registrar el paciente. Por favor, inténtelo de nuevo más tarde.");
            return;
        }

        const data = await response.text();
        console.log(data);

        // Animación de éxito
        submitBtn.innerHTML = '✓ ¡registrado!';
        submitBtn.style.background = 'linear-gradient(135deg, #10b981 0%, #059669 100%)';
        container.classList.add('success-animation');

        setTimeout(() => {
            submitBtn.innerHTML = 'Crear paciente';
            submitBtn.style.background = 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)';
            nameInput.value = '';
            emailInput.value = '';
            container.classList.remove('success-animation');
        }, 3000);

    } catch (error) {
        console.error("Error inesperado:", error);
        alert("Ocurrió un error al registrar el paciente. Por favor, inténtelo de nuevo más tarde.");
    }
});

