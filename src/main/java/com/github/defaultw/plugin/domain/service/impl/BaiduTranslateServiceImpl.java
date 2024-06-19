package com.github.defaultw.plugin.domain.service.impl;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.defaultw.plugin.domain.model.bo.BaiduTranslateParamBO;
import com.github.defaultw.plugin.domain.model.bo.TranslateParamBO;
import com.github.defaultw.plugin.domain.model.bo.TranslateResultBO;
import com.github.defaultw.plugin.domain.model.dto.BaiduTranslateDTO;
import com.github.defaultw.plugin.domain.service.ITranslateService;

import java.util.*;

/**
 * 百度翻译
 *
 * @author Default.W
 * @date 2024/6/19
 */
public class BaiduTranslateServiceImpl implements ITranslateService {

    private static final String APP_JSON = "application/json";

    public static void main(String[] args) {
        BaiduTranslateParamBO b = new BaiduTranslateParamBO();
        b.setApiKey("WIvv1SxABmCUFJTKmYCQBl8f");
        b.setSecretKey("uXr1BuxL7WG5qGx3Qcz8NFhGo4k65wHS");
        b.setFrom("zh");
        b.setTo("en");
        b.setQuery("中国\n白净");
        BaiduTranslateServiceImpl t = new BaiduTranslateServiceImpl();
        List<TranslateResultBO> translate = t.translate(b);
        System.out.println(JSON.toJSON(translate));
    }

    @Override
    public List<TranslateResultBO> translate(TranslateParamBO params) {
        if (params instanceof BaiduTranslateParamBO) {
            BaiduTranslateParamBO bdParam = (BaiduTranslateParamBO) params;
            String accessToken = getAccessToken(bdParam);
            Map<String, String> pa = new HashMap<>();
            pa.put("from", bdParam.getFrom());
            pa.put("to", bdParam.getTo());
            pa.put("q", bdParam.getQuery());
            String body = HttpRequest.post("https://aip.baidubce.com/rpc/2.0/mt/texttrans/v1?access_token=" + accessToken)
                    .header("Content-Type", "application/json;charset=utf-8")
                    .header("Accept", APP_JSON)
                    .body(JSON.toJSONString(pa))
                    .execute().body();
            BaiduTranslateDTO translateDTO = JSON.parseObject(body, BaiduTranslateDTO.class);
            List<BaiduTranslateDTO.TransResult> transResult = translateDTO.getResult().getTransResult();
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
    private String getAccessToken(BaiduTranslateParamBO param) {
        String uParams = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
                param.getApiKey(), param.getSecretKey());
        String token = HttpRequest.post("https://aip.baidubce.com/oauth/2.0/token?" + uParams)
                .header("Content-Type", APP_JSON)
                .header("Accept", APP_JSON)
                .execute().body();
        return JSON.parseObject(token).getString("access_token");
    }

}
