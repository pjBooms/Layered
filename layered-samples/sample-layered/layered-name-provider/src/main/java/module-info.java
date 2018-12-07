import ru.excelsior.layered.samples.helloworldprovider.NameProviderImpl;
import ru.excelsior.layered.samples.helloworldprovider.spi.nameprovider.NameProvider;

module layered.name.provider {

    requires layered.greeter;

    provides NameProvider with NameProviderImpl;
}