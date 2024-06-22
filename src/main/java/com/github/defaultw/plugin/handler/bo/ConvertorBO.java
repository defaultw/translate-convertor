package com.github.defaultw.plugin.handler.bo;

/**
 * TODO
 *
 * @author Default.W
 * @date 2024/6/16
 */
public class ConvertorBO {

    private String sourceFilePath;

    private String translateFilePath;

    private String result;

    private Boolean completeLog;

    private Boolean isTranslateNeeded;

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getTranslateFilePath() {
        return translateFilePath;
    }

    public void setTranslateFilePath(String translateFilePath) {
        this.translateFilePath = translateFilePath;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getCompleteLog() {
        return completeLog;
    }

    public void setCompleteLog(Boolean completeLog) {
        this.completeLog = completeLog;
    }

    public Boolean getTranslateNeeded() {
        return isTranslateNeeded;
    }

    public void setTranslateNeeded(Boolean translateNeeded) {
        isTranslateNeeded = translateNeeded;
    }

    @Override
    public String toString() {
        return "ConvertorBO{" +
                "sourceFilePath='" + sourceFilePath + '\'' +
                ", translateFilePath='" + translateFilePath + '\'' +
                ", result='" + result + '\'' +
                ", completeLog=" + completeLog +
                ", isTranslateNeeded=" + isTranslateNeeded +
                '}';
    }
}
