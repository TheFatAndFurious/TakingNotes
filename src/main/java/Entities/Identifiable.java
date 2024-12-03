package Entities;

import java.time.LocalDateTime;

public abstract class Identifiable {
    public Long id;
    public String content;
    public LocalDateTime timestamp;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
