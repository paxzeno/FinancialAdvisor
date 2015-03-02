package com.mars_crater.controller;

import com.mars_crater.data.sdk.model.ModelFacade;
import com.mars_crater.data.sdk.vo.ExpenseTypeVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Greeting example using spring.
 */
@Controller
public class ExpenseTypeController {

    private static final Logger LOGGER = Logger.getLogger(ExpenseTypeController.class);

    private ModelFacade modelFacade = new ModelFacade();

    @ModelAttribute("allExpensesTypes")
    public ArrayList<ExpenseTypeVO> getAllExpenses() {
        LOGGER.debug("LISTING EXPENSES!!!!!");
        final ArrayList<ExpenseTypeVO> allExpenseType = (ArrayList<ExpenseTypeVO>) modelFacade.getAllExpenseType();
        for (ExpenseTypeVO expenseTypeVO : allExpenseType) {
            LOGGER.debug(expenseTypeVO.toString());
        }
        return allExpenseType;
    }

    @RequestMapping("/ExpensesTypes")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        return "ExpensesTypes";
    }

    @RequestMapping(value = "/addExpenseType", method = RequestMethod.POST)
    public String addExpenseType(HttpServletRequest request, @RequestBody final List<ExpenseTypeVO> expense, Model model) {
        return "ExpensesTypes";
    }

    @RequestMapping(value = "/submitExpensesType", method = RequestMethod.GET)
    public String submitExpensesTypeGET(@ModelAttribute("expenseTypeVO")ExpenseTypeVO expenses, Model model) {
        model.addAttribute("expenseTypeVO", new ExpenseTypeVO());
        return "expenseForm";
    }

}
