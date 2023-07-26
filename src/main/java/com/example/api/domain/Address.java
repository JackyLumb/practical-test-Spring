package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Endereco")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("cep")
    @Column(nullable = false)
    private String cep;
    @JsonProperty("logradouro")
    @Column(nullable = false)
    private String logradouro;
    @JsonProperty("complemento")
    private String complemento;

    @Column(nullable = false)
    @NotBlank(message = "Número não informado")
    private String numero;
    @JsonProperty("bairro")
    @Column(nullable = false)
    private String bairro;
    @JsonProperty("localidade")
    @Column(nullable = false)
    private String localidade;
    @JsonProperty("uf")
    @Column(nullable = false)
    private String uf;

    @JsonIgnoreProperties({ "addresses" })
    @ManyToOne
    @JoinColumn(name ="customer_id", nullable = false)
    private Customer customer;


    public Address() {
    }

    public Address(String cep, String logradouro, String complemento, String numero, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }


    public Customer getCustomer(){
        return customer;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", UF='" + uf + '\'' +
                ", customer=" + (customer != null ? customer.getId() : null) +
                '}';
    }

}
