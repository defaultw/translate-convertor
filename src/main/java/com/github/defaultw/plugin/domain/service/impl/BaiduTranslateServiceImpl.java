package com.github.defaultw.plugin.domain.service.impl;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.defaultw.plugin.domain.model.bo.BaiduTranslateConfigBO;
import com.github.defaultw.plugin.domain.model.bo.TranslateResultBO;
import com.github.defaultw.plugin.domain.model.dto.BaiduTranslateDTO;
import com.github.defaultw.plugin.domain.service.TranslateService;

import java.util.*;

/**
 * 百度翻译
 *
 * @author Default.W
 * @date 2024/6/19
 */
public class BaiduTranslateServiceImpl implements TranslateService {

    private static final String APP_JSON = "application/json";

    private final BaiduTranslateConfigBO baiduTranslateConfig;

    public BaiduTranslateServiceImpl(BaiduTranslateConfigBO baiduTranslateConfig) {
        this.baiduTranslateConfig = baiduTranslateConfig;
    }

    @Override
    public List<TranslateResultBO> translate(String text) {
        String accessToken = getAccessToken(baiduTranslateConfig.getApiKey(), baiduTranslateConfig.getSecretKey());
        Map<String, String> pa = new HashMap<>(3);
        pa.put("from", baiduTranslateConfig.getFrom());
        pa.put("to",baiduTranslateConfig.getTo());
        pa.put("q", text);
        String body = HttpRequest.post("https://aip.baidubce.com/rpc/2.0/mt/texttrans/v1?access_token=" + accessToken)
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Accept", APP_JSON)
                .body(JSON.toJSONString(pa))
                .execute().body();
        BaiduTranslateDTO translateDTO = JSON.parseObject(body, BaiduTranslateDTO.class);
        BaiduTranslateDTO.Result data = translateDTO.getResult();
        if (data != null) {
            List<BaiduTranslateDTO.TransResult> transResult = data.getTransResult();
            List<TranslateResultBO> results = new ArrayList<>();
            for (BaiduTranslateDTO.TransResult result : transResult) {
                TranslateResultBO res = new TranslateResultBO();
                res.setSrc(result.getSrc());
                res.setDst(result.getDst());
                results.add(res);
            }
            return results;
        }
        return Collections.emptyList();
    }

    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @return 鉴权签名（Access Token）
     */
    private String getAccessToken(String apiKey, String secretKey) {
        String uParams = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
                apiKey, secretKey);
        String token = HttpRequest.post("https://aip.baidubce.com/oauth/2.0/token?" + uParams)
                .header("Content-Type", APP_JSON)
                .header("Accept", APP_JSON)
                .execute().body();
        return JSON.parseObject(token).getString("access_token");
    }

}
