package pl.korkischedule.korki.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"creator", "receiver"})
@EqualsAndHashCode(exclude = {"creator", "receiver"})
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "creator")
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private UserEntity receiver;
}
