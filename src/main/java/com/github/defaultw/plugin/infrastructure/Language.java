package com.github.defaultw.plugin.infrastructure;

import java.util.Objects;

/**
 * 翻译语言
 *
 * @author Default.W
 * @date 2024/6/18
 */
public class Language {

    private String code;

    private String name;

    public Language() {
    }

    public Language(String code, String name) {
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
        Language language = (Language) o;
        return Objects.equals(code, language.code) && Objects.equals(name, language.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
