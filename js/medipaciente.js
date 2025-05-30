async function getListPatient() {
    const url = `http://localhost:8080/api/v1/patient/`;
  
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
        console.error("Error al obtener el paciente:", response.status);
        return;
      }
  
      const patien = await response.json();
      console.log(patien);
      
      const select = document.getElementById("selectPatient");
      patien.forEach(ListPatient => {
        const option = document.createElement("option");
        option.value = ListPatient["id_user"];
        option.textContent = ListPatient['_name'];
        select.appendChild(option);
      });
  
    } catch (error) {
      console.error("Error al obtener el país:", error);
    }
}
async function getListMedicine() {
    const url = `http://localhost:8080/api/v1/medicine/`;
  
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
        console.error("Error al obtener el paciente:", response.status);
        return;
      }
  
      const patien = await response.json();
      console.log(patien);
      
      const select = document.getElementById("selectMedicine");
      patien.forEach(ListPatient => {
        const option = document.createElement("option");
        option.value = ListPatient["id_medicine"];
        option.textContent = ListPatient['_name'];
        select.appendChild(option);
      });
  
    } catch (error) {
      console.error("Error al obtener el país:", error);
    }
}
document.getElementById('signupForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const container = document.querySelector('.container');
    const submitBtn = document.querySelector('.submit-btn');
    const medicineInput = document.getElementById('selectMedicine');
    const patientInput = document.getElementById('selectPatient');
    const timeInput = document.getElementById('time_reminder');

    if (medicineInput.value.trim() === '' || patientInput.value.trim() === ''|| timeInput.value.trim() === '') {
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
            "medicine": medicineInput.value,
            "patient": patientInput.value,
            "time_reminder": timeInput.value,
        });

        const response = await fetch("http://localhost:8080/api/v1/reminder/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        if (!response.ok) {
            console.error("Error al registrar medicamento al paciente:", response.status);
            alert("Ocurrió un error al registrar medicamento al paciente. Por favor, inténtelo de nuevo más tarde.");
            return;
        }

        const data = await response.text();
        console.log(data);

        // Animación de éxito
        submitBtn.innerHTML = '✓ ¡registrado!';
        submitBtn.style.background = 'linear-gradient(135deg, #10b981 0%, #059669 100%)';
        container.classList.add('success-animation');

        setTimeout(() => {
            submitBtn.innerHTML = 'Crear recordatorio de medicación';
            submitBtn.style.background = 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)';
            medicineInput.value = '';
            patientInput.value = '';
            container.classList.remove('success-animation');
        }, 3000);

    } catch (error) {
        console.error("Error inesperado:", error);
        alert("Ocurrió un error al registrar del medicamento al paciente. Por favor, inténtelo de nuevo más tarde.");
    }
});
document.addEventListener('DOMContentLoaded', function() {
    getListPatient();
    getListMedicine();
});
