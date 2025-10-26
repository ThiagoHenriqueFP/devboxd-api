package io.devboxd.boxd_api.domain.user;

import io.devboxd.boxd_api.application.user.dto.GetUserDTO;
import io.devboxd.boxd_api.domain.savedPost.Saved;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

// nao precisa definir o nome da tabela, mas pode ser considerada boa pratica
@Data
@Entity
@Table(name = "users")
public class User {
    // todos os tipos armazenados no banco de dados, devems ser classes, nao tipos primitivos
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    String username;
    // por padrao todas sao nullable
    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private Boolean isActive = true;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy="user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Saved> savedPosts;

    public GetUserDTO toDTO() {
        return new GetUserDTO(this.getUsername(), this.getEmail(), this.getCreatedAt());
    }
}
