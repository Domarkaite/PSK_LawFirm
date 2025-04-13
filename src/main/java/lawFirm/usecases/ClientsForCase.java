package lawFirm.usecases;

import lombok.Getter;
import lombok.Setter;
import lawFirm.entities.Client;
import lawFirm.entities.LawCase;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ClientsForCase {

    @PersistenceContext
    private EntityManager em;

    @Getter
    private LawCase lawCase;

    @Getter @Setter
    private Client clientToCreate = new Client();

    @PostConstruct
    public void init() {
        String caseIdParam = javax.faces.context.FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap().get("caseId");

        if (caseIdParam != null) {
            Long caseId = Long.parseLong(caseIdParam);

            lawCase = em.createQuery(
                            "SELECT c FROM LawCase c LEFT JOIN FETCH c.clients WHERE c.id = :id", LawCase.class)
                    .setParameter("id", caseId)
                    .getSingleResult();
        }
    }

    @Transactional
    public String createClient() {
        if (lawCase == null) {
            throw new IllegalStateException("LawCase is not initialized. Make sure 'caseId' is provided and valid.");
        }

        if (clientToCreate == null) {
            throw new IllegalStateException("Client to create is null.");
        }

        if (clientToCreate.getCases() == null) {
            clientToCreate.setCases(new ArrayList<>());
        }

        if (lawCase.getClients() == null) {
            lawCase.setClients(new ArrayList<>());
        }

        // Avoid duplicate additions
        if (!clientToCreate.getCases().contains(lawCase)) {
            clientToCreate.getCases().add(lawCase);
        }

        if (!lawCase.getClients().contains(clientToCreate)) {
            lawCase.getClients().add(clientToCreate);
        }

        em.persist(clientToCreate);
        em.merge(lawCase);

        return null;
    }

}
