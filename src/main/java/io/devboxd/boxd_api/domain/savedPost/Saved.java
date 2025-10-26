package io.devboxd.boxd_api.domain.savedPost;

import io.devboxd.boxd_api.domain.post.Post;
import io.devboxd.boxd_api.domain.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "saved_posts")
public class Saved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Post post;

    @CreatedDate
    private LocalDateTime createdDate;
}
