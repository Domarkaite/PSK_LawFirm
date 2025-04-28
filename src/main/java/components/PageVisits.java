package components;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.concurrent.atomic.AtomicInteger;

@Named
@ApplicationScoped
public class PageVisits implements Serializable {

    private final AtomicInteger casePageVisits = new AtomicInteger(0);

    public void incrementCasePage() {
        casePageVisits.incrementAndGet();
    }

    public int getCasePageCount() {
        return casePageVisits.get();
    }
}