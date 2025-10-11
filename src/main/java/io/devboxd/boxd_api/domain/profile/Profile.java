package io.devboxd.boxd_api.domain.profile;

import io.devboxd.boxd_api.domain.post.Post;
import io.devboxd.boxd_api.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String photo;

   @Column(nullable = false)
   private String firstName;

   private String lastName;

   private String bio;

   @CreatedDate
   private LocalDateTime createdAt;

   @OneToOne
   @Column(unique = true ,nullable = false)
   private User user;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
           name = "profile_following",
           joinColumns = @JoinColumn(name = "follower_id"),
           inverseJoinColumns =  @JoinColumn(name = "following_id")
   )
   private List<Profile> following;

   @ManyToMany(mappedBy = "profile", fetch = FetchType.LAZY)
   private List<Profile> followers;

   @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
   private List<Post> posts;

}
