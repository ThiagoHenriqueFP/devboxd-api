package io.devboxd.boxd_api.domain.comments;

import io.devboxd.boxd_api.domain.content.Content;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "comments")
public class Comment extends Content {

    @Override
    protected boolean create() {
        return false;
    }

    @Override
    protected boolean delete() {
        return false;
    }
}
