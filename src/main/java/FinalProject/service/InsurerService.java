package FinalProject.service;

import FinalProject.model.Insurer;
import FinalProject.repository.InsurerRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;


@Service
public class InsurerService {

    @Autowired
    private InsurerRepositories insurerRepositories;

    public String addInsurer(String name, String address) {
        //Созданим нового Страховщика
        //TODO Настроить занесение всех параметров согласно данным ЦБ РФ
        Insurer insurer = new Insurer();
        //insurer.setFederalDistrict("Центральный федеральный округ");
        insurer.setName(name);
        insurer.setAddress(address);
        //insurer.setLineOfBusiness("Добровольное личное страхование, за исключением добровольного страхования жизни");
        //insurer.setPersonalTaxReferenceNumber((int) 7704311252.00);
        //insurer.setMotor(true);
        insurerRepositories.save(insurer);
        return (insurer.getName() + " Сохранение прошло успешно");
    }


    public void deleteInsurer(long iD) {
        insurerRepositories.deleteAllById(Collections.singleton(iD));
    }

    public List<Insurer> showAll() {
        List<Insurer> insurersList = insurerRepositories.findAll();
        return insurersList;
    }
}

