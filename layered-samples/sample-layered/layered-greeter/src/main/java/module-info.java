import ru.excelsior.layered.samples.helloworldprovider.HelloWorldProviderImpl;
import ru.excelsior.layered.samples.helloworldprovider.spi.nameprovider.NameProvider;
import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

module layered.greeter {
    requires layered.hello.world;
    requires ru.excelsior.layered;

    exports ru.excelsior.layered.samples.helloworldprovider.spi.nameprovider;

    uses NameProvider;
    provides HelloWorldProvider with HelloWorldProviderImpl;
}