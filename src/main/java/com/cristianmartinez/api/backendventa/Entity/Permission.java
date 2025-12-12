package com.cristianmartinez.api.backendventa.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@ToString(exclude = {"viewPermission"})
@Entity @Table(name = "permiso",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_permiso_nombre", columnNames = {"nombre"}),
                @UniqueConstraint(name = "uk_permiso_endpoint", columnNames = {"endpoint_base"})
        })
public class Permission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String name;

    @Column(name = "endpoint_base", length = 255, nullable = false)
    private String endpointBase;

    @Column(name = "descripcion")
    private String description;

    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY)
    private List<ViewPermission> viewPermission;
}
