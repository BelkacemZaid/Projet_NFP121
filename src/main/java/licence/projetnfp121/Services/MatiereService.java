package licence.projetnfp121.Services;

import licence.projetnfp121.data.Matiere;
import licence.projetnfp121.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    public void addMatiere(Matiere matiere) {
        matiereRepository.save(matiere);
    }

    public void deleteMatiere(Long id) {
        matiereRepository.deleteById(id);
    }
}
