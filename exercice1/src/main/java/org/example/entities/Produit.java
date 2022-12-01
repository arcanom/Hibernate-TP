package org.example.entities;


import javax.persistence.*;
import java.util.*;

@Entity
public class Produit {

    @Id
    @GeneratedValue
    private int id;

    private String marque;

    private String reference;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    private double prix;

    private int stock;

    public Produit(){

    }

    public Produit(String marque, String reference, Date dateAchat, double prix, int stock) {
        this.marque = marque;
        this.reference = reference;
        this.dateAchat = dateAchat;
        this.prix = prix;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @OneToMany(mappedBy = "produitCommentaire" )
    private List<Commentaire>  produitCommentaire = new ArrayList<>();

    public void ajouterCommentaire(Commentaire c){
        produitCommentaire.add(c);
    }

    public List<Commentaire> getProduitCommentaire() {
        return produitCommentaire;
    }

    public void setProduitCommentaire(List<Commentaire> produitCommentaire) {
        this.produitCommentaire = produitCommentaire;
    }

    public List<Image> getProduitImage() {
        return produitImage;
    }

    public void setProduitImage(List<Image> produitImage) {
        this.produitImage = produitImage;
    }

    @OneToMany(mappedBy = "produitImage")
    private List<Image> produitImage= new ArrayList<>();

    public void ajouterImage(Image i){
        produitImage.add(i);
    }

    @ManyToMany(mappedBy = "produits")
    Set<Commande> commandes = new HashSet<>();


    public Set<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "produit_id=" + id +
                ", marque='" + marque + '\'' +
                ", reference='" + reference + '\'' +
                ", dateAchat=" + dateAchat +
                ", prix=" + prix +
                ", stock=" + stock +
                '}';
    }
}
