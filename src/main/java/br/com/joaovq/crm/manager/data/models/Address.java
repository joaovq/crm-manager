package br.com.joaovq.crm.manager.data.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "address_tb")
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "address_id")
    private UUID id;
    @Column(nullable = false, length = 8)
    private String cep;
    @Column(name = "public_place_address")
    private String publicPlace;
    @Column(name = "complement_address")
    private String complement;
    @Column(name = "neighborhood_address")
    private String neighborhood;
    @Column(name = "locality_address")
    private String locality;
    @Column(name = "uf")
    private String uf;
    @Column(name = "ibge_address")
    private String ibge;
    @Column(name = "gia_address")
    private String gia;
    @Column(name = "ddd_address")
    private String ddd;
    @Column(name = "siafi_address")
    private String siafi;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Address() {
    }

    public Address(UUID id, String cep, String publicPlace, String complement, String neighborhood, String locality, String uf, String ibge, String gia, String ddd, String siafi, Customer customer) {
        this.id = id;
        this.cep = cep;
        this.publicPlace = publicPlace;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.locality = locality;
        this.uf = uf;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
        this.customer = customer;
    }

    public Address(UUID id, String cep, String publicPlace, String complement, String neighborhood, String locality, String uf, String ibge, String gia, String ddd, String siafi) {
        this.id = id;
        this.cep = cep;
        this.publicPlace = publicPlace;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.locality = locality;
        this.uf = uf;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
