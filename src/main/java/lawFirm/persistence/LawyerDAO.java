package lawFirm.persistence;

import lawFirm.entities.Lawyer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LawyerDAO {

    @Inject
    private EntityManager em;

    @Transactional
    public void persist(Lawyer lawyer) {
        em.persist(lawyer);
    }

    public Lawyer findOne(Long id) {
        return em.find(Lawyer.class, id);
    }

    public List<Lawyer> findAll() {
        return em.createNamedQuery("Lawyer.findAll", Lawyer.class).getResultList();
    }

    @Transactional
    public Lawyer update(Lawyer lawyer) {
        return em.merge(lawyer);
    }
}
