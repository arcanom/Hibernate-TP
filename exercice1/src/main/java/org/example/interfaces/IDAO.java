package org.example.interfaces;

import org.example.entities.Commentaire;
import org.example.entities.Image;
import org.example.entities.Produit;

import java.util.Date;
import java.util.List;

public interface IDAO<T> {

    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    T findById(int id);

    List<T> findAll();
    List<T>filterByPrice() throws Exception;

    List<T>filterByDate() throws Exception;

    double stockHP(String marque) ;

    double moyenneProduit();

    List<T> filterMarqueTelephone(List noms) throws Exception;

    int deleteMarque(String marque);

    void addImage(Image i, int id);

    void addCommentaire(Commentaire c, int id);

    List<T>  filterProduitNoteQuatre();
}
