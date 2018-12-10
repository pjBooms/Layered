package ru.excelsior.layered;

import java.io.InputStream;
import java.lang.StackWalker.Option;
import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class ModuleLayers {

    private static volatile boolean initialized;

    private static Map<String, LayerDescription> layerDescriptions = new HashMap<>();
    private static Map<String, ModuleLayer> loadedLayers = new HashMap<>();

    private static void initializeLayers(ClassLoader cl) {
        if (!initialized) {
            Yaml yaml = new Yaml(new Constructor(LayerDescription.class));
            InputStream inputStream = cl.getResourceAsStream("layers.yml");

            for (Object entry: yaml.loadAll(inputStream)) {
                LayerDescription layerDescription = (LayerDescription) entry;
                String name = Objects.requireNonNullElse(layerDescription.getName(), "default");
                layerDescriptions.put(name, layerDescription);
            }
            initialized = true;
        }
    }

    public static ModuleLayer createLayer(String from, String rootModuleName, ModuleLayer parent) {
        ModuleFinder finder = ModuleFinder.of(Paths.get(from));
        Configuration cf = parent.configuration().resolve(finder, ModuleFinder.of(), Set.of(rootModuleName));
        return parent.defineModulesWithOneLoader(cf, ClassLoader.getSystemClassLoader());
    }

    public static ModuleLayer createLayer(String from, String rootModuleName) {
        return createLayer(from, rootModuleName, ModuleLayer.boot());
    }

    private static <T> List<T> loadServices(ModuleLayer moduleLayer, Class<T> service) {
        ModuleLayers.class.getModule().addUses(service);
        return ServiceLoader.load(moduleLayer, service).stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    public static <T> List<T> createLayerAndLoadServices(String from, String rootModuleName, ModuleLayer parent, Class<T> service) {
        ModuleLayer moduleLayer = createLayer(from, rootModuleName, parent);
        return loadServices(moduleLayer, service);
    }

    public static <T> List<T> createLayerAndLoadServices(String from, String rootModuleName, Class<T> service) {
        return createLayerAndLoadServices(from, rootModuleName, ModuleLayer.boot(), service);
    }

    private static ModuleLayer loadLayer(LayerDescription desc) {
        if (loadedLayers.containsKey(desc.getName())) {
            return loadedLayers.get(desc.getName());
        }
        ModuleLayer parent = desc.getParent() != null ?
                loadLayer(layerDescriptions.get(desc.getParent())):
                ModuleLayer.boot();
        ModuleLayer result = createLayer(desc.getPath(), desc.getRootModules(), parent);
        loadedLayers.put(desc.getName(), result);
        return result;
    }

    public static <T> List<T>  loadService(String layerName, Class<T> service) {
        initializeLayers(service.getClassLoader());
        LayerDescription layerDescription = layerDescriptions.get(layerName);
        if (layerDescription == null) {
            return null;
        }

        ModuleLayer layer = loadLayer(layerDescription);

        return loadServices(layer, service);
    }

    public static <T> List<T>  loadService(Class<T> service) {
        return loadService("default", service);
    }

    public static void initialize() {
        StackWalker walker = StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE);
        Class<?> caller = walker.getCallerClass();
        initializeLayers(caller.getClassLoader());
        //TODO: perform classpath scanning, create beans and handle non-static fields
        //TODO: investigate if ready-to-use DI frameworks (Spring, Guice) can be used for
        //      layers service injection instead of our new wheel.
        Field[] fields = caller.getDeclaredFields();
        for (Field f: fields) {
            if (Modifier.isStatic(f.getModifiers()) && f.isAnnotationPresent(LayerService.class)) {
                List<?> services = loadService(f.getType());
                if (services.size() > 0) {
                    f.setAccessible(true);
                    try {
                        f.set(null, services.get(0));
                    } catch (IllegalAccessException ignore) {
                    }
                }
            }
        }
    }
}
