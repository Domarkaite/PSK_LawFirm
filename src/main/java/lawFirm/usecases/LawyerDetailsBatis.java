package lawFirm.usecases;

import components.PageVisits;
import lawFirm.mybatis.dao.LawyerMapper;
import lawFirm.mybatis.dao.LawCaseMapper;
import lawFirm.mybatis.model.*;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class LawyerDetailsBatis implements Serializable {

    private Long lawyerId;

    private Lawyer lawyer;

    private List<LawCase> lawCases;

    @Inject
    private PageVisits pageVisits;

    @Inject
    private LawyerMapper lawyerMapper;

    @Inject
    private LawCaseMapper lawCaseMapper;

    public void load() {
        if (lawyerId != null) {
            lawyer = lawyerMapper.selectByPrimaryKey(lawyerId);
            lawCases = lawCaseMapper.selectByLawyerId(lawyerId);

            pageVisits.incrementCasePage();
        }
    }

    public Long getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(Long lawyerId) {
        this.lawyerId = lawyerId;
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public List<LawCase> getLawCases() {
        return lawCases;
    }
}