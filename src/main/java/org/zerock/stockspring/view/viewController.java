package org.zerock.stockspring.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class viewController {

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
        return "dailyview";
    }


}
