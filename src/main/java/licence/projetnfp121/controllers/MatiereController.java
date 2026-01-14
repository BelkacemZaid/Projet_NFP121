package licence.projetnfp121.controllers;

import licence.projetnfp121.Services.MatiereService;
import licence.projetnfp121.data.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/matieres")
public class MatiereController {
    @Autowired
    private MatiereService matiereService;

    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @PostMapping("/add")
    public void addMatiere(@RequestBody Matiere matiere) {
        matiereService.addMatiere(matiere);
    }

<<<<<<< Updated upstream
    @PutMapping("/modify")
    public void modifyMatiere(@RequestBody Matiere matiere) {
        matiereService.modifyMatiere(matiere);
    }
=======

>>>>>>> Stashed changes

    @GetMapping("/deleteMatiereById/{id}")
    public void deleteMatiere(@PathVariable Long id) {
        matiereService.deleteMatiere(id);
    }
}
