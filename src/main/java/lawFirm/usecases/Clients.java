package lawFirm.usecases;

import lawFirm.entities.Client;
import lawFirm.persistence.ClientDAO;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class Clients implements Serializable {

    @Inject
    private ClientDAO clientDAO;

    @Getter
    private List<Client> allClients;

    @Getter @Setter
    private Client clientToCreate = new Client();

    @PostConstruct
    public void init() {
        allClients = clientDAO.findAll();
    }

    public void createClient() {
        clientDAO.persist(clientToCreate);
        allClients = clientDAO.findAll();
        clientToCreate = new Client();
    }
}
