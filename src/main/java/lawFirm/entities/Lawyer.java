package lawFirm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Lawyer.findAll", query = "SELECT l FROM Lawyer l"),
        @NamedQuery(name = "Lawyer.findBySpecialization", query = "SELECT l FROM Lawyer l WHERE l.specialization = :specialization")
})
@Table(name = "LAWYER")
@Getter @Setter
public class Lawyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SPECIALIZATION")
    private String specialization;

    @OneToMany(mappedBy = "lawyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LawCase> cases;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lawyer)) return false;
        Lawyer lawyer = (Lawyer) o;
        return Objects.equals(id, lawyer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
