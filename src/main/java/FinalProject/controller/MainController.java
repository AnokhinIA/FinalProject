package FinalProject.controller;

import FinalProject.dto.InsuranceTypeForm;
import FinalProject.model.InsuranceType;
import FinalProject.model.Insurer;
import FinalProject.service.InsuranceTypeService;
import FinalProject.service.InsurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private InsurerService insurerService;
    @Autowired
    private InsuranceTypeService insuranceTypeService;

    @GetMapping("/")
    public String index(Model model) {
        List<Insurer> insurerList = insurerService.showAll();
        model.addAttribute("insurers", insurerList);
        return "index";
    }

    @GetMapping("/types")
    public String type(Model model) {
        List<InsuranceType> types = insuranceTypeService.showAll();
        model.addAttribute("types", types);
        return "types";
    }

}