package lawFirm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "LawCase.findAll", query = "SELECT lc FROM LawCase lc"),
        @NamedQuery(name = "LawCase.findByTitle", query = "SELECT lc FROM LawCase lc WHERE lc.title = :title")
})
@Table(name = "LAW_CASE")
@Getter @Setter
public class LawCase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "LAWYER_ID")
    private Lawyer lawyer;

    @ManyToMany
    @JoinTable(
            name = "CASE_CLIENT",
            joinColumns = @JoinColumn(name = "CASE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLIENT_ID")
    )
    private List<Client> clients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LawCase)) return false;
        LawCase lawCase = (LawCase) o;
        return Objects.equals(id, lawCase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
