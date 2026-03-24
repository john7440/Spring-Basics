package fr.fms.ihm;

import fr.fms.business.ShopService;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 *  Represents the console menu itself
 */
@Component
public class ShopIHM {

    @Autowired
    private ShopService shopService;

    private final Scanner scanner = new Scanner(System.in);

    public void start(){
        int choice = -1;
        while(choice!=12){
            printMenu();
            choice = readInt();
            switch(choice){
                case 1 -> showAllArticles();
                case 2 -> showAllArticlesByPage();
                case 3 -> addArticle();
                case 4 -> showArticle();
                case 5 -> deleteArticle();
                case 6 -> updateArticle();
                case 7 -> addCategory();
                case 8 -> showCategory();
                case 9 -> deleteCategory();
                case 10 -> updateCategory();
                case 11 -> showAllArticlesByCategory();
                case 12 -> System.out.println("\nAu revoir !");
                default -> System.out.println("\nChoix invalide, veuillez réessayer...");
            }
        }
    }
    //---------------------------------------------------------------------------------

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

    /**
     * Show all articles presents in the table and format the result
     * to be more readable
     */
    private void showAllArticles(){
        List<Article> articles = shopService.getAllArticles();
        printArticlesList(articles);
    }
    //-------------------------------------------------------------------------

    /**
     *  The formatting of the list of articles
     * @param articles
     */
    private void printArticlesList(List<Article> articles) {
        if (articles.isEmpty()) {
            System.out.println("Aucun article trouvé.");
            return;
        }
        System.out.printf("%-5s %-15s %-20s %-10s %-15s%n",
                "ID", "MARQUE", "DESCRIPTION", "PRIX", "CATÉGORIE");
        System.out.println("-".repeat(70));
        for (Article a : articles) {
            System.out.printf("%-5d %-15s %-20s %-10.2f %-15s%n",
                    a.getId(),
                    a.getBrand(),
                    a.getDescription(),
                    a.getPrice(),
                    a.getCategory() != null ? a.getCategory().getName() : "N/A");
        }
        System.out.println("-".repeat(70));
    }
    //-------------------------------------------------------------------------------

    /**
     * Represents the current page when pagination mode is used
     * @param page
     * @param currentPage
     */
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

    /**
     *  Display the commands for the pagination menu
     */
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
    //--------------------------------------------------------------------

    /**
     *  Method to add an article to the database
     */
    private void addArticle(){
        String brand = readString("Marque: ");
        String description = readString("Description: ");
        double price = readDouble("Prix: ");
        showAllCategories();
        Long categoryId = readLong("Id de la catégorie: ");
        Category category = shopService.getCategoryById(categoryId);
        if (category==null){
            System.out.println("Catégorie introuvable");
            return;
        }
        Article article = new Article(brand,description,price,category);
        shopService.saveArticle(article);
        System.out.printf("Article %s ajouté", article.getDescription());
    }
    //------------------------------------------------------------------------

    /**
     *  Method to show a specific article (with id)
     */
    private void showArticle(){
        Long id = readLong("\nId de l'article: ");
        Article article = shopService.getArticleById(id);
        if (article==null){
            System.out.println("\nArticle introuvable");
        }else {
            System.out.println(article);
        }
    }
    //--------------------------------------------------------------------------

    /**
     *  Method to update an article (if it exists) in the database
     */
    private void updateArticle() {
        showAllArticles();
        Long id = readLong("ID de l'article à modifier : ");
        Article a = shopService.getArticleById(id);
        if (a == null) { System.out.println("Article introuvable !"); return; }
        System.out.println("Article actuel : " + a);
        a.setBrand(readString("Nouvelle marque (" + a.getBrand() + ") : "));
        a.setDescription(readString("Nouvelle description (" + a.getDescription() + ") : "));
        a.setPrice(readDouble("Nouveau prix (" + a.getPrice() + ") : "));
        shopService.saveArticle(a);
        System.out.println("\nArticle mis à jour !");
    }
    //-------------------------------------------------------------------------------

    /**
     *  Method to delete an article from the database
     */
    private void deleteArticle(){
        showAllArticles();
        Long id = readLong("\nId de l'article à supprimer: ");
        if (shopService.deleteArticleById(id)){
            System.out.println("\nArticle supprimé");
        }
        else{
            System.out.println("\nArticle introuvable");
        }
    }
    //------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------categories-----------------------------------

    /**
     * Method to show all categories (id + name)
     */
    private void showAllCategories() {
        System.out.println("\n");
        shopService.getAllCategories().forEach(c ->
                System.out.println(c.getId() + " - " + c.getName()));
    }
    //----------------------------------------------------------------------

    /**
     *  Method to add a category to the database
     */
    private void addCategory(){
        String name =  readString("\nNom de la catégorie : ");
        Category category = new Category(name);
        shopService.saveCategory(category);
        System.out.println("\nCatégorie ajoutée");
    }
    //---------------------------------------------------------------------

    /**
     *  Method to show a specific category (by id)
     */
    private void showCategory(){
        Long id = readLong("\nId de la catégorie : ");
        Category category = shopService.getCategoryById(id);
        if (category==null){
            System.out.println("\nCatégorie introuvable !");
        }else  {
            System.out.println(category);
        }
    }
    //-----------------------------------------------------------------------

    /**
     *  Method to update an already existing category
     */
    private void updateCategory(){
        showAllCategories();
        Long id = readLong("Id de la catégorie à modifier: ");
        Category category = shopService.getCategoryById(id);
        if (category==null){
            System.out.println("Catégorie non trouvée");
            return;
        }
        category.setName(readString("Nouveau nom (" + category.getName() + ") : "));
        shopService.saveCategory(category);
        System.out.println("Catégorie mise à jour !");

    }
    //--------------------------------------------------------------

    /**
     *  Method to delete a category (by id)
     */
    private void deleteCategory(){
        showAllCategories();
        Long id = readLong("Id de la catégorie a supprimée:");
        Category category = shopService.getCategoryById(id);
        if (category==null){
            System.out.println("Catégorie introuvable");
        }else {
            shopService.deleteCategoryById(category.getId());
        }
    }
    //------------------------------------------------------------

    /**
     *  Method to display all articles in the chosen category (by id)
     */
    private void showAllArticlesByCategory() {
        showAllCategories();
        Long id = readLong("Id de la catégorie: \n");
        List<Article> articles = shopService.getArticlesByCategory(id);
        if (articles.isEmpty()){
            System.out.println("\nAucun article dans cette catégorie");
        } else {
            articles.forEach(System.out::println);
        }
    }
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-------------------------------utilities------------------------------

    /**
     *  Utility method to valid an Int input
     * @return int/-1
     */
    private int readInt() {
        System.out.print("Votre choix : ");
        try {
           return Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e) {
            return  -1; }
        }
    //-----------------------------------------------------------

    /**
     *  Utility method to valid a String input
     * @return String/''
     */
    private String readString(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextLine();
        }
        catch (Exception e) {
            return  "";
        }
    }
    //--------------------------------------------------------------

    /**
     *  Utility method to valid a Double input
     * @return double/ 0.0
     */
    private double readDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine());
        }
        catch (Exception e) {
            return  0.0;
        }
    }
    //---------------------------------------------------------------

    /**
     *  Utility method to valid a Long input
     * @return long/-1L
     */
    private Long readLong(String prompt) {
        System.out.print(prompt);
        try {
            return Long.parseLong(scanner.nextLine());
        }
        catch (Exception e) {
            return  -1L;
        }
    }

    }

