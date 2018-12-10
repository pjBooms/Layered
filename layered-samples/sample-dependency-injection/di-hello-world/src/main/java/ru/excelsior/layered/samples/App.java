package ru.excelsior.layered.samples;

import ru.excelsior.layered.LayerService;
import ru.excelsior.layered.ModuleLayers;
import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

import java.util.ServiceLoader;

/**
 * Hello world!
 */
public class App 
{

    @LayerService
    private static HelloWorldProvider helloWorldProvider;

    public static void main(String[] args)
    {
        ModuleLayers.initialize();
        System.out.println(helloWorldProvider.provide());
    }
}
