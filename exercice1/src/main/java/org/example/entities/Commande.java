package org.example.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commande_id;

    private double total;

    private String dateCommande;

    public Long getId() {
        return commande_id;
    }

    public void setId(Long id) {
        this.commande_id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }



    @ManyToMany
    @JoinTable(name="commande_produit", joinColumns = @JoinColumn(name = "commande_id"),inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private Set<Produit> produits = new HashSet<>();

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public void ajouterProduit(Produit p){this.produits.add(p);};

    public Commande(){

    }

    public Commande(double total, String dateCommande, Set<Produit> produits) {
        this.total = total;
        this.dateCommande = dateCommande;
        this.produits = produits;
    }

    public Commande(double total, String dateCommande) {
        this.total = total;
        this.dateCommande = dateCommande;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + commande_id +
                ", total=" + total +
                ", dateCommande=" + dateCommande +
                ", produits=" + produits +
                '}';
    }
}
