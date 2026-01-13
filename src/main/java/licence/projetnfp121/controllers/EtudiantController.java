package licence.projetnfp121.controllers;

import licence.projetnfp121.Services.EtudiantService;
import licence.projetnfp121.data.Classe;
import licence.projetnfp121.data.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    private final EtudiantService etudiantService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }


    @PostMapping("/add")
    public void addEtudiant(@RequestBody Etudiant etudiant) {
        etudiantService.addEtudiant(etudiant);
    }

    @PostMapping("/deleteEtudiantById/{id}")
    public void deleteEtudiantById(@PathVariable Long id){
        etudiantService.deleteEtudiant(id);
    }
}
