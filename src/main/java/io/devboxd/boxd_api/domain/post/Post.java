package io.devboxd.boxd_api.domain.post;

import io.devboxd.boxd_api.domain.comments.Comment;
import io.devboxd.boxd_api.domain.content.Content;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "posts")
public class Post extends Content {

    private String header;

    private Integer views = 0;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    private List<String> photos;

    @Override
    protected boolean create() {
        return false;
    }

    @Override
    protected boolean delete() {
        return false;
    }
}


