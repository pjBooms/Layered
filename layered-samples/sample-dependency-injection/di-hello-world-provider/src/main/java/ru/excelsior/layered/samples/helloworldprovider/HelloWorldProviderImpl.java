package ru.excelsior.layered.samples.helloworldprovider;

import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

public class HelloWorldProviderImpl implements HelloWorldProvider {

    @Override
    public String provide() {
        return "Hello World!";
    }
}
