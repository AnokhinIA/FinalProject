/* package FinalProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "insurance_list")
public class InsuranceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    //@JoinColumn(name="id")
    private InsuranceType type;
    @ManyToOne
    //@JoinColumn(name = "insurer_id")
    private Insurer insurer;
}

 */