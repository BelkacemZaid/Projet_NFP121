package licence.projetnfp121.controller;

import licence.projetnfp121.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping("/")
    public List<Etudiant> getEtudiants(){
        return etudiantRepository.findAll();
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable Long id){
        return etudiantRepository.findById(id).orElse(null);
    }

    @GetMapping("/classe/{classe_id}")
    public  List<Etudiant> getEtudiantsByClasseId(@PathVariable Long classeId){
        return etudiantRepository.getEtudiantsByClasse_Id(classeId);
    }

    @GetMapping("/disponible")
    public List<Etudiant> getEtudiantsDisponible(){
        return etudiantRepository.getEtudiantsByClasse(null);
    }

    @PostMapping("/add")
    void addEtudiant(@RequestBody Etudiant etudiant){
        etudiantRepository.save(etudiant);
    }

}
