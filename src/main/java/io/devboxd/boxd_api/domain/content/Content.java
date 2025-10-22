package io.devboxd.boxd_api.domain.content;

import io.devboxd.boxd_api.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Content {

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedDate
    private LocalDateTime updatedAt;

    private String body;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Profile author;

    protected abstract boolean create();

    protected abstract boolean delete();

    private boolean edit(String body){
        if(body != null && !body.equals(" ")) {
            this.body = body;
            updatedAt = LocalDateTime.now();
            return true;
        }

        return false;
    }

}