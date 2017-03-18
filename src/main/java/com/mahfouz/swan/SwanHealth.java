package com.mahfouz.swan;

public final class SwanHealth {

    private final String desc;
    private final long memUsed;

    public SwanHealth(String desc, long memUsed) {
        this.desc = desc;
        this.memUsed = memUsed;
    }

    public String getDesc() {
        return desc;
    }

    public long getMemUsed() {
        return memUsed;
    }
}
