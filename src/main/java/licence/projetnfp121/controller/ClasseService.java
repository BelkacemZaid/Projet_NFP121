package licence.projetnfp121.controller;


import licence.projetnfp121.data.Classe;
import licence.projetnfp121.data.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClasseService {
    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping("/")
    List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    @GetMapping("/{id}")
    Classe getClasseById(@PathVariable Long id){
        return classeRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    void addClasse(@RequestBody Classe classe){
        classeRepository.save(classe);
    }
}
