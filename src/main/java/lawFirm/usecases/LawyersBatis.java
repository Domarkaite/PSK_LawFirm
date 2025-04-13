package lawFirm.usecases;

import lawFirm.mybatis.dao.LawyerMapper;
import lombok.Getter;
import lombok.Setter;
import lawFirm.mybatis.dao.LawCaseMapper;
import lawFirm.mybatis.model.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
@Named
@ViewScoped

public class LawyersBatis implements Serializable{

    @Inject
    private LawyerMapper lawyerMapper;

    private List<Lawyer> allLawyers;
    private Lawyer lawyerToCreate = new Lawyer();

    @PostConstruct
    public void init() {
        allLawyers = lawyerMapper.selectAll();
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
        this.allLawyers = lawyerMapper.selectAll(); // <-- THIS refreshes the list
        return "/myBatis/lawyers?faces-redirect=true";
    }
}
