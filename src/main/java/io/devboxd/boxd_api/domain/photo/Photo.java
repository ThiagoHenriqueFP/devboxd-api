package io.devboxd.boxd_api.domain.photo;

import io.devboxd.boxd_api.domain.post.Post;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "photos")
public class Photo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    private String url;

    @CreatedDate
    private LocalDateTime createdAt;
}
