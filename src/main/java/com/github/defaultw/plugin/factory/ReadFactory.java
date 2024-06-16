package com.github.defaultw.plugin.factory;

import com.github.defaultw.plugin.ui.Convertor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

/**
 * TODO
 *
 * @author Default.W
 * @date 2024/6/16
 */
public class ReadFactory implements ToolWindowFactory {

    private Convertor convertor = new Convertor();

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(convertor.getMainPanel(), "", false);
        toolWindow.getContentManager().addContent(content);


    }
}
