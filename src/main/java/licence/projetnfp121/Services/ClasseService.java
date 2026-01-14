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

    public Classe addClasse( Classe classe){
        return classeRepository.save(classe);//cet methode marche
        //ici je doit faire la logique metier de ajouter des etudiant si il n'ont pas de classe
    }

    public Classe updateClasse(Classe classe){
        return classeRepository.save(classe);
        //ici je doit faire la logique metier d'ajouter des etudiant disponible
    }

    public void deleteClasse(@PathVariable Long id){
        classeRepository.deleteById(id); //cet methode marche

        //ici la logique mettier lorsque classe supprimer idclasse devient null donc disponible
    }

    //creer une methode pour afficher le bultin pour chaque etudiant, sa moyenne par matiere et moyenne general
}
