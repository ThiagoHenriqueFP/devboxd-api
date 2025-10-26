package io.devboxd.boxd_api.domain.user.role;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
