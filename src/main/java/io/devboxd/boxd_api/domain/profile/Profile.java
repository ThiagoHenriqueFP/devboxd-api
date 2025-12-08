package io.devboxd.boxd_api.domain.profile;

import io.devboxd.boxd_api.application.profile.dto.ProfileResponseDTO;
import io.devboxd.boxd_api.domain.post.Post;
import io.devboxd.boxd_api.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
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
   @JoinColumn(unique = true, nullable = false)
   private User user;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
           name = "profile_follow",
           joinColumns = @JoinColumn(name = "follower_id"),
           inverseJoinColumns =  @JoinColumn(name = "following_id")
   )
   private List<Profile> following;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
           name = "profile_follow",
           joinColumns = @JoinColumn(name = "following_id"),
           inverseJoinColumns =  @JoinColumn(name = "follower_id")
   )
   private List<Profile> followers;

   @OneToMany(fetch = FetchType.LAZY)
   private List<Post> posts;

   public ProfileResponseDTO toDto() {
       return new ProfileResponseDTO(
               this.getPhoto(),
               this.getFirstName(),
               this.getLastName(),
               this.getBio()
       );
   }

}
