import ru.excelsior.layered.samples.spi.helloworldprovider.HelloWorldProvider;

module another.hello.world {
    requires ru.excelsior.layered;
    exports ru.excelsior.layered.samples.spi.helloworldprovider;
    uses HelloWorldProvider;
}