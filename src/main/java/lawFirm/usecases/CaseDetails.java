package lawFirm.usecases;

import lawFirm.entities.Client;
import lawFirm.entities.LawCase;
import lawFirm.entities.Lawyer;
import lawFirm.persistence.ClientDAO;
import lawFirm.persistence.LawCaseDAO;

import lawFirm.persistence.LawyerDAO;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CaseDetails implements Serializable {

    @Inject
    private LawCaseDAO lawCaseDAO;

    @Inject
    private ClientDAO clientDAO;

    @Getter
    private LawCase lawCase;

    @Getter
    private List<Client> availableClients;

    @Getter @Setter
    private Long clientIdToAdd;
    @Getter @Setter
    private Long caseId;

    @Inject
    private LawyerDAO lawyerDAO;

    @Getter
    private List<Lawyer> availableLawyers;

    @Getter @Setter
    private Long lawyerIdToAdd;

    @Inject
    private Clients clients;

    public void refreshAvailableClients() {
        this.availableClients = clients.getAllClients();
    }

    public void loadCaseDetails() {
        lawCase = lawCaseDAO.findOne(caseId);
        availableClients = clientDAO.findAll();
        availableLawyers = lawyerDAO.findAll();
    }

    public void addClientToCase() {
        Client client = clientDAO.findOne(clientIdToAdd);
        if (!lawCase.getClients().contains(client)) {
            lawCase.getClients().add(client);
            lawCaseDAO.update(lawCase);
        }
        loadCaseDetails();
    }

    public void assignLawyer() {
        Lawyer lawyer = lawyerDAO.findOne(lawyerIdToAdd);
        lawCase.setLawyer(lawyer);
        lawCaseDAO.update(lawCase);
        loadCaseDetails();
    }

    public String createClientAndReload() {
        clients.createClient();
        refreshAvailableClients();
        return "caseDetails?faces-redirect=true&caseId=" + caseId;
    }
}
