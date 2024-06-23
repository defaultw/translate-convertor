package com.github.defaultw.plugin.infrastructure;

import java.io.Serializable;
import java.util.Objects;

/**
 * 翻译服务
 *
 * @author Default.W
 * @date 2024/6/18
 */
public class TranslateComboItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;

    public TranslateComboItem() {
    }

    public TranslateComboItem(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TranslateComboItem that = (TranslateComboItem) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return this.name;
    }
}