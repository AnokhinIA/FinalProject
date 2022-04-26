package FinalProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "insurers")
public class Insurer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private FederalDistrict federalDistrict;
    private String address;
    private String name;
    private String mean_of_communication;
    private long personalTaxReferenceNumber;
    @OneToMany(mappedBy = "insurer")
    List<InsuranceList> insuranceLists = new ArrayList<>();
    @OneToMany(mappedBy = "insurer")
    List<BusinessLineList> businessLineLists = new ArrayList<>();
}

/*
По поводу вашего TODO комментария в файле schema.xml могу порекомендовать вынести множество видов страхования в отдельную таблицу
и сделать связь с таблицей старховщиков как one-to-many.
Например добавить таблицу insurance_type с полями id, insurer_id, type
Соответственно в коде через hibernate можно будет реализовать связь таким образом, чтобы автоматически подтягивались все типы страхования,
 которые есть у страховщика.
Для этого нужно в класс Insurers добавить такое поле с аннотациями
@OneToMany(mappedBy="insurer")
List<InsuracneType> insuranceTypes;

Ну и создать еще отдельный entity класс InsuracneType, где тоже нужно будет отразить связь с таблицей Insurers:

@ManyToOne
@JoinColumn(name = "insurer_id", insertable = false, updatable = false)
private Insurers insurer;

Ну и сам класс Insurers лучше переименовать просто в Insurer, не смотря на то, что таблица называется insurers.
То есть таблица хранит коллекцию страховщиков и такое название отражает корректно его суть, а класс представляет собой одну запись из этой таблице,
то есть одного страховщика, поэтому название класса во множественном числе не совсем правильное
 */
