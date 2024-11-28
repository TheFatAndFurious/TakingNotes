package Entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class NotesEntity {
    Long ID;
    String content;
    Timestamp timestamp;
    KeywordEntity keyword;

    public Long getId(){
        return this.ID;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public String getContent(){
        return this.content;
    }

    public void setID(Long id){
        this.ID = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = Timestamp.valueOf(timestamp);
    }

}
