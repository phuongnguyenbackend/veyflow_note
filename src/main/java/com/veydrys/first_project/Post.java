package com.veydrys.first_project;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String idUser;
    private String title;
    private String content;

    public String getId(){
        return id;
    }
    public String getIdUser(){
        return idUser;
    }
    public void setIdUser(String idUser){
        this.idUser = idUser;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
