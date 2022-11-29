package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.IDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        double min = sc.nextDouble();
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

    @Override
    public long stockHP(String marque) {
        Session session = sessionFactory.openSession();
        Query query =  session.createQuery("select sum(stock) from Produit where marque=:marque");
        query.setParameter("marque",marque);
        long  stockHP = (long) query.uniqueResult();

        return stockHP;
    }

    @Override
    public double moyenneProduit() {
        Session session = sessionFactory.openSession();
        double moyenneProduit = (double) session.createQuery("select avg(prix) from Produit").uniqueResult();
        return moyenneProduit;
    }

    @Override
    public List<Produit> filterMarqueTelephone() throws Exception {
        Session session = sessionFactory.openSession();
        List noms = new ArrayList<String>();
        noms.add("Samsung");
        noms.add("Sony");
        noms.add("Apple");
        noms.add("Huawai");

        Query<Produit> query =session.createQuery("from Produit where marque in :noms");
        query.setParameter("noms", noms);

        return query.list();

    }

    @Override
    public int deleteMarque(String marque) {
        Session session = sessionFactory.openSession();
        String delete_query =  "delete from Produit where marque=:marqueD";
        Query query = session.createQuery(delete_query);
        query.setParameter("marqueD",marque);
        session.getTransaction().begin();
        int success = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return success;
    }


}
