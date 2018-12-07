package ru.excelsior.layered.samples;

import ru.excelsior.layered.ModuleLayers;
import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

import java.util.ServiceLoader;

/**
 * Hello world!
 */
public class App 
{

    public static void main(String[] args)
    {
        var services = ModuleLayers.createLayerAndLoadServices(args[0], "another.hello.beautiful.world.provider",
                HelloWorldProvider.class);
        services.stream()
                .forEach(helloWorldProvider ->
                        System.out.println(helloWorldProvider.provide())
                );
    }
}
