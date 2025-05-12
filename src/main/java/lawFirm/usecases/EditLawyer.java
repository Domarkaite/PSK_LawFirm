package lawFirm.usecases;


import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lawFirm.entities.Lawyer;
import lawFirm.persistence.LawyerDAO;

import java.io.Serializable;

@Named
@ViewScoped
public class EditLawyer implements Serializable {

    @Inject
    private LawyerDAO lawyerDAO;

    @Getter @Setter
    private Long lawyerId;

    @Getter @Setter
    private Lawyer lawyer;

    public void loadLawyer() {
        if (lawyerId != null) {
            lawyer = lawyerDAO.findOne(lawyerId);
        }
    }

    public String save() {
        lawyerDAO.update(lawyer);
        return "index?faces-redirect=true";
    }
}
