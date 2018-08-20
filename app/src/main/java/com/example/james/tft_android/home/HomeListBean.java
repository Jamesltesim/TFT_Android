package com.example.james.tft_android.home;

import java.util.List;

/**
 * Created by caobin on 2017/9/14.
 */

public class HomeListBean {

        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> groupList) {
            this.data = groupList;
        }



//        public static class GroupListBean {
        public static class DataBean {
            private String groupName;
            private List<ChildListBean> childList;

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public List<ChildListBean> getChildList() {
                return childList;
            }

            public void setChildList(List<ChildListBean> childList) {
                this.childList = childList;
            }

            public static class ChildListBean {

                private String childName;
                private String openTime;
                private boolean isGroup;
                private boolean isFooter;

                public boolean isDiscountItem() {
                    return isDiscountItem;
                }

                public void setDiscountItem(boolean discountItem) {
                    isDiscountItem = discountItem;
                }

                private boolean isDiscountItem;

                public boolean isFooter() {
                    return isFooter;
                }

                public void setFooter(boolean footer) {
                    isFooter = footer;
                }

                /**
                 * 添加child
                 *
                 * @param childName
                 */
                public ChildListBean(String childName) {
                    this.childName = childName;
                }

                /**
                 * 把group当成child
                 *
                 * @param childName
                 * @param isGroup
                 */
                public ChildListBean(String childName, boolean isGroup) {
                    this.childName = childName;
                    this.isGroup = isGroup;
                }

                public ChildListBean(String childName, boolean isGroup,boolean ifFooter) {
                    this.childName = childName;
                    this.isFooter = ifFooter;
                }

                public boolean isGroup() {
                    return isGroup;
                }

                public void setGroup(boolean group) {
                    isGroup = group;
                }

                public String getChildName() {
                    return childName;
                }

                public void setChildName(String childName) {
                    this.childName = childName;
                }

                public String getOpenTime() {
                    return openTime;
                }

                public void setOpenTime(String openTime) {
                    this.openTime = openTime;
                }
            }
        }
    }
