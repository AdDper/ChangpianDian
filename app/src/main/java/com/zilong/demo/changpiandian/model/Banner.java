package com.zilong.demo.changpiandian.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class Banner {



    /**
     * data : [{"getLinkAddress":"","HomePagePhoto":"/jeesite/userfiles/1/images/sys/xyHomePhoto/2017/05/146833-106.jpg","LinkType":"2","LinkId":"201705110011"},{"getLinkAddress":"","HomePagePhoto":"/jeesite/userfiles/1/images/sys/xyHomePhoto/2017/05/234850-106.jpg","LinkType":"0","LinkId":"20170511150008"},{"getLinkAddress":"","HomePagePhoto":"/jeesite/userfiles/1/images/sys/xyHomePhoto/2017/05/234850-106.jpg","LinkType":"1","LinkId":"2017051117110001"}]
     * message : 查询成功
     * errorcode : OK
     */

    private String message;
    private String errorcode;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * getLinkAddress :
         * HomePagePhoto : /jeesite/userfiles/1/images/sys/xyHomePhoto/2017/05/146833-106.jpg
         * LinkType : 2
         * LinkId : 201705110011
         */

        private String getLinkAddress;
        private String HomePagePhoto;
        private String LinkType;
        private String LinkId;

        public String getGetLinkAddress() {
            return getLinkAddress;
        }

        public void setGetLinkAddress(String getLinkAddress) {
            this.getLinkAddress = getLinkAddress;
        }

        public String getHomePagePhoto() {
            return HomePagePhoto;
        }

        public void setHomePagePhoto(String HomePagePhoto) {
            this.HomePagePhoto = HomePagePhoto;
        }

        public String getLinkType() {
            return LinkType;
        }

        public void setLinkType(String LinkType) {
            this.LinkType = LinkType;
        }

        public String getLinkId() {
            return LinkId;
        }

        public void setLinkId(String LinkId) {
            this.LinkId = LinkId;
        }
    }
}
