package com.teamway.planning.util;

public enum TimeTable {
    Morning(0,8),
    Afternoon(8,16),
    Night(16,24);

    public int start;
    public int end;

    TimeTable(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
