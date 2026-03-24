package fr.fms.ihm;

import fr.fms.business.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShopIHM {

    @Autowired
    private ShopService shopService;

    private Scanner scanner = new Scanner(System.in);

    public void start(){
        int choice = -1;
    }

    private void printMenu(){
        System.out.println("\nBienvenu dans notre application de gestion d'articles !");
        System.out.println("1: Afficher tous les articles sans pagination");
        System.out.println("2: Afficher tous les articles avec pagination");
        System.out.println("***************************");
        System.out.println("3: Ajouter un article");
        System.out.println("4: Afficher un article");
        System.out.println("5: Supprimer un article");
        System.out.println("6: Modifier un article");
        System.out.println("***************************");
        System.out.println("7: Ajouter une categorie");
        System.out.println("8: Afficher une categorie");
        System.out.println("9: Supprimer une categorie");
        System.out.println("10: Mettre a jour une categorie");
        System.out.println("11: Afficher tous les articles d'une categorie");
        System.out.println("***************************");
        System.out.println("12: Sortir du programme");
    }

}
