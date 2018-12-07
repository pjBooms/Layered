import ru.excelsior.layered.samples.helloworldprovider.HelloWorldProviderImpl;
import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

module onemore.hello.world.provider {

    requires onemore.hello.world;

    provides HelloWorldProvider with HelloWorldProviderImpl;
}