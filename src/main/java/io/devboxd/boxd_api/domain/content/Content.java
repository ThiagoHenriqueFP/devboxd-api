package io.devboxd.boxd_api.domain.content;

import io.devboxd.boxd_api.domain.profile.Profile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Content {

    @CreatedDate
    private LocalDateTime createdAt;

    private String body;

    private long id;

    private final Profile author;

    public Content(String body, Profile author){
        this.body = body;
        this.author = author;
    }

    abstract boolean delete();

    private boolean edit(String body){
        if(body != null && !body.equals(" ")) {
            this.body = body;
            return true;
        }

        return false;
    }

}