package lawFirm.usecases;


import lombok.Getter;
import lombok.Setter;

import lawFirm.entities.Client;
import lawFirm.persistence.ClientDAO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
        clientDAO.update(client); // or whatever your save logic is
        return "caseDetails?faces-redirect=true&caseId=" + caseId;
    }
}
