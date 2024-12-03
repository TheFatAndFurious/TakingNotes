package Entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class NotesEntity implements Identifiable {
    Long ID;
    String content;
    Timestamp timestamp;
    KeywordEntity keyword;

    @Override
    public Long getId(){
        return this.ID;
    }

    @Override
    public void setId(Long id) {
        this.ID = id;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public String getContent(){
        return this.content;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = Timestamp.valueOf(timestamp);
    }

}
