package br.unitins.tp1.irondragon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PlacaIntegrada extends PanacheEntityBase {
    @Id
    private Long id;

    private String nome;
    private Float directX;
    private Float openGl;
    private Float vulkan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getDirectX() {
        return directX;
    }

    public void setDirectX(Float directX) {
        this.directX = directX;
    }

    public Float getOpenGl() {
        return openGl;
    }

    public void setOpenGl(Float openGl) {
        this.openGl = openGl;
    }

    public Float getVulkan() {
        return vulkan;
    }

    public void setVulkan(Float vulkan) {
        this.vulkan = vulkan;
    }
}
