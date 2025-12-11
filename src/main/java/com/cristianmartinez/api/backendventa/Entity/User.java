package com.cristianmartinez.api.backendventa.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"sales", "inventoryMovements"})
@Entity 
@Table(name = "usuario",
        uniqueConstraints = {@UniqueConstraint(name = "uk_usuario_email", columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String name;

    @Column(name = "apellido", length = 100)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_negocio", nullable = false)
    private Business business;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Sale[] sales;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private InventoryMovement[] inventoryMovements;
}
