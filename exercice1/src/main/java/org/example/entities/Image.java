package org.example.entities;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id")
    Produit produitImage;

    public Produit getProduitImage() {
        return produitImage;
    }

    public void setProduitImage(Produit produitImage) {
        this.produitImage = produitImage;
    }

    public Image() {
    }
}
