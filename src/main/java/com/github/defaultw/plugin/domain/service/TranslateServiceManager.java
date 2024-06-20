package com.github.defaultw.plugin.domain.service;

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

    public void registerService(String serviceName, TranslateService service) {
        services.put(serviceName, service);
    }

    public TranslateService getTranslate(String serviceName) {
        return services.get(serviceName);
    }

}
