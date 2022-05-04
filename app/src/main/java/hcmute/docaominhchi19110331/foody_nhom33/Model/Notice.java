package hcmute.docaominhchi19110331.foody_nhom33.Model;

import java.time.LocalDateTime;

public class Notice {
    private String content;
    private String time;

    public Notice(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
