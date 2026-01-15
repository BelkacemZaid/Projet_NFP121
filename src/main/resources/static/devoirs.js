const API_URL = 'http://localhost:8082';

async function loadDevoirs() {
    try {
        const response = await fetch(`${API_URL}/devoir/getAllDevoirs`);
        const devoirs = await response.json();

        const tbody = document.getElementById('devoirsBody');
        tbody.innerHTML = '';

        devoirs.forEach(devoir => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${devoir.id}</td>
                <td>${devoir.description}</td>
                <td>${devoir.idMat ? devoir.idMat.denomination : '-'}</td>
                <td>${devoir.idClasse ? devoir.idClasse.denomination : '-'}</td>
                <td>${devoir.categorie}</td>
                <td>${devoir.coef}</td>
                <td>${devoir.dateCrea || '-'}</td>
                <td>
                    <button class="btn btn-danger" onclick="deleteDevoir(${devoir.id})">🗑️ Supprimer</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        showAlert('Erreur lors du chargement des devoirs', 'error');
    }
}

async function loadClasses() {
    try {
        const response = await fetch(`${API_URL}/classe/getAllClasses`);
        const classes = await response.json();

        const select = document.getElementById('classe');
        select.innerHTML = '<option value="">-- Sélectionner --</option>';

        classes.forEach(classe => {
            const option = document.createElement('option');
            option.value = classe.id;
            option.textContent = classe.denomination;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Erreur chargement classes:', error);
    }
}

async function loadMatieres() {
    try {
        const response = await fetch(`${API_URL}/matieres/getAllMatieres`);
        const matieres = await response.json();

        const select = document.getElementById('matiere');
        select.innerHTML = '<option value="">-- Sélectionner --</option>';

        matieres.forEach(matiere => {
            const option = document.createElement('option');
            option.value = matiere.id;
            option.textContent = matiere.denomination;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Erreur chargement matières:', error);
    }
}

function openAddModal() {
    document.getElementById('modalTitle').textContent = 'Nouveau Devoir';
    document.getElementById('devoirForm').reset();
    document.getElementById('coef').value = '1.0';
    loadClasses();
    loadMatieres();
    document.getElementById('devoirModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('devoirModal').style.display = 'none';
}

document.getElementById('devoirForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const devoir = {
        description: document.getElementById('description').value,
        idClasse: { id: parseInt(document.getElementById('classe').value) },
        idMat: { id: parseInt(document.getElementById('matiere').value) },
        categorie: document.getElementById('categorie').value,
        coef: parseFloat(document.getElementById('coef').value),
        dateCrea: new Date().toISOString().split('T')[0]
    };

    try {
        await fetch(`${API_URL}/devoir/add`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(devoir)
        });

        showAlert('Devoir créé avec succès', 'success');
        closeModal();
        loadDevoirs();
    } catch (error) {
        showAlert('Erreur lors de la création', 'error');
    }
});

async function deleteDevoir(id) {
    if (!confirm('Supprimer ce devoir ? Toutes les notations seront supprimées.')) return;

    try {
        await fetch(`${API_URL}/devoir/deleteDevoirById/${id}`);
        showAlert('Devoir supprimé avec succès', 'success');
        loadDevoirs();
    } catch (error) {
        showAlert('Erreur lors de la suppression', 'error');
    }
}

function showAlert(message, type) {
    const alertDiv = document.getElementById('alert');
    alertDiv.innerHTML = `<div class="alert alert-${type}">${message}</div>`;
    setTimeout(() => alertDiv.innerHTML = '', 3000);
}

loadDevoirs();
