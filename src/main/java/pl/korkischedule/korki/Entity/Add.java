package pl.korkischedule.korki.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(exclude = "owner")
public class Add {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean expose;
    @Enumerated(EnumType.STRING)
    private TypeOfAdd typeOfAdd;
    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;
    @Enumerated(EnumType.STRING)
    private Category category;
}
