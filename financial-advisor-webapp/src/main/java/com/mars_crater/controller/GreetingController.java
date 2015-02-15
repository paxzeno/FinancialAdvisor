package com.mars_crater.controller;

import com.mars_crater.data.sdk.model.ModelFacade;
import com.mars_crater.data.sdk.vo.ExpenseTypeVO;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(GreetingController.class);

    private ModelFacade modelFacade = new ModelFacade();

    @ModelAttribute("allExpensesTypes")
    public List<ExpenseTypeVO> getAllExpenses() {
        LOGGER.debug("LISTING EXPENSES!!!!!");
        final List<ExpenseTypeVO> allExpenseType = modelFacade.getAllExpenseType();
        for (ExpenseTypeVO expenseTypeVO : allExpenseType) {
            LOGGER.debug(expenseTypeVO.toString());
        }
        return allExpenseType;
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
