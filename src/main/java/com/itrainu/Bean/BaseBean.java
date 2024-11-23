package com.itrainu.Bean;

import java.io.Serializable;

/**
 * Parent class of all Beans in application. It contains generic attributes.
 * 
 * @autor Kirti Singh
 */
public abstract class BaseBean implements Serializable, DropdownListBean, Comparable<BaseBean> {
    protected long id;
    protected String createdBy;

    // Uncomment and add if needed
 
    // protected String modifiedBy;
    // protected Timestamp createdDatetime;
    // protected Timestamp modifiedDatetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // Abstract methods to be implemented by subclasses
    public abstract String getKey();
    public abstract String getValue();

    // Uncomment and add getters/setters if needed
    // public String getModifiedBy() {
    //     return modifiedBy;
    // }

    // public void setModifiedBy(String modifiedBy) {
    //     this.modifiedBy = modifiedBy;
    // }

    // public Timestamp getCreatedDatetime() {
    //     return createdDatetime;
    // }

    // public void setCreatedDatetime(Timestamp createdDatetime) {
    //     this.createdDatetime = createdDatetime;
    // }

    // public Timestamp getModifiedDatetime() {
    //     return modifiedDatetime;
    // }

    // public void setModifiedDatetime(Timestamp modifiedDatetime) {
    //     this.modifiedDatetime = modifiedDatetime;
    // }

    @Override
    public int compareTo(BaseBean next) {
        return getValue().compareTo(next.getValue());
    }
}
