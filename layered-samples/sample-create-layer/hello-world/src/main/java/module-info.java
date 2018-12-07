import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

module hello.world {
    requires ru.excelsior.layered;
    exports ru.excelsior.layered.samples.spi.helloworldprovider;
    uses HelloWorldProvider;
}