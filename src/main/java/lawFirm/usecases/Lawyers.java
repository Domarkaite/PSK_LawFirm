package lawFirm.usecases;

import lawFirm.entities.Lawyer;
import lawFirm.persistence.LawyerDAO;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class Lawyers implements Serializable {

    @Inject
    private LawyerDAO lawyerDAO;

    @Getter @Setter
    private Lawyer lawyerToCreate = new Lawyer();

    @Getter
    private List<Lawyer> allLawyers;

    @PostConstruct
    public void init() {
        allLawyers = lawyerDAO.findAll();
        if (allLawyers == null) {
            allLawyers = new ArrayList<>();
        }
    }

    public void createLawyer() {
        lawyerDAO.persist(lawyerToCreate);
        allLawyers = lawyerDAO.findAll();
        lawyerToCreate = new Lawyer();
    }
}
