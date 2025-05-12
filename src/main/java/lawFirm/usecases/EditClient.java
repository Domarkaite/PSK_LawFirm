package lawFirm.usecases;


import lombok.Getter;
import lombok.Setter;

import lawFirm.entities.Client;
import lawFirm.persistence.ClientDAO;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import java.io.Serializable;

@Named
@ViewScoped
public class EditClient implements Serializable {

    @Inject
    private ClientDAO clientDAO;

    @Getter @Setter
    private Long clientId;

    @Getter @Setter
    private Client client;

    @Getter @Setter
    private Long caseId;

    public void loadClient() {
        if (clientId != null) {
            client = clientDAO.findOne(clientId);
        }
    }

    public String save() {
        try {
            clientDAO.update(client);
            return "caseDetails?faces-redirect=true&caseId=" + caseId;
        } catch (OptimisticLockException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Kitas naudotojas pakeitė šį klientą. Pakartokite veiksmą.", null));
            client = clientDAO.findOne(client.getId());
            return null;
        }
    }
}
