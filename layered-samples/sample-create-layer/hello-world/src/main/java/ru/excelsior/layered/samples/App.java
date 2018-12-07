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
        var layer = ModuleLayers.createLayer(args[0], "hello.beautiful.world.provider");
        var services = ServiceLoader.load(layer, HelloWorldProvider.class);
        services.stream()
                .map(ServiceLoader.Provider::get)
                .forEach(helloWorldProvider ->
                        System.out.println(helloWorldProvider.provide())
                );
    }
}
