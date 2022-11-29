package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.IDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProduitService implements IDAO<Produit> {

    private StandardServiceRegistry registre;

    private SessionFactory sessionFactory;

    public ProduitService(){
        registre = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();
    }

    @Override
    public boolean create(Produit o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Produit o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Produit o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Produit findById(int id) {
        Produit produit = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        produit = (Produit)session.get(Produit.class, id);
        session.getTransaction().commit();
        return produit;
    }

    @Override
    public List<Produit> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Produit> productsQuery = session.createQuery("from Produit");
        session.getTransaction().commit();
        return productsQuery.list();
    }

    @Override
    public List<Produit> filterByPrice() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Valeur");
        int min = sc.nextInt();
        if(min>=0){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<Produit> productsQuery1 = session.createQuery("SELECT id, reference from Produit where stock <= :min");
            productsQuery1.setParameter("min",min);
            session.getTransaction().commit();
            return productsQuery1.list();
        }throw  new Exception("error value");


    }

    @Override
    public List<Produit> filterByDate() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Date 1 Format:dd/MM/YYYY:");
        String madate = sc.nextLine();
        Date date = new SimpleDateFormat("dd/MM/YYYY").parse(madate);

        System.out.println("Date 2 Format:dd/MM/YYYY:");
        String madate1 = sc.nextLine();
        Date date1 = new SimpleDateFormat("dd/MM/YYYY").parse(madate1);

        if(date.before(date1)){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<Produit> productsQuery2 = session.createQuery("from Produit where dateachat between :date and :date1");
            productsQuery2.setParameter("date",date);
            productsQuery2.setParameter("date1",date1);
            session.getTransaction().commit();
            return productsQuery2.list();
        } throw new Exception("error date");
    }
}
