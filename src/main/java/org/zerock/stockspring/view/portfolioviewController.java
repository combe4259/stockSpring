package org.zerock.stockspring.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class portfolioviewController {

    @GetMapping("/portfolioview")
    public String portfolioView(){
        return "portfolioview.html";
    }

    @GetMapping("/stockview")
    public String stockView(){
        return "stockview.html";
    }
    @GetMapping("/dailyview")
    public String dailyView(){
        return "dailyview.html";
    }


}
