package com.dchoe.portal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Document(collection="nodenav")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class NodeNav {

    public NodeNav()
    {
        super();
    }

    public NodeNav(String name, int level, boolean hasChild, List<NodeNav> children)
    {
        this.name = name;
        this.level = level;
        this.hasChild = hasChild;
        this.children = children;
    }

    @Id
    private String id;

    @NotBlank
    @Size(max=100)
//    @Indexed(unique=true)
    private String name;

    private int level;
    private boolean hasChild;

    @DBRef
    private List<NodeNav> children;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public List<NodeNav> getChildren() {
        return children;
    }

    public void setChildren(List<NodeNav> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "NodeNav{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", hasChild=" + hasChild +
                ", children=" + children +
                '}';
    }
}
