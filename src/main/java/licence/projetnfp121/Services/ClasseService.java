package licence.projetnfp121.Services;

import licence.projetnfp121.data.Classe;
import licence.projetnfp121.data.Etudiant;
import licence.projetnfp121.repository.ClasseRepository;
import licence.projetnfp121.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Service
public class ClasseService {
    private final  ClasseRepository classeRepository;
    private final EtudiantRepository etudiantRepository;

    @Autowired
    public ClasseService(ClasseRepository classeRepository, EtudiantRepository etudiantRepository ) {
        this.classeRepository = classeRepository;
        this.etudiantRepository = etudiantRepository;
    }


    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    public Classe getClasseById( Long id){
        return classeRepository.findById(id).orElse(null);
    }

    public Classe addClasse(Classe classe){ // une methode pour ajouter une classe simple sans etudiant a l'interieur
        return classeRepository.save(classe);
    }
//    public Classe addClasse(Classe classe, List<Integer> etudiantIds) { // ici le controller m'envoie la classe et les id des etudiant a mettre dans la classe
//
//        Classe nouvelleClasse = classeRepository.save(classe); //ici je creer un objet nouvelleClasse qui va prendre la classe envoyer par le controller
//
//        for (Integer id : etudiantIds ){ // pour chaque id dans la liste etudiantIds
//            Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id); // on va aller verifier si il ya un etudiant avec cet id
//            if (optionalEtudiant.isPresent()) { // si present
//                Etudiant etudiant = optionalEtudiant.get();// l'avoir
//                etudiant.setIdClasse(nouvelleClasse);// on lui ajoute la classe dans la quel il est
//                etudiantRepository.save(etudiant); // et on ajoute l'etudiant
//            }else {
//                throw new RuntimeException("Etudiant non trouvé avec id: " + id); }
//        }
//        return nouvelleClasse;

   // }

    public Classe updateClasse(Classe classe){
        return classeRepository.save(classe);
    }

    public void deleteClasse( Long id){
        classeRepository.deleteById(id);
    }

    public void addEtudiant(Long idClasse, List<Integer> etudiantIds){

        Classe classe = classeRepository.findById(idClasse)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));

        for (Integer id : etudiantIds) {
            Optional<Etudiant> optional = etudiantRepository.findById(id);
            if (optional.isPresent()) {
                Etudiant etudiant = optional.get();
                etudiant.setIdClasse(classe);
                etudiantRepository.save(etudiant);
            }
        }

        // a documenter tout sa

    }





}
