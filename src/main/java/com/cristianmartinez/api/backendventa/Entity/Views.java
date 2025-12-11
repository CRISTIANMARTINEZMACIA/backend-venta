package com.cristianmartinez.api.backendventa.Entity;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"viewPermissions"})
@Entity @Table(name = "vista",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_vista_nombre", columnNames = {"nombre"}),
                @UniqueConstraint(name = "uk_vista_ruta", columnNames = {"ruta"})
        })
public class Views {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vista")
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String name;

    @Column(name = "ruta", length = 255, nullable = false)
    private String route;

    @Column(name = "icono", length = 50)
    private String icon;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "padre", nullable = true)
    private Views father;

    @Column(name = "activo")
    private Boolean active;

    @OneToMany(mappedBy = "views", fetch = FetchType.LAZY)
    private ViewPermission[] viewPermissions;
}
