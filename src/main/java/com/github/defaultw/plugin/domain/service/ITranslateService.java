package com.github.defaultw.plugin.domain.service;

import com.github.defaultw.plugin.domain.model.bo.TranslateParamBO;
import com.github.defaultw.plugin.domain.model.bo.TranslateResultBO;

import java.util.List;

/**
 * 翻译统一接口
 *
 * @author Default.W
 * @date 2024/6/19
 */
public interface ITranslateService {

    /**
     * 翻译
     *
     * @param params 翻译相关信息
     * @return 翻译后结果
     */
    List<TranslateResultBO> translate(TranslateParamBO params);


}
