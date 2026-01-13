package licence.projetnfp121.controllers;

import licence.projetnfp121.Services.MatiereService;
import licence.projetnfp121.data.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matieres")
public class MatiereController {
    private MatiereService matiereService;

    @Autowired
    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @PostMapping("/add")
    public void addMatiere(@RequestParam Matiere matiere) {
        matiereService.addMatiere(matiere);
    }

    @GetMapping("/deleteMatiereById/{id}")
    public void deleteMatiere(@PathVariable Long id) {
        matiereService.deleteMatiere(id);
    }
}
