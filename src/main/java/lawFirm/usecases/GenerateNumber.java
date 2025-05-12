package lawFirm.usecases;

import lawFirm.interceptors.LoggedInvocation;
import services.IClientNumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@SessionScoped
@Named
public class GenerateNumber implements Serializable {
    @Inject
    IClientNumberGenerator clientNumberGenerator;

    private CompletableFuture<Integer> numberGenerationTask = null;

    @LoggedInvocation
    public String generateNewJerseyNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        numberGenerationTask = CompletableFuture.supplyAsync(() -> clientNumberGenerator.generateNumber());

        return  "/caseDetails.xhtml?faces-redirect=true&caseId=" + requestParameters.get("caseId");
    }

    public boolean isJerseyGenerationRunning() {
        return numberGenerationTask != null && !numberGenerationTask.isDone();
    }

    public String getJerseyGenerationStatus() {
        if (numberGenerationTask == null) {
            return null;
        } else if (isJerseyGenerationRunning()) {
            return "Generation in progress";
        }
        try {
            return "Suggested number: " + numberGenerationTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during generation.";
        }
    }
}