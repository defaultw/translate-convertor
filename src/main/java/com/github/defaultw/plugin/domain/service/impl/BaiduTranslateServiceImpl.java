package com.github.defaultw.plugin.domain.service.impl;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.github.defaultw.plugin.domain.model.bo.BaiduTranslateConfigBO;
import com.github.defaultw.plugin.domain.model.bo.TranslateConfigBO;
import com.github.defaultw.plugin.domain.model.bo.TranslateResultBO;
import com.github.defaultw.plugin.domain.model.dto.BaiduTranslateDTO;
import com.github.defaultw.plugin.domain.service.TranslateService;
import com.github.defaultw.plugin.domain.service.TranslateServiceManager;
import com.github.defaultw.plugin.infrastructure.DataSetting;
import com.github.defaultw.plugin.infrastructure.DataState;
import org.apache.commons.lang3.StringUtils;

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
        if (!StringUtils.isBlank(translateDTO.getErrorCode()) || !StringUtils.isBlank(translateDTO.getErrorMsg())) {
            throw new RuntimeException(String.format("百度翻译服务调用失败, Code: %s, Message: %s",
                    translateDTO.getErrorCode(), translateDTO.getErrorMsg()));
        }
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

    @Override
    public void register(TranslateConfigBO config) {
        DataState dataState = DataSetting.getInstance().getState();
        if (dataState == null) {
            return;
        }
        // 注册百度翻译服务
        BaiduTranslateConfigBO bdConfig = new BaiduTranslateConfigBO();
        bdConfig.setFrom(dataState.getFrom().getCode());
        bdConfig.setTo(dataState.getTo().getCode());
        bdConfig.setApiKey(dataState.getApiKey());
        bdConfig.setSecretKey(dataState.getSecretKey());
        TranslateServiceManager.getInstance().registerService("baidu", new BaiduTranslateServiceImpl(bdConfig));
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
