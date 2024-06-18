package com.github.defaultw.plugin.module;

import com.github.defaultw.plugin.ui.BaseConsoleUi;
import com.github.defaultw.plugin.ui.PageComponentUi;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.JBSplitter;

/**
 * 主体页面
 *
 * @author Default.W
 * @date 2024/6/17
 */
public class ViewBars extends SimpleToolWindowPanel {

    private Project project;

    private BaseConsoleUi baseConsoleUi;


    public ViewBars(Project project) {
        super(false, true);
        this.project = project;
        this.baseConsoleUi = new BaseConsoleUi();
        this.baseConsoleUi.addTab("Translate", new PageComponentUi(1).getMainPanel());
        this.baseConsoleUi.addTab("Convertor", new PageComponentUi(2).getMainPanel());

        // 设置窗体侧边栏按钮
        DefaultActionGroup group = new DefaultActionGroup();
        group.add(new SettingBar(this));

        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("bar", group, false);
        toolbar.setTargetComponent(this);
        setToolbar(toolbar.getComponent());

        // 添加
        JBSplitter splitter = new JBSplitter(false);
        splitter.setSplitterProportionKey("main.splitter.key");
        splitter.setFirstComponent(baseConsoleUi.getTabbedPane());
        splitter.setProportion(0.3f);
        setContent(splitter);

    }

    public Project getProject() {
        return project;
    }

    public BaseConsoleUi getBaseConsoleUi() {
        return baseConsoleUi;
    }
}
