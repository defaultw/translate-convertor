package com.github.defaultw.plugin.infrastructure;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

/**
 * 数据持久化
 *
 * @author Default.W
 * @date 2024/6/18
 */
@State(name = "DataSetting", storages = @Storage("plugin.xml"))
public class DataSetting implements PersistentStateComponent<DataState> {

    private DataState dataState = new DataState();

    public static DataSetting getInstance() {
        return ServiceManager.getService(DataSetting.class);
    }

    @Override
    public DataState getState() {
        // 默认使用百度翻译
        if (this.dataState.getTranslateComboItem() == null) {
            this.dataState.setTranslateComboItem(new TranslateComboItem("baidu", "百度翻译"));
        }
        return this.dataState;
    }

    @Override
    public void loadState(@NotNull DataState state) {
        this.dataState = state;
    }

}
