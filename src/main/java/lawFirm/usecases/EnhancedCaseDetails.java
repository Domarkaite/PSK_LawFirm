package lawFirm.usecases;

import lawFirm.persistence.LawCaseDAO;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

@SessionScoped
@Specializes
public class EnhancedCaseDetails extends CaseDetails {

    @Inject
    private LawCaseDAO lawCaseDAO;

    @Override
    public void assignLawyerWithSpecializationNote() {
        super.assignLawyerWithSpecializationNote();

        if (getLawCase() != null && getLawCase().getLawyer() != null) {
            String extra = "\nThis method gives you an ability to add more info for the case when adding a lawyer using CDI @Specializes.";
            String currentDesc = getLawCase().getDescription() != null ? getLawCase().getDescription() : "";

            if (!currentDesc.contains(extra)) {
                getLawCase().setDescription(currentDesc + extra);
                lawCaseDAO.update(getLawCase());
            }
        }
    }
}
