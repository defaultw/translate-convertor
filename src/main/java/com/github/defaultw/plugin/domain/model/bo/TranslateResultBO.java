package com.github.defaultw.plugin.domain.model.bo;

/**
 * TODO
 *
 * @author Default.W
 * @date 2024/6/19
 */
public class TranslateResultBO {

    /**
     * 翻译前文本
     */
    private String src;

    /**
     * 翻译后文本
     */
    private String dst;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    @Override
    public String toString() {
        return "TranslateResultBO{" +
                "src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                '}';
    }
}
