package com.github.defaultw.plugin.domain.model.bo;

/**
 * 百度翻译请求参数
 *
 * @author Default.W
 * @date 2024/6/19
 */
public class BaiduTranslateParamBO extends TranslateParamBO {

    private String apiKey;

    private String secretKey;

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

    @Override
    public String toString() {
        return "BaiduTranslateParamBO{" +
                "apiKey='" + apiKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
