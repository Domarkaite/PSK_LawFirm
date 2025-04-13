package lawFirm.usecases;

import lawFirm.entities.*;
import lawFirm.persistence.LawCaseDAO;
import lombok.Getter;
import lombok.Setter;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CasesOverview implements Serializable {

    @Inject
    private LawCaseDAO lawCaseDAO;

    @Getter @Setter
    private LawCase caseToCreate = new LawCase();

    @Getter
    private List<LawCase> allCases;

    @PostConstruct
    public void loadAllCases() {
        allCases = lawCaseDAO.findAll();
    }

    public String createCaseAndReload() {
        lawCaseDAO.persist(caseToCreate);
        allCases.add(caseToCreate);
        caseToCreate = new LawCase();
        return "index?faces-redirect=true";
    }
}