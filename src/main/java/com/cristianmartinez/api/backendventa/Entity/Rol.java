package com.cristianmartinez.api.backendventa.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"rolPermissions", "users"})
@Entity 
@Table(name = "rol",
        uniqueConstraints = {@UniqueConstraint(name = "uk_rol_nombre", columnNames = {"nombre_rol"})})
public class Rol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @Column(name = "nombre_rol", length = 50, nullable = false)
    private String name;

    @Column(name = "descripcion", length = 255)
    private String description;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<RolPermission> rolPermissions;
}

