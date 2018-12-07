package ru.excelsior.layered.samples.helloworldprovider;

import ru.excelsior.layered.samples.helloworldprovider.spi.nameprovider.NameProvider;

public class NameProviderImpl implements NameProvider {

    @Override
    public String name() {
        return "World";
    }
}
