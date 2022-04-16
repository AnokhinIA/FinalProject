package FinalProject.service;

import FinalProject.dto.InsuranceTypeForm;
import FinalProject.model.InsuranceType;

import FinalProject.repository.InsuranceTypeRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class InsuranceTypeService {

    @Autowired
    private InsuranceTypeRepositories insuranceTypeRepositories;


    public List<InsuranceType> showAll() {
        List<InsuranceType> insuranceTypeList = insuranceTypeRepositories.findAll();
        return insuranceTypeList;
    }

    @Transactional
    public void add(InsuranceTypeForm insuranceTypeForm) {
        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType(insuranceTypeForm.getType());
        insuranceTypeRepositories.save(insuranceType);
    }

    @Transactional
    public void edit(InsuranceTypeForm insuranceTypeForm) {
        InsuranceType insuranceType = (InsuranceType) insuranceTypeRepositories.findById(insuranceTypeForm.getId()).get();
        insuranceType.setType(insuranceTypeForm.getType());
        insuranceTypeRepositories.save(insuranceType);
    }

    @Transactional
    public void delete(Long id) {
        insuranceTypeRepositories.deleteById(id);
    }

    public boolean checkById(long id) {
        boolean enable = insuranceTypeRepositories.existsById(id);
        return enable;
    }

    public Optional<?> findById(Long id) {

        Optional<InsuranceType> insuranceType = insuranceTypeRepositories.findById(id);
        return insuranceType;
    }

}

