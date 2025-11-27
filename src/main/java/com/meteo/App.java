package com.meteo;
import com.meteo.model.Meteo;
import com.meteo.service.MeteoService;
import java.io.IOException;
import java.util.Scanner;
public class App {
    public static void main( String[] args ){
        MeteoService meteoService=new MeteoService();
        Scanner scanner=new Scanner(System.in);
        System.out.println("========bienvenue sur meteoCup==========");
        while (true){
            System.out.println("donner le nom de la ville/ecrire quit ou exit pour quitter/n");
            String city=scanner.nextLine().trim();
            if (city.equalsIgnoreCase("quit")||city.equalsIgnoreCase("exit")){
                System.out.println("Fermeture de l'application. À bientôt !");
                break;
            }
            if (city.isEmpty()){
                System.out.println("entrer le nom d'une ville");
                continue;
            }
            try {
                Meteo meteo=meteoService.getMeteo(city);
                System.out.println("\n" + meteo);
            } catch (IOException |InterruptedException e) {
                System.out.println("erreur reseau: impossible de contacter l'api");
            } catch (RuntimeException e) {
                System.out.println("erreur:"+ e.getMessage());
            }
            System.out.println("voulez vous rechercher une autre ville? (O/N)");
            String again=scanner.nextLine().trim();
            if (!again.equalsIgnoreCase("o") && !again.equalsIgnoreCase("oui")) {
                System.out.println("fermeture de l'application a bientot");
                break;
            }
        }
        scanner.close();
    }
}