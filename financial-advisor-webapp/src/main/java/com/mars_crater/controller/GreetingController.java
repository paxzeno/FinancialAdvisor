package com.mars_crater.controller;

import com.mars_crater.data.sdk.model.ModelFacade;
import com.mars_crater.data.sdk.vo.ExpenseTypeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Greeting example using spring.
 */
@Controller
public class GreetingController {

    private ModelFacade modelFacade = new ModelFacade();

    @ModelAttribute("allExpensesTypes")
    public List<ExpenseTypeVO> getAllExpenses() {
        System.out.println("LISTING EXPENSES!!!!!");
        final List<ExpenseTypeVO> allExpenseType = modelFacade.getAllExpenseType();
        for (ExpenseTypeVO expenseTypeVO : allExpenseType) {
            System.out.println(expenseTypeVO.toString());
        }
        return allExpenseType;
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
