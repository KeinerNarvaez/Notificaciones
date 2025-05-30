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

        const patients = await response.json();
        const totalPacientes = document.getElementById("totalPacientes");
        const list = document.getElementById("pacientesGrid");

        if (!totalPacientes || !list) return;

        totalPacientes.innerText = patients.length;
        list.innerHTML = '';

        if (patients.length === 0) {
            const noResult = document.createElement('div');
            noResult.className = 'no-results';
            noResult.innerText = 'No hay resultados de pacientes';
            list.appendChild(noResult);
            return;
        }

        patients.forEach(p => {
            const card = document.createElement('div');
            card.className = 'data-card';

            const cardContent = `
                <div class="card-header">
                    <div class="card-title">${p._name}</div>
                    <div class="card-actions">
                        <button class="action-btn delete-btn" onclick="deleteItem('paciente',${p.id_user})" title="Eliminar">🗑️</button>
                    </div>
                </div>
                <div class="card-content">
                    <div class="card-info">
                        <div class="info-row">
                            <span class="info-label">Email:</span>
                            <span class="info-value">${p.email}</span>
                        </div>
                        <div class="info-row" style="display:none;">
                            <span class="info-label">Estado:</span>
                            <span class="status-badge">${p.status}</span>
                        </div>
                    </div>
                </div>
            `;
            card.innerHTML = cardContent;
            list.appendChild(card);
        });

    } catch (error) {
        console.error("Error al obtener el paciente:", error);
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
            console.error("Error al obtener el medicamento:", response.status);
            return;
        }

        const medicines = await response.json();
        const totalMedicamentos = document.getElementById("totalMedicamentos");
        const list = document.getElementById("medicamentosGrid");

        if (!totalMedicamentos || !list) return;

        totalMedicamentos.innerText = medicines.length;
        list.innerHTML = '';

        if (medicines.length === 0) {
            const noResult = document.createElement('div');
            noResult.className = 'no-results';
            noResult.innerText = 'No hay resultados de medicamentos';
            list.appendChild(noResult);
            return;
        }

        medicines.forEach(m => {
            const card = document.createElement('div');
            card.className = 'data-card';

            const cardContent = `
                <div class="card-header">
                    <div class="card-title">${m._name}</div>
                    <div class="card-actions">
                        <button class="action-btn delete-btn" onclick="deleteItem('medicamento',${m.id_medicine})" title="Eliminar">🗑️</button>
                    </div>
                </div>
                <div class="card-content">
                    <div class="info-row">
                        <span class="info-label">Gramos:</span>
                        <span class="info-value">${m._dose}</span>
                    </div>
                </div>
            `;
            card.innerHTML = cardContent;
            list.appendChild(card);
        });

    } catch (error) {
        console.error("Error al obtener el medicamento:", error);
    }
}


async function getListReminder() {
    const url = `http://localhost:8080/api/v1/reminder/active/`;

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
            console.error("Error al obtener el recordatorio:", response.status);
            return;
        }

        const reminders = await response.json();
        const totalRecordatorios = document.getElementById("totalRecordatorios");
        const list = document.getElementById("recordatoriosGrid");

        if (!totalRecordatorios || !list) return;

        totalRecordatorios.innerText = reminders.length;
        list.innerHTML = '';

        if (reminders.length === 0) {
            const noResult = document.createElement('div');
            noResult.className = 'no-results';
            noResult.innerText = 'No hay resultados de recordatorios';
            list.appendChild(noResult);
            return;
        }

        reminders.forEach(r => {
            const card = document.createElement('div');
            card.className = 'data-card';

            const cardContent = `
                <div class="card-header">
                    <div class="card-title">${r.patient._name}</div>
                    <div class="card-actions">
                        <button class="action-btn delete-btn" onclick="deleteItem('recordatorio',${r.id_reminder})" title="Eliminar">🗑️</button>
                    </div>
                </div>
                <div class="card-content">
                    <div class="info-row">
                        <span class="info-label">Fecha:</span>
                        <span class="info-value">${r.date_reminder}</span>
                    </div>
                    <div class="info-row">
                        <span class="info-label">Medicina:</span>
                        <span class="info-value">${r.medicine._name}</span>
                    </div>
                </div>
            `;
            card.innerHTML = cardContent;
            list.appendChild(card);
        });

    } catch (error) {
        console.error("Error al obtener el recordatorio:", error);
    }
}


async function getBinnacle() {
    const url = `http://localhost:8080/api/v1/binnacle/confirmation/Se envio correctamente`;
  
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
        console.error("Error al obtener el bitácora:", response.status);
        return;
      }
  
      const binnacle = await response.json();
      console.log(binnacle);
      document.getElementById("totalBitacoras").innerText = binnacle.length;
    } catch (error) {
      console.error("Error al obtener el bitácora:", error);
    }
}
async function deleteItem(tipo, id) {
    let endpoint = '';
    let listFunction = null;

    switch (tipo) {
        case 'paciente':
            endpoint = `http://localhost:8080/api/v1/patient/${id}`;
            listFunction = getListPatient;
            break;
        case 'medicamento':
            endpoint = `http://localhost:8080/api/v1/medicine/${id}`;
            listFunction = getListMedicine;
            break;
        case 'recordatorio':
            endpoint = `http://localhost:8080/api/v1/reminder/${id}`;
            listFunction = getListReminder;
            break;
        default:
            console.error("Tipo desconocido:", tipo);
            return;
    }

    if (!confirm(`¿Estás seguro de eliminar este ${tipo}?`)) return;

    try {
        const response = await fetch(endpoint, {
            method: 'DELETE',
            headers: {
                "Accept": "*/*",
                "User-Agent": "web",
                "Content-Type": "application/json"
            }
        });

        if (!response.ok) {
            console.error(`Error al eliminar el ${tipo}:`, response.status);
            return;
        }

        // Mostrar mensaje opcional o feedback visual
        alert(`${tipo} eliminado correctamente`);

        // Actualiza la lista
        if (typeof listFunction === 'function') {
            listFunction();
        }

    } catch (error) {
        alert(`Error al eliminar el ${tipo}:`, error,`puede que el ${tipo} tenga recordatorios asociados`);
    }
}


document.addEventListener("DOMContentLoaded", function() {
    getListPatient();
    getListMedicine();
    getListReminder();
    getBinnacle();

});