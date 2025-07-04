package Home_Utility_Tracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type", unique = true, nullable = false)
    private String name;
    @Column(name = "utility_description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "utilityType", cascade = CascadeType.ALL)
    private List<UtilityBill> utilityBills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UtilityBill> getUtilityBills() {
        return utilityBills;
    }

    public void setUtilityBills(List<UtilityBill> utilityBills) {
        this.utilityBills = utilityBills;
    }
}
