package com.cristianmartinez.api.backendventa.Entity;

import java.math.BigDecimal;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"stock", "recipeItems", "saleDetails"})
@Entity @Table(name = "producto")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "nombre", length = 150, nullable = false)
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "precio_venta", precision = 10, scale = 2, nullable = false)
    private BigDecimal priceSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Category category; // puede ser null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_negocio", nullable = false)
    private Business business;

    @Column(name = "es_inventariable", nullable = false)
    private Boolean is_inventariable;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stock[] stock;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Recipe[] recipeItems;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private SaleDetail[] saleDetails;
}
