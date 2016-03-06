package com.hyzx.xschool.web.controller.request;

import org.apache.commons.lang.StringUtils;

/**
 * Created by jack on 15-11-1.
 */
public class OrgQueryFormBean extends PageQueryFormBean {

    private String name;
    private String phone;

    public boolean hasCriteria() {
        return StringUtils.isNotBlank(name) || StringUtils.isNotBlank(phone);
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
}
