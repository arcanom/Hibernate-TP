package org.example;

import org.example.entities.Produit;
import org.example.services.ProduitService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
        List<Produit> produits = ps.filterByDate();
        for (Produit p :  produits){
            System.out.println(p);
        }

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

    }
    }