package lawFirm.usecases;

import lombok.Getter;
import lombok.Setter;
import lawFirm.mybatis.dao.LawCaseMapper;
import lawFirm.mybatis.model.LawCase;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named("casesBatis")
@ViewScoped
public class CasesBatis implements Serializable{

    @Inject
    private LawCaseMapper lawCaseMapper;

    @Getter
    private List<LawCase> allCases;

    @Getter @Setter
    private LawCase caseToCreate = new LawCase();

    @PostConstruct
    public void init() {
        this.loadAllCases();
    }

    private void loadAllCases() {
        this.allCases = lawCaseMapper.selectAll();
    }

    @Transactional
    public String createCase() {
        lawCaseMapper.insert(caseToCreate);
        this.caseToCreate = new LawCase();
        this.loadAllCases();
        return "/myBatis/cases?faces-redirect=true";
    }
}
