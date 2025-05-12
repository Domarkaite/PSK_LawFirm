package lawFirm.usecases;

import components.PageVisits;
import lawFirm.entities.Client;
import lawFirm.entities.LawCase;
import lawFirm.entities.Lawyer;
import lawFirm.persistence.ClientDAO;
import lawFirm.persistence.LawCaseDAO;
import lawFirm.persistence.LawyerDAO;
import lombok.Getter;
import lombok.Setter;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class CaseDetails implements Serializable {

    @Inject private LawCaseDAO lawCaseDAO;
    @Inject private PageVisits pageVisits;
    @Inject private ClientDAO clientDAO;
    @Inject private LawyerDAO lawyerDAO;
    @Inject private Clients clients;

    @Getter private LawCase lawCase;
    @Getter private List<Client> availableClients;
    @Getter private List<Lawyer> availableLawyers;

    @Getter @Setter private Long clientIdToAdd;
    @Getter @Setter private Long lawyerIdToAdd;
    @Getter @Setter private Long caseId;

    public void refreshAvailableClients() {
        this.availableClients = clients.getAllClients();
    }

    public void loadCaseDetails() {
        pageVisits.incrementCasePage();
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

    public void assignLawyerWithSpecializationNote() {
        Lawyer lawyer = lawyerDAO.findOne(lawyerIdToAdd);
        if (lawyer != null) {
            lawCase.setLawyer(lawyer);

            StringBuilder note = new StringBuilder("Assigned to ");
            note.append(lawyer.getName());
            if (lawyer.getSpecialization() != null && !lawyer.getSpecialization().trim().isEmpty()) {
                note.append(" (Specialization: ").append(lawyer.getSpecialization()).append(")");
            }
            note.append(".");

            String currentDesc = lawCase.getDescription() != null ? lawCase.getDescription().trim() : "";
            if (!currentDesc.startsWith(note.toString())) {
                lawCase.setDescription(note + "\n" + currentDesc);
            }

            lawCaseDAO.update(lawCase);
            loadCaseDetails();
        }
    }
}
