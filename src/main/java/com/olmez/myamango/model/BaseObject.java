package com.olmez.myamango.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseObject implements Serializable, Comparable<BaseObject> {

    @Id
    @Field("_id")
    protected String id = new ObjectId().toString();
    protected boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public int compareTo(BaseObject obj) {
        return hasId(obj) ? this.id.compareTo(obj.getId()) : 0;
    }

    private boolean hasId(BaseObject obj) {
        return (obj != null) && (id != null) && (obj.getId() != null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BaseObject)) {
            return false;
        }
        BaseObject bo = (BaseObject) obj;
        return hasId(bo) && (this.id.equals(bo.getId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 79;
        return prime * result + ((id == null) ? 0 : id.hashCode());
    }

}
