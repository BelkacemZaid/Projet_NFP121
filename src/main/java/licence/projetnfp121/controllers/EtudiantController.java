package licence.projetnfp121.controllers;

import org.springframework.transaction.annotation.Transactional;
import licence.projetnfp121.Services.EtudiantService;
import licence.projetnfp121.data.Classe;
import licence.projetnfp121.data.Devoir;
import licence.projetnfp121.data.Etudiant;
import licence.projetnfp121.repository.ClasseRepository;
import licence.projetnfp121.repository.DevoirRepository;
import licence.projetnfp121.repository.EtudiantRepository;
import licence.projetnfp121.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;
    private final EtudiantRepository etudiantRepository;
    private final DevoirRepository devoirRepository;
    private final MatiereRepository matiereRepository;
    private final ClasseRepository classeRepository;

    @Autowired
    public EtudiantController(EtudiantService etudiantService, EtudiantRepository etudiantRepository, DevoirRepository devoirRepository, MatiereRepository matiereRepository, ClasseRepository classeRepository) {
        this.etudiantService = etudiantService;
        this.etudiantRepository=etudiantRepository;
        this.devoirRepository = devoirRepository;
        this.matiereRepository = matiereRepository;
        this.classeRepository = classeRepository;
    }

    @PostMapping("/ajouter")
    public void addEtudiant(@RequestBody Etudiant etudiant) {
        etudiantService.addEtudiant(etudiant);
    }

    @DeleteMapping("/deleteEtudiantById/{id}")
    public void deleteEtudiantById(@PathVariable Long id){
        etudiantService.deleteEtudiant(id);
    }

    @GetMapping("/disponible")
    public List<Etudiant> findAll(){
        return  etudiantService.getEtudiants();
    }

    @PutMapping("/modify")
    public void UpdateEtudiant(@RequestBody Etudiant etudiant){
        etudiantService.UpdateEtudiant(etudiant);
    }

    @GetMapping("/{id}/releve-notes")
    @Transactional(readOnly = true)
    public Map<String, Object> getReleveNotes(@PathVariable Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow();
        List<Devoir> devoirs = devoirRepository.findByEtudiant_Id(id);

        Map<String, Object> releve = new HashMap<>();
        releve.put("nom", etudiant.getNom());
        releve.put("prenom", etudiant.getPrenom());

        Map<String, Map<String, Object>> matieres = new HashMap<>();

        double totalNotesGeneral = 0;
        double totalCoefGeneral = 0;

        for (Devoir d : devoirs) {
            if (d.getIdMat() == null || d.getIdMat().getDenomination() == null) continue;

            String nomMatiere = d.getIdMat().getDenomination();
            Map<String, Object> matiereInfo = matieres.computeIfAbsent(nomMatiere, k -> new HashMap<>());

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> devoirsMatiere = (List<Map<String, Object>>) matiereInfo.getOrDefault("devoirs", new ArrayList<>());

            Map<String, Object> devoirInfo = new HashMap<>();
            devoirInfo.put("description", d.getDescription());
            devoirInfo.put("note", d.getNote());
            devoirInfo.put("coef", d.getCoef());
            devoirInfo.put("categorie", d.getCategorie());

            devoirsMatiere.add(devoirInfo);
            matiereInfo.put("devoirs", devoirsMatiere);

            if (d.getNote() != null && d.getCoef() != null) {
                double note = d.getNote().doubleValue();
                double coef = d.getCoef().doubleValue();

                double totalNotes = (double) matiereInfo.getOrDefault("totalNotes", 0.0);
                double totalCoef = (double) matiereInfo.getOrDefault("totalCoef", 0.0);

                matiereInfo.put("totalNotes", totalNotes + (note * coef));
                matiereInfo.put("totalCoef", totalCoef + coef);

                totalNotesGeneral += note * coef;
                totalCoefGeneral += coef;
            }
        }

        for (Map<String, Object> matiereInfo : matieres.values()) {
            double totalNotes = (double) matiereInfo.getOrDefault("totalNotes", 0.0);
            double totalCoef = (double) matiereInfo.getOrDefault("totalCoef", 0.0);

            if (totalCoef > 0) {
                matiereInfo.put("moyenne", Math.round(totalNotes / totalCoef * 100.0) / 100.0);
            }

            matiereInfo.remove("totalNotes");
            matiereInfo.remove("totalCoef");
        }

        releve.put("matieres", matieres);
        releve.put("moyenneGenerale", totalCoefGeneral > 0 ? Math.round(totalNotesGeneral / totalCoefGeneral * 100.0) / 100.0 : 0.0);

        return releve;
    }
}
