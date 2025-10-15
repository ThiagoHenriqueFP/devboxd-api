package io.devboxd.boxd_api.domain.comments;

import io.devboxd.boxd_api.domain.content.Content;
import io.devboxd.boxd_api.domain.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "comments")
public class Comment extends Content {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    protected boolean create() {
        return false;
    }

    @Override
    protected boolean delete() {
        return false;
    }
}
