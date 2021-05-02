package aman.filemanager.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class FileSystemObject implements Serializable {

    @Id
    String id;

    String name;
    String ownerId;

    ObjectType type;

    @OneToMany(targetEntity = FileSystemObject.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<FileSystemObject> childObjects;

    public FileSystemObject() {
        super();
        id= UUID.randomUUID().toString();
    }

    public FileSystemObject(String name, String ownerId, ObjectType type, List<FileSystemObject> childObjects) {
        this.id=UUID.randomUUID().toString();
        this.name = name;
        this.ownerId = ownerId;
        this.type = type;
        this.childObjects = childObjects;
    }

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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public ObjectType getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }

    public List<FileSystemObject> getChildObjects() {
        return childObjects;
    }

    public void setChildObjects(List<FileSystemObject> childObjects) {
        this.childObjects = childObjects;
    }

    @Override
    public String toString() {
        return "FileSystemObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", type=" + type +
                ", childObjects=" + childObjects +
                '}';
    }
}
