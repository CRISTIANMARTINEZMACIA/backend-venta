package com.cristianmartinez.api.backendventa.Entity;

import java.math.BigDecimal;
import java.util.List;

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

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
@Entity @Table(name = "stock",
        uniqueConstraints = {@UniqueConstraint(name = "uk_stock_producto", columnNames = {"id_producto"})})
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Product product;

    @Column(name = "cantidad_actual", precision = 10, scale = 3, nullable = false)
    private BigDecimal amountCurrent;

    @Column(name = "unidad_medida", length = 20, nullable = false)
    private String unitMeasure;

    @Column(name = "stock_minimo", precision = 10, scale = 3)
    private BigDecimal stockMin;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
    private List<InventoryMovement> movements;
}
