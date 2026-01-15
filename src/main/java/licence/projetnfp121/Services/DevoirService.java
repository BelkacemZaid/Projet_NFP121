package licence.projetnfp121.Services;

import licence.projetnfp121.data.Devoir;
import licence.projetnfp121.repository.DevoirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DevoirService {
    private final DevoirRepository devoirRepository;

    @Autowired
    public DevoirService(DevoirRepository devoirRepository) {
        this.devoirRepository = devoirRepository;
    }

    public void addDevoir(Devoir devoir) {
        if (devoir.getCoef() == null) {
            devoir.setCoef(BigDecimal.valueOf(1.0));
        }
        devoirRepository.save(devoir);
    }

    public void modifyDevoir(Devoir devoir) {
        if (devoir.getIdClasse()== null) {
            devoir.setIdMat(null);
            devoir.setDescription(null);
            devoir.setCategorie(null);
            devoir.setDateCrea(null);
            devoirRepository.save(devoir);
        }
        devoirRepository.save(devoir);

    }

    public void deleteDevoir(Long id) {
        devoirRepository.deleteById(id);
    }

    public List<Devoir> getAllDevoirs() {
        return devoirRepository.findAll();
    }

}
