package com.taahyt.gyre.storage;

public abstract class AbstractStorage {
    private boolean enabled;

    public abstract StorageType getType();

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String toString() {
        return String.format("AbstractStorage ? ENABLED: %S", isEnabled());
    }
}
