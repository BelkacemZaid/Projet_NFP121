package licence.projetnfp121.controllers;

import licence.projetnfp121.Services.DevoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devoir")
public class DevoirController {
    private final DevoirService devoirService;

    @Autowired
    public DevoirController(DevoirService devoirService) {
        this.devoirService = devoirService;
    }


}
