package org.example;

import jdk.swing.interop.SwingInterOpUtils;
import org.example.entities.Commentaire;
import org.example.entities.Image;
import org.example.entities.Produit;
import org.example.services.ProduitService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // Exercice 1

        // Creation des produits
        ProduitService ps = new ProduitService();
//        ps.create(new Produit("TOSHIBA","zzaa123",new Date("2016/01/08"), 6000,555));
//        ps.create(new Produit("HP","EER678",new Date("2016/02/09"), 2000,444));
//        ps.create(new Produit("SONY VAIO","AQWZSX",new Date("2016/09/23"), 6000,333));
//        ps.create(new Produit("DELL","AZERTY",new Date("2016/02/12"), 6000,222));
//        ps.create(new Produit("SONY","qsdERT",new Date("2016/02/02"), 6000,111));
//
//        // informations produit id = 2
//        Produit p = ps.findById(2);
//        System.out.println(p.getId()+ " , "+p.getMarque()+" , "+p.getReference());
//
//        // supprimer le produit dont id = 3
//            ps.delete(ps.findById(3));
//
//        // Modifier les informations du produit dont id =1
//        p = ps.findById(1);
//        if(p != null) {
//            p.setMarque("HP");
//            p.setReference("MMMMMPPPP");
//            p.setDateAchat(new Date("2015/09/08"));
//            p.setPrix(50000);
//            ps.update(p);
//        }
//
//        List<Produit> produits=  ps.findAll();
//        for (Produit p : produits){
//            System.out.println(p);
//        }
//
//        List<Produit> produits1= ps.filterByPrice(100);
//        for (Produit p : produits1){
//            System.out.println(p);
//        }

//        String madate="08/08/2016";
//        String madate2= "09/10/2017";
//        Date date = new SimpleDateFormat("dd/MM/YYYY").parse(madate);
//        Date date2 = new SimpleDateFormat("dd/MM/YYYY").parse(madate2);
//        List<Produit> produits2 = ps.filterByDate(date,date2);
//        for (Produit p : produits2){
//            System.out.println(p);
//        }


        //1
//        List<Produit> produits = ps.filterByDate();
//        for (Produit p :  produits){
//            System.out.println(p);
//        }

        // 2
//        List result= ps.filterByPrice();
//        List<Produit> produits = new ArrayList<>();
//        for (Object o: result){
//            Object[] res = (Object[]) o;
//            Produit produit = new Produit();
//            produit.setId((int)res[0]);
//            produit.setReference((String)res[1]);
//            produits.add(produit);
//        }
//        for(Produit p: produits){
//            System.out.println(p.getId());
//            System.out.println(p.getReference());
//        }

//        //1
//      long result = ps.stockHP();
//      System.out.println("Somme stock HP " +  result);
//
//      //2
//      double moyenne = ps.moyenneProduit();
//        System.out.println("Moyenne " + moyenne);
//
//        //3
//      List<Produit> produits = ps.filterMarqueTelephone();
//      for (Produit p: produits){
//          System.out.println(p);
//      }
//    //4
//      int result1 = ps.deleteMarque();
//        System.out.println(result1);

        System.out.println("1- Stock des produits d'une marque");
        System.out.println("2- Prix Moyen des produits");
        System.out.println("3-  Liste des marques de téléphone");
        System.out.println("4- Supprimer les produits d'une marque ");
        System.out.println("5- Ajouter un commentaire à un produit");
        System.out.println("6- Ajouter une image à un produit");
        System.out.println("7- Afficher les produits avec une note de 4 ");
        System.out.println("0- Exit");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        while(choix != 0){
            switch (choix){
                case 1:
                    System.out.println("Le nom de la marque");
                    String marque = sc.next();
                    double stock = ps.stockHP(marque);
                    System.out.println("Somme stock HP " +  stock);
                    break;
                case 2:
                    double moyenne = ps.moyenneProduit();
                    System.out.println("Moyenne " + moyenne);
                    break;
                case 3:
                    List noms = new ArrayList<String>();
                    
                    noms.add("Samsung");
                    noms.add("Sony");
                    noms.add("Apple");
                    noms.add("Huawai");

                    List<Produit> produits = ps.filterMarqueTelephone(noms);
                     for (Produit p: produits){
                         System.out.println(p);
                         }
                     break;
                case 4:
                    System.out.println("Le nom de la marque");
                    String marque1 = sc.next();
                    int result1 =  ps.deleteMarque(marque1);
                    System.out.println(result1);
                    break;
                case 5:
                    System.out.println("Id du produit");
                    int idCommentaire = sc.nextInt();
                    System.out.println("Contenu du commentaire");
                    sc.nextLine();
                    String contenu = sc.nextLine();
                    System.out.println("date du commentaire (Format:dd/mm/yyyy)");
                    String date = sc.next();
                    Date convertDate = new SimpleDateFormat("dd/mm/yyyy").parse(date);
                    System.out.println("Note du commentaire");
                    int note = sc.nextInt();
                    Commentaire c = new Commentaire();

                    c.setContenu(contenu);
                    c.setDate(convertDate);
                    c.setNote(note);
                    ps.addCommentaire(c,idCommentaire);
                    break;
                case 6:
                    System.out.println("Id du produit");
                    int idImage = sc.nextInt();
                    System.out.println("URL de l'image");
                    String url = sc.next();
                    Image i = new Image();
                    i.setUrl(url);
                    ps.addImage(i,idImage);
                    break;
                case 7:
                        List<Produit> produits1 =  ps.filterProduitNoteQuatre();
                        for (Produit p: produits1){

                            System.out.println(p);
                        }
                        break;

                case 0:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Choix incorrect");
            }
            System.out.println("1- Stock des produits d'une marque");
            System.out.println("2- Prix Moyen des produits");
            System.out.println("3-  Liste des marques de téléphone");
            System.out.println("4- Supprimer les produits d'une marque ");
            System.out.println("5- Ajouter un commentaire à un produit");
            System.out.println("6- Ajouter une image à un produit");
            System.out.println("7- Afficher les produits avec une note de 4 ");
            System.out.println("0- Exit");
            choix = sc.nextInt();
        }

    }
    }