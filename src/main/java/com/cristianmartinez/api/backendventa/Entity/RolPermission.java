package com.cristianmartinez.api.backendventa.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString(exclude = { "rol", "viewPermission" })
@Entity
@Table(name = "rol_permiso", uniqueConstraints = {
        @UniqueConstraint(name = "uk_rol_vistapermiso", columnNames = { "id_rol", "id_vista_permiso" }) })
public class RolPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_permiso")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @Column(name = "leer")
    private Boolean read;
    @Column(name = "escribir")
    private Boolean write;
    @Column(name = "actualizar")
    private Boolean update;
    @Column(name = "eliminar")
    private Boolean delete;
    @Column(name = "imprimir")
    private Boolean print;
    @Column(name = "lider")
    private Boolean lead;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vista_permiso", nullable = false)
    private ViewPermission viewPermission;
}
