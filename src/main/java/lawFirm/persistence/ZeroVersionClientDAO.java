package lawFirm.persistence;

import lawFirm.entities.Client;

import javax.enterprise.inject.Alternative;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Alternative
@ApplicationScoped
public class ZeroVersionClientDAO extends ClientDAO {

    @Inject
    private EntityManager em;

    @Override
    public List<Client> findAll() {
        return em.createQuery("SELECT c FROM Client c WHERE c.version = 0", Client.class)
                .getResultList();
    }
}