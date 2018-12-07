import ru.excelsior.layered.samples.helloworldprovider.HelloWorldProviderImpl;
import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

module hello.beautiful.world.provider {

    requires hello.world;

    provides HelloWorldProvider with HelloWorldProviderImpl;
}