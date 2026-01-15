package licence.projetnfp121.controllers;

import licence.projetnfp121.Services.ClasseService;
import licence.projetnfp121.Services.EtudiantService;
import licence.projetnfp121.data.Classe;
import licence.projetnfp121.data.Devoir;
import licence.projetnfp121.data.Etudiant;
import licence.projetnfp121.repository.DevoirRepository;
import licence.projetnfp121.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/classe")
public class ClasseController {

    private final ClasseService classeService;
    private EtudiantRepository etudiantRepository;
    private EtudiantService etudiantService;
    private DevoirRepository devoirRepository;

    @Autowired
    public ClasseController(ClasseService classeService, EtudiantRepository etudiantRepository,
                            EtudiantService etudiantService, DevoirRepository devoirRepository) {
        this.classeService = classeService;
        this.etudiantRepository = etudiantRepository;
        this.etudiantService = etudiantService;
        this.devoirRepository = devoirRepository;
    }

    @GetMapping("/getAllClasses")
    public List<Classe> findAll() {
        return classeService.getAllClasses();
    }

    @GetMapping("/ClassesByid/{id}")
    public Classe getClasseById(@PathVariable("id") Long id){
        return classeService.getClasseById(id);
    }

    @PostMapping("/add")
    public void addClasse(@RequestBody Classe classe){
        classeService.addClasse(classe);
    }

    @PostMapping("/update")
    public void updateClasse(@RequestBody Classe classe){
        classeService.updateClasse(classe);
    }

    @GetMapping("/deleteClassesByid/{id}")
    public void deleteClasseById(@PathVariable("id") Long id){
        classeService.deleteClasse(id);
    }

    @PutMapping("/{idClasse}/etudiants")
    public void addEtudiant(@PathVariable Long idClasse, @RequestBody List<Integer> etudiantsIds){
        classeService.addEtudiant(idClasse,etudiantsIds);
    }

    @GetMapping("/{id}/bulletin")
    public Map<String, Object> getBulletin(@PathVariable Integer id) {
        Classe classe = classeService.getClasseById(id.longValue());
        List<Etudiant> etudiants = etudiantRepository.findByIdClasse_Id(id);  // CHANGEMENT ICI

        Map<String, Object> bulletin = new HashMap<>();
        bulletin.put("classe", classe.getDenomination());
        List<Map<String, Object>> listEtudiants = new ArrayList<>();

        for (Etudiant etudiant : etudiants) {
            List<Devoir> devoirs = devoirRepository.findByEtudiant_Id(etudiant.getId());

            Map<String, Object> etudiantBulletin = new HashMap<>();
            etudiantBulletin.put("nom", etudiant.getNom());
            etudiantBulletin.put("prenom", etudiant.getPrenom());

            Map<String, Double> moyennes = new HashMap<>();
            Map<String, List<Devoir>> parMatiere = new HashMap<>();

            for (Devoir d : devoirs) {
                if (d.getIdMat() != null && d.getIdMat().getDenomination() != null) {
                    String matiere = d.getIdMat().getDenomination();
                    parMatiere.computeIfAbsent(matiere, k -> new ArrayList<>()).add(d);
                }
            }

            double somme = 0;
            for (Map.Entry<String, List<Devoir>> entry : parMatiere.entrySet()) {
                double total = 0;
                double coef = 0;

                for (Devoir d : entry.getValue()) {
                    BigDecimal note = d.getNote();
                    BigDecimal coefBd = d.getCoef();

                    if (note != null && coefBd != null) {
                        total += note.multiply(coefBd).doubleValue();
                        coef += coefBd.doubleValue();
                    }
                }

                double moy = (coef == 0) ? 0 : total / coef;
                moyennes.put(entry.getKey(), moy);
                somme += moy;
            }

            etudiantBulletin.put("moyennesParMatiere", moyennes);
            etudiantBulletin.put("moyenneGenerale", moyennes.isEmpty() ? 0.0 : somme / moyennes.size());

            listEtudiants.add(etudiantBulletin);
        }

        bulletin.put("etudiants", listEtudiants);

        return bulletin;
    }
}
