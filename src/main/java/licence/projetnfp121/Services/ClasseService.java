package licence.projetnfp121.Services;

import licence.projetnfp121.data.Classe;
import licence.projetnfp121.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Service
public class ClasseService {
    private final  ClasseRepository classeRepository;

    @Autowired
    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }


    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    public Classe getClasseById(@PathVariable Long id){
        return classeRepository.findById(id).orElse(null);
    }

    public void addClasse(@RequestBody Classe classe){
        classeRepository.save(classe);
    }

    public void deleteClasse(@PathVariable Long id){
        classeRepository.deleteById(id);
    }
}
