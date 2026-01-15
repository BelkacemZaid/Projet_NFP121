package licence.projetnfp121.controllers;

import licence.projetnfp121.Services.DevoirService;
import licence.projetnfp121.data.Devoir;
import licence.projetnfp121.data.Matiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// a faire
@RestController
@RequestMapping("/devoir")
public class DevoirController {
    private final DevoirService devoirService;

    @Autowired
    public DevoirController(DevoirService devoirService) {
        this.devoirService = devoirService;
    }


    @PostMapping("/add")
    public void addDevoir(@RequestBody Devoir devoir) {

        if (devoir.getCoef() != null) {
            devoirService.addDevoir(devoir);
        }
    }

    @PutMapping("/modify")
    public void modifyDevoir(@RequestBody Devoir devoir) {
        devoirService.modifyDevoir(devoir);
    }

    @GetMapping("/deleteDevoirById/{id}")
    public void deleteMatiere(@PathVariable Long id) {
        devoirService.deleteDevoir(id);
    }

    @GetMapping("/getAllDevoirs")  // PAS getAllDevoirs1
    public List<Devoir> getAllDevoirs() {
        return devoirService.getAllDevoirs();
    }


}
