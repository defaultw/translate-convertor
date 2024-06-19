package com.github.defaultw.plugin.domain.model.dto;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.List;

/**
 *
 *
 * @author Default.W
 * @date 2024/6/19
 */
public class BaiduTranslateDTO {

    private Result result;

    @JSONField(name = "log_id")
    private String logId;

    @JSONField(name = "error_msg")
    private String errorMsg;

    @JSONField(name = "error_code")
    private String errorCode;

    public class Result {
        private String from;
        private String to;
        @JSONField(name = "trans_result")
        private List<TransResult> transResult;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public List<TransResult> getTransResult() {
            return transResult;
        }

        public void setTransResult(List<TransResult> transResult) {
            this.transResult = transResult;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", transResult=" + transResult +
                    '}';
        }
    }

    public class TransResult {
        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }

        @Override
        public String toString() {
            return "Trans{" +
                    "src='" + src + '\'' +
                    ", dst='" + dst + '\'' +
                    '}';
        }
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "BaiduTranslateDTO{" +
                "result=" + result +
                ", logId='" + logId + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
