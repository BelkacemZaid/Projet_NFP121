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

    Etudiant etudiant;


    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }


    public List<Etudiant> getEtudiants(){
        return etudiantRepository.findAll();
    }


    public List<Etudiant> getEtudiantById( Integer id){
        return  etudiantRepository.findAllById( id);
    }


    public  List<Etudiant> getEtudiantsByIdClasse( Classe idclasse){
        return etudiantRepository.findByIdClasse(idclasse);
    }



    public Etudiant addEtudiant( Etudiant etudiant){
       return etudiantRepository.save(etudiant);
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    public Etudiant UpdateEtudiant( Etudiant etudiant){
        return etudiantRepository.save(etudiant);// j'utilise save pcq on peux modifier avec si se que je veux modifier n'existe pas sa le creer si sa existe sa l'ecrase et sa permet de modifier
    }

//    public List<Etudiant> updateEtudiant(String nom, String nouveaunom ,String nouveauPrenom){ //on aura besoin de l'objet etudiant le modifier
//        etudiantRepository.findByNom(nom);
//        etudiant.setNom(nouveaunom);
//        etudiant.setPrenom(nouveauPrenom);
//
//        return etudiantRepository.save(Etudiant);
//
//    }
}
