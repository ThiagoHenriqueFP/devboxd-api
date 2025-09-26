package io.devboxd.boxd_api.domain.post;

import io.devboxd.boxd_api.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // List<Comments> comments
    // Like like

    @ManyToOne
    private User user;
}
