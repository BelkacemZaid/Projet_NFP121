package licence.projetnfp121.Services;

import licence.projetnfp121.data.Classe;
import licence.projetnfp121.data.Etudiant;
import licence.projetnfp121.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;


    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @GetMapping("/")
    public List<Etudiant> getEtudiants(){
        return etudiantRepository.findAll();
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable Long id){
        return etudiantRepository.findById(id).get();
    }

    @GetMapping("/classe/{idclasse}")
    List<Etudiant> getEtudiantsByIdClasse(@PathVariable Classe idclasse){
        return etudiantRepository.findByIdClasse(idclasse);
    }


    @PostMapping("/add")
    public void addEtudiant(@RequestBody Etudiant etudiant){
        etudiantRepository.save(etudiant);
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }
}
