package com.github.defaultw.plugin.domain.service;

import com.github.defaultw.plugin.domain.model.bo.TranslateConfigBO;
import com.github.defaultw.plugin.domain.model.bo.TranslateResultBO;

import java.util.List;

/**
 * 翻译统一接口
 *
 * @author Default.W
 * @date 2024/6/19
 */
public interface TranslateService {

    /**
     * 翻译
     *
     * @param text 待翻译字符串
     * @return 翻译后结果
     */
    List<TranslateResultBO> translate(String text);

    /**
     * 注册服务
     * @param config 服务配置
     */
    void register(TranslateConfigBO config);


}
