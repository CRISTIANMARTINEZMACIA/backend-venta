package com.cristianmartinez.api.backendventa.Entity;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"sales"})
@Entity @Table(name = "cliente")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String name;

    @Column(name = "apellido", length = 100)
    private String lastName;

    @Column(name = "telefono", length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_negocio", nullable = false)
    private Business business;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Sale[] sales;
}
