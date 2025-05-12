package lawFirm.persistence;

import lawFirm.entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClientDAO {

    @Inject
    private EntityManager em;

    @Transactional
    public void persist(Client client) {
        em.persist(client);
    }

    public Client findOne(Long id) {
        return em.find(Client.class, id);
    }

    public List<Client> findAll() {
        return em.createNamedQuery("Client.findAll", Client.class).getResultList();
    }

    @Transactional
    public Client update(Client client) {
        return em.merge(client);
    }
}
