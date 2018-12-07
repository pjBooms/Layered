import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

module layered.hello.world {
    requires ru.excelsior.layered;
    exports ru.excelsior.layered.samples.spi.helloworldprovider;
    uses HelloWorldProvider;
}