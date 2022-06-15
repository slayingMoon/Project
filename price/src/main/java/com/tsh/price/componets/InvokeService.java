package com.tsh.price.componets;

import com.tsh.price.services.CityService;
import com.tsh.price.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class InvokeService {
    @Autowired
    private final PriceService priceService;

    public InvokeService(PriceService priceService) {
        this.priceService = priceService;
    }
    @PostConstruct
    public void init(){

        //priceService.addPrice("Sofia","Karlovo","22");
        //priceService.allPrices().forEach(System.out::println);
        //priceService.removePrice("Burgas","Varna");
        priceService.getPrice("Sofia","Karlovo", false,false);
    }

}
