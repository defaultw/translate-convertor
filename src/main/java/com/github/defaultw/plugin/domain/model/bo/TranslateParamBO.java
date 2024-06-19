package com.github.defaultw.plugin.domain.model.bo;

/**
 * TODO
 *
 * @author Default.W
 * @date 2024/6/19
 */
public class TranslateParamBO {

    private String from;

    private String to;

    private String query;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "TranslateParamsBO{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
