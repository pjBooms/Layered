package ru.excelsior.layered;

public class LayerDescription {

    private String name;
    private String path;
    private String rootModules;
    private String parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRootModules() {
        return rootModules;
    }

    public void setRootModules(String rootModules) {
        this.rootModules = rootModules;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
