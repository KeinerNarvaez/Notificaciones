document.getElementById('signupForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const container = document.querySelector('.container');
    const submitBtn = document.querySelector('.submit-btn');
    const nameInput = document.getElementById('name');
    const emailInput = document.getElementById('dose');

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
            "dose": emailInput.value,
        });

        const response = await fetch("http://localhost:8080/api/v1/medicine/", {
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
            submitBtn.innerHTML = 'Crear medicamento';
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