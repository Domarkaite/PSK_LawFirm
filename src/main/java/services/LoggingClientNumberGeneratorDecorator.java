package services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public abstract class LoggingClientNumberGeneratorDecorator implements IClientNumberGenerator {

    @Inject
    @Delegate
    private IClientNumberGenerator delegate;

    @Override
    public Integer generateNumber() {
        long start = System.currentTimeMillis();

        System.out.println("[Decorator] Generating client number...");
        Integer result = delegate.generateNumber();
        long time = System.currentTimeMillis() - start;

        System.out.println("[Decorator] Generated number: " + result + " in " + time + " ms.");
        return result;
    }
}
