package Entities;

import java.sql.Timestamp;

public class NotesEntity {
    Long ID;
    String content;
    Timestamp timestamp;
    KeywordEntity keyword;

    public String getContent(){
        return this.content;
    }

    public void setID(Long id){
        this.ID = id;
    }
}
