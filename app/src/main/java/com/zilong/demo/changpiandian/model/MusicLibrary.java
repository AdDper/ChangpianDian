package com.zilong.demo.changpiandian.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class MusicLibrary {

    /**
     * data : [{"Musicianlist":[{"UserName":"ytgctuybuj","Address":"","UserNum":"201705170001","HeadPortrait":"","Vip":"","userRole":"2","Profile":""},{"UserName":"xy_655956","Address":"","UserNum":"201705260001","HeadPortrait":"/jeesite/userfiles/1/images/sys/xyUser/headPortraitUrl/2017/05/234850-106_medium.jpg","Vip":"1","userRole":"2","Profile":"大家好"},{"UserName":"消消乐","Address":"","UserNum":"201705260005","HeadPortrait":"","Vip":"","userRole":"2","Profile":"消消乐消消乐消消乐消消乐消消乐消消乐消消乐消消乐消消乐消消乐消消乐"},{"UserName":"家集客","Address":"","UserNum":"201705260004","HeadPortrait":"","Vip":"","userRole":"2","Profile":"时刻记得hiu"},{"UserName":"科技三角尺","Address":"","UserNum":"201705260003","HeadPortrait":"","Vip":"1","userRole":"2","Profile":"上帝视角克难奋进鞥及"},{"UserName":"xy_51666266162","Address":"","UserNum":"201705260002","HeadPortrait":"","Vip":"1","userRole":"2","Profile":"大家好"}],"albumlist":[{"getAlbumIntroduction":"情况危情况危急情况危急情况危急情况危急情况危急情况危急情况危急情况危急情况危急急情况危急","UserName":"黄大大_51515","AlbumFrom":"心意","getReleaseTime":"2017-05-13","Price":"10","AlbumName":"情况危急","albumId":"20170513080009","SellCount":80,"CollectionCount":0,"getAlbumPhotoUrl":""},{"getAlbumIntroduction":"安时处顺才不会安时处顺才不会安时处顺才不会安时处顺才不会安时处顺才不会安时处顺才不会安时处顺才不会","UserName":"xy_12345678910b6n","AlbumFrom":"此刻是金","getReleaseTime":"2017-05-18","Price":"10","AlbumName":"安时处顺才不会","albumId":"20170513080004","SellCount":10,"CollectionCount":0,"getAlbumPhotoUrl":""},{"getAlbumIntroduction":"小黄花小黄花","UserName":"黄大大_51515","AlbumFrom":"心音传媒","getReleaseTime":"2017-05-13","Price":"10","AlbumName":"小黄花","albumId":"20170511150008","SellCount":0,"CollectionCount":0,"getAlbumPhotoUrl":""},{"getAlbumIntroduction":"优雅优雅优雅优雅优雅优雅优雅","UserName":"xy_6261666duhsd","AlbumFrom":"心意","getReleaseTime":"2017-05-13","Price":"10","AlbumName":"优雅","albumId":"20170513080002","SellCount":0,"CollectionCount":0,"getAlbumPhotoUrl":""},{"getAlbumIntroduction":"无线电无线电无线电无线电无线电无线电","UserName":"xy_6261666duhsd","AlbumFrom":"心意","getReleaseTime":"2017-05-13","Price":"10","AlbumName":"无线电","albumId":"20170513080008","SellCount":0,"CollectionCount":0,"getAlbumPhotoUrl":""},{"getAlbumIntroduction":"","UserName":"黄大大_51515","AlbumFrom":"","getReleaseTime":"2017-05-13","Price":"10","AlbumName":"数据采集舌敝唇焦","albumId":"20170512110001","SellCount":0,"CollectionCount":0,"getAlbumPhotoUrl":""}],"homePagelist":[{"getLinkAddress":"","HomePagePhoto":"/jeesite/userfiles/1/images/sys/xyHomePhoto/2017/05/146833-106.jpg","LinkType":"0","LinkId":"20170513080010"},{"getLinkAddress":"","HomePagePhoto":"/jeesite/userfiles/1/images/sys/xyHomePhoto/2017/05/146833-106.jpg","LinkType":"1","LinkId":""},{"getLinkAddress":"","HomePagePhoto":"/jeesite/userfiles/1/images/sys/xyHomePhoto/2017/05/146833-106.jpg","LinkType":"0","LinkId":""},{"getLinkAddress":"","HomePagePhoto":"/jeesite/userfiles/1/images/sys/xyHomePhoto/2017/05/146833-106.jpg","LinkType":"2","LinkId":""}],"homeActivelist":[],"singerlist":[{"UserName":"沙茶牛蒡丝","Address":"","UserNum":"201705260007","HeadPortrait":"","Vip":"","userRole":"1","Profile":"沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝"},{"UserName":"黄大大_51515","Address":"","UserNum":"201705110011","HeadPortrait":"","Vip":"","userRole":"1","Profile":""},{"UserName":"案件成本核算","Address":"","UserNum":"201705260008","HeadPortrait":"","Vip":"","userRole":"1","Profile":"案件成本核算案件成本核算案件成本核算案件成本核算案件成本核算"},{"UserName":"爱心","Address":"","UserNum":"201705260006","HeadPortrait":"","Vip":"","userRole":"1","Profile":"爱心爱心爱心爱心爱心爱心爱心爱心爱心爱心爱心爱心爱心爱心"},{"UserName":"剑姬","Address":"","UserNum":"20170502006","HeadPortrait":"|/jeesite/userfiles/1/files/sys/xyUser/2017/04/234850-106_small.jpg","Vip":"1","userRole":"1","Profile":""},{"UserName":"小红马","Address":"","UserNum":"20170504001","HeadPortrait":"","Vip":"1","userRole":"1","Profile":""}]}]
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
        private List<MusicianlistBean> Musicianlist;
        private List<AlbumlistBean> albumlist;
        private List<HomePagelistBean> homePagelist;
        private List<?> homeActivelist;
        private List<SingerlistBean> singerlist;

        public List<MusicianlistBean> getMusicianlist() {
            return Musicianlist;
        }

        public void setMusicianlist(List<MusicianlistBean> Musicianlist) {
            this.Musicianlist = Musicianlist;
        }

        public List<AlbumlistBean> getAlbumlist() {
            return albumlist;
        }

        public void setAlbumlist(List<AlbumlistBean> albumlist) {
            this.albumlist = albumlist;
        }

        public List<HomePagelistBean> getHomePagelist() {
            return homePagelist;
        }

        public void setHomePagelist(List<HomePagelistBean> homePagelist) {
            this.homePagelist = homePagelist;
        }

        public List<?> getHomeActivelist() {
            return homeActivelist;
        }

        public void setHomeActivelist(List<?> homeActivelist) {
            this.homeActivelist = homeActivelist;
        }

        public List<SingerlistBean> getSingerlist() {
            return singerlist;
        }

        public void setSingerlist(List<SingerlistBean> singerlist) {
            this.singerlist = singerlist;
        }

        public static class MusicianlistBean {
            /**
             * UserName : ytgctuybuj
             * Address :
             * UserNum : 201705170001
             * HeadPortrait :
             * Vip :
             * userRole : 2
             * Profile :
             */

            private String UserName;
            private String Address;
            private String UserNum;
            private String HeadPortrait;
            private String Vip;
            private String userRole;
            private String Profile;

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getUserNum() {
                return UserNum;
            }

            public void setUserNum(String UserNum) {
                this.UserNum = UserNum;
            }

            public String getHeadPortrait() {
                return HeadPortrait;
            }

            public void setHeadPortrait(String HeadPortrait) {
                this.HeadPortrait = HeadPortrait;
            }

            public String getVip() {
                return Vip;
            }

            public void setVip(String Vip) {
                this.Vip = Vip;
            }

            public String getUserRole() {
                return userRole;
            }

            public void setUserRole(String userRole) {
                this.userRole = userRole;
            }

            public String getProfile() {
                return Profile;
            }

            public void setProfile(String Profile) {
                this.Profile = Profile;
            }
        }

        public static class AlbumlistBean {
            /**
             * getAlbumIntroduction : 情况危情况危急情况危急情况危急情况危急情况危急情况危急情况危急情况危急情况危急急情况危急
             * UserName : 黄大大_51515
             * AlbumFrom : 心意
             * getReleaseTime : 2017-05-13
             * Price : 10
             * AlbumName : 情况危急
             * albumId : 20170513080009
             * SellCount : 80
             * CollectionCount : 0
             * getAlbumPhotoUrl :
             */

            private String getAlbumIntroduction;
            private String UserName;
            private String AlbumFrom;
            private String getReleaseTime;
            private String Price;
            private String AlbumName;
            private String albumId;
            private int SellCount;
            private int CollectionCount;
            private String getAlbumPhotoUrl;

            public String getGetAlbumIntroduction() {
                return getAlbumIntroduction;
            }

            public void setGetAlbumIntroduction(String getAlbumIntroduction) {
                this.getAlbumIntroduction = getAlbumIntroduction;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getAlbumFrom() {
                return AlbumFrom;
            }

            public void setAlbumFrom(String AlbumFrom) {
                this.AlbumFrom = AlbumFrom;
            }

            public String getGetReleaseTime() {
                return getReleaseTime;
            }

            public void setGetReleaseTime(String getReleaseTime) {
                this.getReleaseTime = getReleaseTime;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String Price) {
                this.Price = Price;
            }

            public String getAlbumName() {
                return AlbumName;
            }

            public void setAlbumName(String AlbumName) {
                this.AlbumName = AlbumName;
            }

            public String getAlbumId() {
                return albumId;
            }

            public void setAlbumId(String albumId) {
                this.albumId = albumId;
            }

            public int getSellCount() {
                return SellCount;
            }

            public void setSellCount(int SellCount) {
                this.SellCount = SellCount;
            }

            public int getCollectionCount() {
                return CollectionCount;
            }

            public void setCollectionCount(int CollectionCount) {
                this.CollectionCount = CollectionCount;
            }

            public String getGetAlbumPhotoUrl() {
                return getAlbumPhotoUrl;
            }

            public void setGetAlbumPhotoUrl(String getAlbumPhotoUrl) {
                this.getAlbumPhotoUrl = getAlbumPhotoUrl;
            }
        }

        public static class HomePagelistBean {
            /**
             * getLinkAddress :
             * HomePagePhoto : /jeesite/userfiles/1/images/sys/xyHomePhoto/2017/05/146833-106.jpg
             * LinkType : 0
             * LinkId : 20170513080010
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

        public static class SingerlistBean {
            /**
             * UserName : 沙茶牛蒡丝
             * Address :
             * UserNum : 201705260007
             * HeadPortrait :
             * Vip :
             * userRole : 1
             * Profile : 沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝沙茶牛蒡丝
             */

            private String UserName;
            private String Address;
            private String UserNum;
            private String HeadPortrait;
            private String Vip;
            private String userRole;
            private String Profile;

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getUserNum() {
                return UserNum;
            }

            public void setUserNum(String UserNum) {
                this.UserNum = UserNum;
            }

            public String getHeadPortrait() {
                return HeadPortrait;
            }

            public void setHeadPortrait(String HeadPortrait) {
                this.HeadPortrait = HeadPortrait;
            }

            public String getVip() {
                return Vip;
            }

            public void setVip(String Vip) {
                this.Vip = Vip;
            }

            public String getUserRole() {
                return userRole;
            }

            public void setUserRole(String userRole) {
                this.userRole = userRole;
            }

            public String getProfile() {
                return Profile;
            }

            public void setProfile(String Profile) {
                this.Profile = Profile;
            }
        }
    }
}
