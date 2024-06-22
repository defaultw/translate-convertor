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

    private String apiKey;

    private String secretKey;

    /**
     * 当前选择的翻译服务
     */
    private String currentTranslateService;

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getCurrentTranslateService() {
        return currentTranslateService;
    }

    public void setCurrentTranslateService(String currentTranslateService) {
        this.currentTranslateService = currentTranslateService;
    }

    @Override
    public String toString() {
        return "DataState{" +
                "from=" + from +
                ", to=" + to +
                ", apiKey='" + apiKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", currentTranslateService='" + currentTranslateService + '\'' +
                '}';
    }
}
