package lawFirm.persistence;

import lawFirm.entities.LawCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LawCaseDAO {

    @Inject
    private EntityManager em;

    @Transactional
    public void persist(LawCase lawCase) {
        em.persist(lawCase);
    }

    public LawCase findOne(Long id) {
        return em.find(LawCase.class, id);
    }

    public List<LawCase> findAll() {
        return em.createNamedQuery("LawCase.findAll", LawCase.class).getResultList();
    }

    public LawCase findByTitle(String title) {
        return em.createNamedQuery("LawCase.findByTitle", LawCase.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Transactional
    public LawCase update(LawCase lawCase) {
        return em.merge(lawCase);
    }
}
