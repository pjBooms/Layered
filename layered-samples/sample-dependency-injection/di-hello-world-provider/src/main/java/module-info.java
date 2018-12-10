import ru.excelsior.layered.samples.helloworldprovider.HelloWorldProviderImpl;
import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

module di.hello.world.provider {

    requires di.hello.world;

    provides HelloWorldProvider with HelloWorldProviderImpl;
}