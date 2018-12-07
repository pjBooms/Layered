import ru.excelsior.layered.samples.helloworldprovider.HelloWorldProviderImpl;
import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

module hello.world.provider {

    requires hello.world;

    provides HelloWorldProvider with HelloWorldProviderImpl;
}