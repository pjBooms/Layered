import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

open module di.hello.world {
    requires ru.excelsior.layered;
    exports ru.excelsior.layered.samples.spi.helloworldprovider;
    uses HelloWorldProvider;
}