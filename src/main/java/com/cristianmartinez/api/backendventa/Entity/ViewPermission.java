package com.cristianmartinez.api.backendventa.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
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

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"views", "permission", "rolPermissions"})
@Entity @Table(name = "vista_permiso",
        uniqueConstraints = {@UniqueConstraint(name = "uk_vista_permiso", columnNames = {"id_vista", "id_permiso"})})
public class ViewPermission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vista_permiso")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vista", nullable = false)
    private Views views;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso", nullable = false)
    private Permission permission;

    @Column(name = "leer") private Boolean read;
    @Column(name = "escribir") private Boolean write;
    @Column(name = "actualizar") private Boolean update;
    @Column(name = "eliminar") private Boolean delete;
    @Column(name = "imprimir") private Boolean print;
    @Column(name = "lider") private Boolean lead;

    @OneToMany(mappedBy = "viewPermission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RolPermission> rolPermissions;
}

