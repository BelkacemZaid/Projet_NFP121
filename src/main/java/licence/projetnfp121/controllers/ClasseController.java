package licence.projetnfp121.controllers;

import licence.projetnfp121.Services.ClasseService;
import licence.projetnfp121.data.Classe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classe")
public class ClasseController {

    private final ClasseService classeService;

    @Autowired
    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping("/getAllClasses")
    public List<Classe> findAll() {
        return classeService.getAllClasses();
    }

    @GetMapping("/getClassesByid/{id}")
    public Classe getClasseById(@PathVariable("id") Long id){
        return classeService.getClasseById(id);
    }

    @PostMapping("/add")
    public void addClasse(@RequestBody Classe classe){

        classeService.addClasse(classe);

    }

    @GetMapping("/deleteClassesByid/{id}")
    public void deleteClasseById(@PathVariable("id") Long id){
        classeService.deleteClasse(id);
    }
}
