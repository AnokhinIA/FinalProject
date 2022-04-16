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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/action")
public class ActionController {
    @Autowired
    private InsurerService insurerService;
    @Autowired
    private InsuranceTypeService insuranceTypeService;

    @GetMapping("/form")
    public  String form(Model model){
        InsuranceTypeForm insuranceTypeForm = new InsuranceTypeForm();
        model.addAttribute("type",insuranceTypeForm);
        return "add";
    }

    @PostMapping("/save")
    public String type(@Valid InsuranceTypeForm insuranceTypeForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("type",insuranceTypeForm);
            return "add";
        }
        if(insuranceTypeForm.isStatus()){
            insuranceTypeService.edit(insuranceTypeForm);
            List<InsuranceType> types = insuranceTypeService.showAll();
            model.addAttribute("types", types);
        }else {
            try
            {
                insuranceTypeService.add(insuranceTypeForm);
                List<InsuranceType> types = insuranceTypeService.showAll();
                model.addAttribute("types", types);
            }catch (Exception e){
                insuranceTypeForm.setErrorMessage(e.getMessage());
                model.addAttribute("type",insuranceTypeForm);
                return "add";
            }

        }
        return "redirect:/types";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        long idType = Long.parseLong(id);
        if (insuranceTypeService.checkById(idType)) {
            insuranceTypeService.delete(idType);
            return "redirect:/types";
        }
        return "redirect:/types";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model){
        long idType = Long.parseLong(id);

        if (insuranceTypeService.checkById(idType)) {
            InsuranceType insuranceType = (InsuranceType)insuranceTypeService.findById(idType).get();
            InsuranceTypeForm insuranceTypeForm = new InsuranceTypeForm();
            insuranceTypeForm.setType(insuranceType.getType());
            insuranceTypeForm.setId(insuranceType.getId());
            insuranceTypeForm.setStatus(true);
            model.addAttribute("type",insuranceTypeForm);
            return "add";
        }
        return "redirect:/types";
    }

}