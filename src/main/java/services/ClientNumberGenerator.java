package services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class ClientNumberGenerator implements IClientNumberGenerator, Serializable {

    public Integer generateNumber() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        Integer generatedNumber = new Random().nextInt(100000);
        return generatedNumber;
    }
}
