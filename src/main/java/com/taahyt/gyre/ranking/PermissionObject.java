package com.taahyt.gyre.ranking;

import com.google.common.collect.Lists;

import java.util.List;

public class PermissionObject
{
    private final List<String> inheritances;
    private final List<String> disabledPerms;

    public PermissionObject()
    {
        this.inheritances = Lists.newArrayList();
        this.disabledPerms = Lists.newArrayList();
    }

    public List<String> getDisabledPerms() {
        return disabledPerms;
    }

    public List<String> getInheritances() {
        return inheritances;
    }
}
