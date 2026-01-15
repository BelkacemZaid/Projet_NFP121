const API_URL = 'http://localhost:8082';

async function loadClasses() {
try {
const response = await fetch(`${API_URL}/classe/getAllClasses`);
const classes = await response.json();

const tbody = document.getElementById('classesBody');
tbody.innerHTML = '';

classes.forEach(classe => {
const tr = document.createElement('tr');
tr.innerHTML = `
<td>${classe.id}</td>
<td>${classe.denomination}</td>
<td>
    <button class="btn btn-primary" onclick="viewBulletin(${classe.id})">📊 Bulletin</button>
    <button class="btn btn-secondary" onclick="editClasse(${classe.id})">✏️ Modifier</button>
    <button class="btn btn-danger" onclick="deleteClasse(${classe.id})">🗑️ Supprimer</button>
</td>
`;
tbody.appendChild(tr);
});
} catch (error) {
showAlert('Erreur lors du chargement des classes', 'error');
}
}

function openAddModal() {
document.getElementById('modalTitle').textContent = 'Nouvelle Classe';
document.getElementById('classeForm').reset();
document.getElementById('classeId').value = '';
document.getElementById('classeModal').style.display = 'block';
}

function closeModal() {
document.getElementById('classeModal').style.display = 'none';
}

document.getElementById('classeForm').addEventListener('submit', async (e) => {
e.preventDefault();

const id = document.getElementById('classeId').value;
const classe = {
id: id ? parseInt(id) : null,
denomination: document.getElementById('denomination').value
};

try {
const url = id ? `${API_URL}/classe/update` : `${API_URL}/classe/add`;

await fetch(url, {
method: 'POST',
headers: {'Content-Type': 'application/json'},
body: JSON.stringify(classe)
});

showAlert('Classe enregistrée avec succès', 'success');
closeModal();
loadClasses();
} catch (error) {
showAlert('Erreur lors de l\'enregistrement', 'error');
}
});

async function editClasse(id) {
try {
const response = await fetch(`${API_URL}/classe/ClassesByid/${id}`);
const classe = await response.json();

document.getElementById('modalTitle').textContent = 'Modifier Classe';
document.getElementById('classeId').value = classe.id;
document.getElementById('denomination').value = classe.denomination;
document.getElementById('classeModal').style.display = 'block';
} catch (error) {
showAlert('Erreur lors du chargement de la classe', 'error');
}
}

async function deleteClasse(id) {
if (!confirm('Supprimer cette classe ? Les étudiants deviendront disponibles.')) return;

try {
await fetch(`${API_URL}/classe/deleteClassesByid/${id}`);
showAlert('Classe supprimée avec succès', 'success');
loadClasses();
} catch (error) {
showAlert('Erreur lors de la suppression', 'error');
}
}

async function viewBulletin(id) {
try {
const response = await fetch(`${API_URL}/classe/${id}/bulletin`);
const bulletin = await response.json();

let html = `<div class="bulletin">`;
    html += `<h2>Classe: ${bulletin.classe}</h2>`;

    bulletin.etudiants.forEach(etudiant => {
    html += `<div class="etudiant-block">`;
        html += `<h3>${etudiant.nom} ${etudiant.prenom}</h3>`;

        for (const [matiere, moyenne] of Object.entries(etudiant.moyennesParMatiere)) {
        html += `<p>${matiere}: <strong>${moyenne.toFixed(2)}/20</strong></p>`;
        }

        html += `<p class="moyenne-generale">Moyenne Générale: ${etudiant.moyenneGenerale.toFixed(2)}/20</p>`;
        html += `</div>`;
    });

    html += `</div>`;

document.getElementById('bulletinContent').innerHTML = html;
document.getElementById('bulletinModal').style.display = 'block';
} catch (error) {
showAlert('Erreur lors du chargement du bulletin', 'error');
}
}

function closeBulletinModal() {
document.getElementById('bulletinModal').style.display = 'none';
}

function showAlert(message, type) {
const alertDiv = document.getElementById('alert');
alertDiv.innerHTML = `<div class="alert alert-${type}">${message}</div>`;
setTimeout(() => alertDiv.innerHTML = '', 3000);
}

loadClasses();
