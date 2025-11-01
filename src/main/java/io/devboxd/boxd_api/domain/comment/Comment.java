package io.devboxd.boxd_api.domain.comment;

import io.devboxd.boxd_api.domain.content.Content;
import io.devboxd.boxd_api.domain.like.Like;
import io.devboxd.boxd_api.domain.post.Post;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "comments")
public class Comment extends Content {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @Override
    protected boolean create() {
        return false;
    }

    @Override
    protected boolean delete() {
        return false;
    }
}
