package com.cristianmartinez.api.backendventa.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Todas las entidades JPA mapeadas desde el SQL provisto.
 *
 * Notas:
 * - Estrategia IDENTITY para compatibilidad con "GENERATED ALWAYS AS IDENTITY" (PostgreSQL).
 * - Relaciones y restricciones únicas replicadas con anotaciones JPA.
 * - Se usan BigDecimal para valores monetarios y de cantidad.
 * - @CreationTimestamp para fechas por defecto CURRENT_TIMESTAMP.
 */

// =======================
// 1. GESTIÓN DE USUARIOS
// =======================

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
    private User[] users;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private RolPermission[] rolPermissions;
}

