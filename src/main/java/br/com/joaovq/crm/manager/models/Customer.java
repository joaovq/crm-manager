package br.com.joaovq.crm.manager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "customer_tb")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "customer_first_name", nullable = false)
    private  String firstName;
    @Column(name = "customer_last_name", nullable = false)
    private  String lastName;
    @Column(name = "customer_cpf", unique = true, nullable = false)
    private  String cpf;
}
