package com.lambad.network;

import java.util.List;

/**
 * Created by xushun on  2020/3/15 20:19.
 * Email：shunplus@163.com
 * Des：
 */
public class Bean {

    /**
     * errorCode :
     * message :
     * iserror : false
     * data : [{"id":"0","pId":"-9999","online":false,"name":"最高人民法院","phone":"","mobile":"","open":false}]
     * exception :
     */

    private String errorCode;
    private String message;
    private boolean iserror;
    private String exception;
    private List<DataBean> data;

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

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 0
         * pId : -9999
         * online : false
         * name : 最高人民法院
         * phone :
         * mobile :
         * open : false
         */

        private String id;
        private String pId;
        private boolean online;
        private String name;
        private String phone;
        private String mobile;
        private boolean open;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public boolean isOnline() {
            return online;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }
    }
}
