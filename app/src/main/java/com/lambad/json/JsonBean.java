package com.lambad.json;

import java.util.List;

/**
 * Created by xushun on  2019/7/2 11:11.
 * Email：shunplus@163.com
 * Des：
 */
public class JsonBean {

    /**
     * errorCode :
     * message :
     * iserror : false
     * data : {"id":"45303010614461710011809","type":"home_qz","judgeId":"cshish20","createTime":1560050127028,"deputyMedia":[{"id":"76224052711161610025209","name":"上传","fid":"45303010614461710011809","subtype":"1","sort":1,"createTime":1561025911621},{"id":"76224052712482580072900","name":"直播","fid":"45303010614461710011809","subtype":"2","sort":2,"createTime":1561025911621},{"id":"76224052714152410005557","name":"拍照","fid":"45303010614461710011809","subtype":"3","sort":3,"createTime":1561025911621},{"id":"76224052715485040052445","name":"录像","fid":"45303010614461710011809","subtype":"5","sort":4,"createTime":1561025911621},{"id":"7622405271741360041542","name":"录音","fid":"45303010614461710011809","subtype":"4","sort":5,"createTime":1561025911621}]}
     * exception :
     */

    private String errorCode;
    private String message;
    private boolean iserror;
    private DataBean data;
    private Expetin exception;

    public Expetin getException() {
        return exception;
    }

    public void setException(Expetin exception) {
        this.exception = exception;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIserror() {
        return iserror;
    }

    public void setIserror(boolean iserror) {
        this.iserror = iserror;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {
        /**
         * id : 45303010614461710011809
         * type : home_qz
         * judgeId : cshish20
         * createTime : 1560050127028
         * deputyMedia : [{"id":"76224052711161610025209","name":"上传","fid":"45303010614461710011809","subtype":"1","sort":1,"createTime":1561025911621},{"id":"76224052712482580072900","name":"直播","fid":"45303010614461710011809","subtype":"2","sort":2,"createTime":1561025911621},{"id":"76224052714152410005557","name":"拍照","fid":"45303010614461710011809","subtype":"3","sort":3,"createTime":1561025911621},{"id":"76224052715485040052445","name":"录像","fid":"45303010614461710011809","subtype":"5","sort":4,"createTime":1561025911621},{"id":"7622405271741360041542","name":"录音","fid":"45303010614461710011809","subtype":"4","sort":5,"createTime":1561025911621}]
         */

        private String id;
        private String type;
        private String judgeId;
        private long createTime;
        private List<DeputyMediaBean> deputyMedia;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getJudgeId() {
            return judgeId;
        }

        public void setJudgeId(String judgeId) {
            this.judgeId = judgeId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public List<DeputyMediaBean> getDeputyMedia() {
            return deputyMedia;
        }

        public void setDeputyMedia(List<DeputyMediaBean> deputyMedia) {
            this.deputyMedia = deputyMedia;
        }

        public static class DeputyMediaBean {
            /**
             * id : 76224052711161610025209
             * name : 上传
             * fid : 45303010614461710011809
             * subtype : 1
             * sort : 1
             * createTime : 1561025911621
             */

            private String id;
            private String name;
            private String fid;
            private String subtype;
            private int sort;
            private long createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getSubtype() {
                return subtype;
            }

            public void setSubtype(String subtype) {
                this.subtype = subtype;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }
    }

    public  class Expetin{
        private String nem;

        public String getNem() {
            return nem;
        }

        public void setNem(String nem) {
            this.nem = nem;
        }
    }

}
