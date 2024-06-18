package com.github.defaultw.plugin.infrastructure;

import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    public @Nullable DataState getState() {
        return this.dataState;
    }

    @Override
    public void loadState(@NotNull DataState state) {
        this.dataState = state;
    }

}
