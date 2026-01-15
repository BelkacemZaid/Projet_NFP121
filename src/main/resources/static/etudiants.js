const API_URL = 'http://localhost:8082';

async function loadEtudiants() {
try {
const response = await fetch(`${API_URL}/etudiants/disponible`);
const etudiants = await response.json();

const tbody = document.getElementById('etudiantsBody');
tbody.innerHTML = '';

etudiants.forEach(etudiant => {
const tr = document.createElement('tr');
tr.innerHTML = `
<td>${etudiant.id}</td>
<td>${etudiant.nom}</td>
<td>${etudiant.prenom}</td>
<td>${etudiant.classe ? etudiant.classe.denomination : 'Disponible'}</td>
<td>
    <button class="btn btn-primary" onclick="viewReleve(${etudiant.id})">📊 Relevé</button>
    <button class="btn btn-secondary" onclick="editEtudiant(${etudiant.id})">✏️ Modifier</button>
    <button class="btn btn-danger" onclick="deleteEtudiant(${etudiant.id})">🗑️ Supprimer</button>
</td>
`;
tbody.appendChild(tr);
});
} catch (error) {
showAlert('Erreur lors du chargement des étudiants', 'error');
}
}

async function loadClasses() {
try {
const response = await fetch(`${API_URL}/classe/getAllClasses`);
const classes = await response.json();

const select = document.getElementById('classe');
select.innerHTML = '<option value="">-- Aucune classe --</option>';

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

function openAddModal() {
document.getElementById('modalTitle').textContent = 'Nouvel Étudiant';
document.getElementById('etudiantForm').reset();
document.getElementById('etudiantId').value = '';
loadClasses();
document.getElementById('etudiantModal').style.display = 'block';
}

function closeModal() {
document.getElementById('etudiantModal').style.display = 'none';
}

document.getElementById('etudiantForm').addEventListener('submit', async (e) => {
e.preventDefault();

const id = document.getElementById('etudiantId').value;
const classeId = document.getElementById('classe').value;

const etudiant = {
id: id ? parseInt(id) : null,
nom: document.getElementById('nom').value,
prenom: document.getElementById('prenom').value,
photo: document.getElementById('photo').value || null,
classe: classeId ? { id: parseInt(classeId) } : null
};

try {
const url = id ? `${API_URL}/etudiants/modify` : `${API_URL}/etudiants/ajouter`;
const method = id ? 'PUT' : 'POST';

await fetch(url, {
method: method,
headers: {'Content-Type': 'application/json'},
body: JSON.stringify(etudiant)
});

showAlert('Étudiant enregistré avec succès', 'success');
closeModal();
loadEtudiants();
} catch (error) {
showAlert('Erreur lors de l\'enregistrement', 'error');
}
});

async function editEtudiant(id) {
try {
const response = await fetch(`${API_URL}/etudiants/disponible`);
const etudiants = await response.json();
const etudiant = etudiants.find(e => e.id === id);

document.getElementById('modalTitle').textContent = 'Modifier Étudiant';
document.getElementById('etudiantId').value = etudiant.id;
document.getElementById('nom').value = etudiant.nom;
document.getElementById('prenom').value = etudiant.prenom;
document.getElementById('photo').value = etudiant.photo || '';

await loadClasses();
document.getElementById('classe').value = etudiant.classe ? etudiant.classe.id : '';

document.getElementById('etudiantModal').style.display = 'block';
} catch (error) {
showAlert('Erreur lors du chargement de l\'étudiant', 'error');
}
}

async function deleteEtudiant(id) {
if (!confirm('Supprimer cet étudiant ? Toutes ses notes seront supprimées.')) return;

try {
await fetch(`${API_URL}/etudiants/deleteEtudiantById/${id}`, { method: 'DELETE' });
showAlert('Étudiant supprimé avec succès', 'success');
loadEtudiants();
} catch (error) {
showAlert('Erreur lors de la suppression', 'error');
}
}

async function viewReleve(id) {
try {
const response = await fetch(`${API_URL}/etudiants/${id}/releve-notes`);
const releve = await response.json();

let html = `<div class="bulletin">`;
    html += `<h2>${releve.nom} ${releve.prenom}</h2>`;

    for (const [matiere, info] of Object.entries(releve.matieres)) {
    html += `<div class="etudiant-block">`;
        html += `<h3>${matiere}</h3>`;

        info.devoirs.forEach(devoir => {
        html += `<p>${devoir.description} (${devoir.categorie}): <strong>${devoir.note}/20</strong> (coef: ${devoir.coef})</p>`;
        });

        html += `<p><strong>Moyenne: ${info.moyenne}/20</strong></p>`;
        html += `</div>`;
    }

    html += `<p class="moyenne-generale">Moyenne Générale: ${releve.moyenneGenerale}/20</p>`;
    html += `</div>`;

document.getElementById('releveContent').innerHTML = html;
document.getElementById('releveModal').style.display = 'block';
} catch (error) {
showAlert('Erreur lors du chargement du relevé', 'error');
}
}

function closeReleveModal() {
document.getElementById('releveModal').style.display = 'none';
}

function showAlert(message, type) {
const alertDiv = document.getElementById('alert');
alertDiv.innerHTML = `<div class="alert alert-${type}">${message}</div>`;
setTimeout(() => alertDiv.innerHTML = '', 3000);
}

loadEtudiants();
