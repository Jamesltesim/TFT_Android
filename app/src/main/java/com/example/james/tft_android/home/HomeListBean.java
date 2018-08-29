package com.example.james.tft_android.home;

import com.example.james.tft_android.base.model.ItemBean;

import java.util.List;

/**
 * Created by caobin on 2017/9/14.
 */

public class HomeListBean {


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

            public static class ChildListBean extends ItemBean{

                private String openTime;
                private String childName;

                /**
                 * 添加child
                 *
                 * @param childName
                 */
                public ChildListBean(String childName) {
                    this.childName = childName;
                }



                private boolean isDiscountHeader;
                private boolean isDiscountItem;
                private boolean isDiscountFooter;

                private boolean isGeneralHeader;
                private boolean isGeneralItem;
                private boolean isGeneralFooter;
                //get set
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

                public boolean isGeneralHeader() {
                    return isGeneralHeader;
                }

                public void setGeneralHeader(boolean generalHeader) {
                    isGeneralHeader = generalHeader;
                }

                public boolean isGeneralFooter() {
                    return isGeneralFooter;
                }

                public void setGeneralFooter(boolean generalFooter) {
                    isGeneralFooter = generalFooter;
                }

                public boolean isDiscountHeader() {
                    return isDiscountHeader;
                }

                public void setDiscountHeader(boolean discountHeader) {
                    isDiscountHeader = discountHeader;
                }

                public boolean isDiscountFooter() {
                    return isDiscountFooter;
                }

                public void setDiscountFooter(boolean discountFooter) {
                    isDiscountFooter = discountFooter;
                }

                public boolean isGeneralItem() {
                    return isGeneralItem;
                }

                public void setGeneralItem(boolean generalItem) {
                    isGeneralItem = generalItem;
                }

                public boolean isDiscountItem() {
                    return isDiscountItem;
                }

                public void setDiscountItem(boolean discountItem) {
                    isDiscountItem = discountItem;
                }

            }
}
