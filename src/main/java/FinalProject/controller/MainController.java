package FinalProject.controller;

import FinalProject.model.InsuranceType;
import FinalProject.model.Insurer;
import FinalProject.service.FillInsurerDb;
import FinalProject.service.InsuranceTypeService;
import FinalProject.service.InsurerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MainController {
    @Autowired
    private InsurerService insurerService;
    @Autowired
    private InsuranceTypeService insuranceTypeService;

    private final RestTemplate restTemplate;

    @GetMapping("/")
    public String index(Model model) throws IOException {
        FillInsurerDb fillInsurerDb = new FillInsurerDb();
        ArrayList<String> result = null;
        result = fillInsurerDb.parse();
        List<Insurer> insurerList = insurerService.showAll();
        model.addAttribute("insurers", insurerList);
        model.addAttribute("excel", result);
        return "index";
    }

    @GetMapping("/types")
    public String type(Model model) {
        List<InsuranceType> types = insuranceTypeService.showAll();
        model.addAttribute("types", types);
        return "types";
    }

    /* Задание №39
    Разработать аспект, который залогирует название и
    параметры вызываемого метода прокси-объекта
    через REST-эндпойнт отдельно стоящего сервиса.*/

    @GetMapping("/aspect")
    public String aspect(Model model){
        String message = restTemplate.getForObject(UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("/getinsurers")
                .queryParam("initiator", "Запрос из сервиса FinalProject")
               .build(Map.of()), String.class);
            model.addAttribute("aspect", message);
        return "aspect";
    }
}