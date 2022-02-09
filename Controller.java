package com.cg.PaymentSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.PaymentSYstem.exception.AccountBalanceException;
import com.cg.PaymentSYstem.exception.AccountCreateException;
import com.cg.PaymentSYstem.exception.AccountException;
import com.cg.PaymentSystem.model.Account;
import com.cg.PaymentSystem.service.PaymentAccountService;

@Controller
public class Controller {

	@Autowired
	PaymentAccountService service;

	@RequestMapping("/home")
	public String addPage() {
		String view = "home";
		return view;
	}

	@RequestMapping("/add")
	public String addAccountScreen(Model model) {
		String view = "addAccount";
		model.addAttribute("Account", new Account());
		return view;
	}

	@RequestMapping(value = "/accountAdded", method = RequestMethod.POST)
	public String AccountIdGenerator(Model model, @ModelAttribute("Account") Account account) {
		String view = "";
		try {
			int id = service.addAccount(account);
			model.addAttribute("accountId", id);
			view = "idGenerated";
		} catch (AccountCreationException e) {
			model.addAttribute("error", e.getMessage());
			view = "errorPage";
		}
		return view;
	}

	@RequestMapping("/deposit")
	public String depositScreen() {
		String view = "depositAmount";
		return view;
	}

	@RequestMapping(value = "/moneyDeposited", method = RequestMethod.POST)
	public String moneyDeposited(Model model, @RequestParam("accountId") int accountId,
			@RequestParam("money") int money) {
		String view = "";
		try {
			int accBalance = service.depositMoney(accountId, money);
			model.addAttribute("accountBalance", accBalance);
			view = "balanceScreen";
		} catch (AccountException e) {
			model.addAttribute("error", e.getMessage());
			view = "errorPage";
		}
		return view;
	}

	@RequestMapping("/withdraw")
	public String withdrawScreen() {
		String view = "withdrawAmount";
		return view;
	}

	@RequestMapping(value = "/moneyWithdrawn", method = RequestMethod.POST)
	public String moneyWithdrawed(Model model, @RequestParam("accountId") int accountId,
			@RequestParam("money") int money) {
		String view = "";
		try {
			int accBalance = service.withdrawMoney(accountId, money);
			model.addAttribute("accountBalance", accBalance);
			view = "balanceScreen";
		} catch (AccountException e) {
			model.addAttribute("error", e.getMessage());
			view = "errorPage";
		} catch (AccountBalanceException e) {
			model.addAttribute("error", e.getMessage());
			view = "errorPage";
		}
		return view;
	}

	@RequestMapping("/transfer")
	public String transferScreen() {
		String view = "fundTransfer";
		return view;
	}

	@RequestMapping(value = "/fundTransfer", method = RequestMethod.POST)
	public String transferMoney(Model model, @RequestParam("accountId") int accountId,
			@RequestParam("receiverAccountId") int receiverId, @RequestParam("money") int money) {
		String view = "";
		try {
			String transfer = service.fundTransfer(accountId, receiverId, money);
			model.addAttribute("transferMessage", transfer);
			view = "success";
		} catch (AccountException e) {
			model.addAttribute("error", e.getMessage());
			view = "errorPage";
		} catch (AccountBalanceException e) {
			model.addAttribute("error", e.getMessage());
			view = "errorPage";
		}
		return view;
	}

	@RequestMapping("/balance")
	public String showBalanceScreen() {
		String view = "showBalance";
		return view;
	}

	@RequestMapping(value = "/availableBalance", method = RequestMethod.POST)
	public String availableBalance(Model model, @RequestParam("accountId") int accountId) {
		String view = "showAvailableBalance";
		try {
			int balance = service.showBalance(accountId);
			model.addAttribute("balance", balance);
			view = "balanceValue";
		} catch (AccountException e) {
			model.addAttribute("error", e.getMessage());
			view = "errorPage";
		}
		return view;
	}

	@RequestMapping("/details")
	public String showDetails() {
		String view = "showDetails";
		return view;
	}

	@RequestMapping("/availableDetails")
	public String availableDetails(Model model, @RequestParam("accountId") int accountId) {
		String view = "";
		try {
			Account acc = service.showDetails(accountId);
			model.addAttribute("accountDetails", acc);
			view = "userData";
		} catch (AccountException e) {
			model.addAttribute("error", e.getMessage());
			view = "errorPage";
		}
		return view;
	}
}