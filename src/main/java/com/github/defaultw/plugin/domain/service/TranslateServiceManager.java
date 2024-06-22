package com.github.defaultw.plugin.domain.service;

import com.github.defaultw.plugin.domain.model.bo.BaiduTranslateConfigBO;
import com.github.defaultw.plugin.domain.service.impl.BaiduTranslateServiceImpl;
import com.github.defaultw.plugin.infrastructure.DataSetting;
import com.github.defaultw.plugin.infrastructure.DataState;

import java.util.HashMap;
import java.util.Map;

/**
 * 翻译服务管理器
 *
 * @author Default.W
 * @date 2024/6/20
 */
public class TranslateServiceManager {

    private final Map<String, TranslateService> services = new HashMap<>();

    private static TranslateServiceManager manager;

    public static TranslateServiceManager getInstance() {
        if (manager == null) {
            manager = new TranslateServiceManager();
        }
        return manager;
    }

    public void registerService(String serviceName, TranslateService service) {
        services.put(serviceName, service);
    }

    public TranslateService getTranslate(String serviceName) {
        DataState dataState = DataSetting.getInstance().getState();
        if (!services.containsKey(serviceName) && dataState != null) {
            // 注册百度翻译服务
            BaiduTranslateConfigBO bdConfig = new BaiduTranslateConfigBO();
            bdConfig.setFrom(dataState.getFrom().getCode());
            bdConfig.setTo(dataState.getTo().getCode());
            bdConfig.setApiKey(dataState.getApiKey());
            bdConfig.setSecretKey(dataState.getSecretKey());
            this.registerService("baidu", new BaiduTranslateServiceImpl(bdConfig));
        }
        return services.get(serviceName);
    }

}
