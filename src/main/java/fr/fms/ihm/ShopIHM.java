package fr.fms.ihm;

import fr.fms.business.ShopService;
import fr.fms.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ShopIHM {

    @Autowired
    private ShopService shopService;

    private Scanner scanner = new Scanner(System.in);

    public void start(){
        int choice = -1;
        while(choice!=12){
            printMenu();
            choice = readInt("Votre choix : ")
            switch(choice){
                case 1 -> showAllArticles();
                case 2 -> showAllArticlesByPage();
                case 12 -> System.out.println("\nAu revoir !");
                default -> System.out.println("\nChoix invalide, veuillez réessayer...");
            }
        }
    }

    private void printMenu(){
        System.out.println("\nBienvenu dans notre application de gestion d'articles !");
        System.out.println("1: Afficher tous les articles sans pagination");
        System.out.println("2: Afficher tous les articles avec pagination");
        System.out.println("****************************");
        System.out.println("3: Ajouter un article");
        System.out.println("4: Afficher un article");
        System.out.println("5: Supprimer un article");
        System.out.println("6: Modifier un article");
        System.out.println("***************************");
        System.out.println("7: Ajouter une catégorie");
        System.out.println("8: Afficher une catégorie");
        System.out.println("9: Supprimer une catégorie");
        System.out.println("10: Mettre a jour une catégorie");
        System.out.println("11: Afficher tous les articles d'une catégorie");
        System.out.println("******************************");
        System.out.println("12: Sortir du programme\n\n");
    }

    //-----------------------------------------------------------------------------
    //------------------------------articles-------------------------------------
    private void showAllArticles(){
        List<Article> articles = shopService.getAllArticles();
        articles.forEach(System.out::println);
    }
    //-------------------------------------------------------------------------
    private void printPage(Page<Article> page,int currentPage){
        System.out.printf("%-5s %-20s %-15s %-10s %-15s%n",
                "ID", "DESCRIPTION", "MARQUE", "PRIX", "CATÉGORIE");
        System.out.println("-".repeat(70));
        page.getContent().forEach(a -> System.out.printf("%-5d %-20s %-15s %-10.2f %-15s%n",
                a.getId(), a.getDescription(), a.getBrand(), a.getPrice(),
                a.getCategory() != null ? a.getCategory().getName() : "N/A"));
        System.out.println();
        System.out.printf("PREV [ ");
        for (int i = 0; i < page.getTotalPages(); i++) {
            System.out.printf(i == currentPage ? "{%d} " : "%d ", i);
        }
        System.out.printf("] NEXT%n");
    }
    //----------------------------------------------------------------------
    private void printPageMenu() {
        System.out.println("\nEXIT pour sortir de la pagination");
        System.out.println("PREV pour afficher la page précédente");
        System.out.println("NEXT pour afficher la page suivante");
        System.out.println("PAGE puis 7 pour afficher 7 articles par page (par défaut c'est 5)\n\n");
    }
    //------------------------------------------------------------------------
    private void showAllArticlesByPage() {
        int size = 5;
        int currentPage= 0;
        String cmd = "";

        while (!cmd.equalsIgnoreCase("EXIT")) {
            Page<Article> page = shopService.getAllArticlesByPage(currentPage, size);
            printPage(page, currentPage);

            System.out.println("EXIT | PREV | NEXT | PAGE x | MENU");
            cmd = scanner.nextLine().trim().toUpperCase();

            if (cmd.equals("NEXT")) {
                if (currentPage < page.getTotalPages() - 1) currentPage++;
                else System.out.println("\nVous êtes déjà à la dernière page");
            } else if (cmd.equals("PREV")) {
                if (currentPage > 0) currentPage--;
                else System.out.println("\nVous êtes déjà à la première page");
            } else if (cmd.startsWith("PAGE")) {
                try {
                    size = Integer.parseInt(cmd.split(" ")[1]);
                    currentPage = 0;
                } catch (Exception e) {
                    System.out.println("\nFormat invalide !Ex: PAGE 7");
                }
            } else if (cmd.equals("MENU")) {
                printPageMenu();
            }
        }
    }
    //-----------------------------------------------------------------------
    //-------------------------------utility------------------------------
    private int readInt(String prompt) {
        System.out.print(prompt);
        try {
           return Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e) {
            return  -1; }
        }

    }

