package com.github.defaultw.plugin.infrastructure;

/**
 * 持久化数据
 *
 * @author Default.W
 * @date 2024/6/18
 */
public class DataState {

    private Language from;

    private Language to;

    public Language getFrom() {
        return from;
    }

    public void setFrom(Language from) {
        this.from = from;
    }

    public Language getTo() {
        return to;
    }

    public void setTo(Language to) {
        this.to = to;
    }
}
