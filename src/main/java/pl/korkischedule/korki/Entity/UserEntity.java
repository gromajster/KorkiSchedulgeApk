package pl.korkischedule.korki.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class UserEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String telephone;
    private String description;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Add> add = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<UserRole> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver")
    private Set<Opinion> opinions = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE)
    private List<YoutubeVideo> videos = new ArrayList<>();
}
