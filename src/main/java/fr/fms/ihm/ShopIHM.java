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

}
