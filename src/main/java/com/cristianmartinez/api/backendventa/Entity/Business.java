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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "salePoints", "users", "categories", "products", "clients", "sales" })
@Entity
@Table(name = "negocio", uniqueConstraints = {
        @UniqueConstraint(name = "uk_negocio_ruc_nit", columnNames = { "ruc_nit" }) })
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_negocio")
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String name;

    @Column(name = "tipo_negocio", length = 50, nullable = false)
    private String typeBusiness;

    @Column(name = "direccion", length = 255)
    private String direction;

    @Column(name = "telefono", length = 20)
    private String phone;

    @Column(name = "ruc_nit", length = 50)
    private String rucNit;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<PointSale> salePoints;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<Category> categories;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<Client> clients;

}
