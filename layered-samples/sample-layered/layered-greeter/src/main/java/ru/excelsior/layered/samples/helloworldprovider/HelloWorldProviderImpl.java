package ru.excelsior.layered.samples.helloworldprovider;

import ru.excelsior.layered.ModuleLayers;
import ru.excelsior.layered.samples.helloworldprovider.spi.nameprovider.NameProvider;
import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

import java.util.List;

public class HelloWorldProviderImpl implements HelloWorldProvider {

    private String greeting(String name) {
        return "Hello " + name  + "!";
    }

    @Override
    public String provide() {
        List<NameProvider> providers = ModuleLayers.loadService("world", NameProvider.class);
        return providers.stream()
                .map(a -> greeting(a.name()))
                .reduce((a, b) -> a + "\n" + b).orElse("");
    }
}
