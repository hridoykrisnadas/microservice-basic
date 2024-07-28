package com.hridoykrisna.bookservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseModel {
    @Column(updatable = false)
    private int createdBy;
    @Column(updatable = false)
    private Date createdAt;
    private int updateBy;
    private Date updateAt;
    private boolean isActive;

    @PrePersist
    public void setPreInsertData(){
        this.createdAt = new Date();
        this.updateAt = new Date();
        this.isActive = true;
    }

    @PreUpdate
    public void setPostUpdateData(){
        this.updateAt = new Date();
    }


}
