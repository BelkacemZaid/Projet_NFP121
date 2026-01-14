package licence.projetnfp121.Services;

import licence.projetnfp121.data.Devoir;
import licence.projetnfp121.repository.DevoirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevoirService {
    private final DevoirRepository devoirRepository;

    @Autowired
    public DevoirService(DevoirRepository devoirRepository) {
        this.devoirRepository = devoirRepository;
    }

    public void addDevoir(Devoir devoir) {
        devoirRepository.save(devoir);
    }

    public void modifyDevoir(Devoir devoir) {
        devoirRepository.save(devoir);
    }

    public void deleteDevoir(Long id) {
        devoirRepository.deleteById(id);
    }
}
