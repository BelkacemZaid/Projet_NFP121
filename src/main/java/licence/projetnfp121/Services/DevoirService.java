package licence.projetnfp121.Services;

import licence.projetnfp121.repository.DevoirRepository;
import org.springframework.stereotype.Service;

@Service
public class DevoirService {
    private final DevoirRepository devoirRepository;

    public DevoirService(DevoirRepository devoirRepository) {
        this.devoirRepository = devoirRepository;
    }

}
