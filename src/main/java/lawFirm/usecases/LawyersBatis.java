package lawFirm.usecases;

import lawFirm.mybatis.dao.LawyerMapper;
import lombok.Getter;
import lombok.Setter;
import lawFirm.mybatis.model.Lawyer;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class LawyersBatis implements Serializable {

    @Inject
    private LawyerMapper lawyerMapper;

    private List<Lawyer> allLawyers;
    private Lawyer lawyerToCreate = new Lawyer();

    @PostConstruct
    public void init() {
        allLawyers = lawyerMapper.selectLawyersWithCases();
        //allLawyers = lawyerMapper.selectAll();
        //allLawyers.forEach(lawyer -> lawyer.setCases(lawyerMapper.selectLawyerWithCasesById(lawyer.getId()).getCases()));
    }

    public List<Lawyer> getAllLawyers() {
        return allLawyers;
    }

    public Lawyer getLawyerToCreate() {
        return lawyerToCreate;
    }

    public void setLawyerToCreate(Lawyer lawyerToCreate) {
        this.lawyerToCreate = lawyerToCreate;
    }

    @Transactional
    public String createLawyer() {
        lawyerMapper.insert(lawyerToCreate);
        this.lawyerToCreate = new Lawyer();
        this.allLawyers = lawyerMapper.selectAll(); // Refresh list after creation
        return "/myBatis/lawyers?faces-redirect=true";
    }

    // NEW: Optional method to load a lawyer with cases if needed
    public Lawyer loadLawyerWithCases(Long lawyerId) {
        return lawyerMapper.selectLawyerWithCasesById(lawyerId);
    }
}
