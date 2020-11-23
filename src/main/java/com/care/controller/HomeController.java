package com.care.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.dto.TicketDTO;
import com.care.service.TicketResultServiceImpl;
import com.care.service.TicketService;
import com.care.service.TicketServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private TicketService ts;
	
	@RequestMapping("buy_ticket")
	public String buy_ticket() {
		return "buy_ticket";
	}
	@RequestMapping("buy_ticket_card")
	public String buy_ticket_card(Model model, TicketDTO dto) {
		ts = new TicketServiceImpl();
		model.addAttribute("ticketDTO", dto);
		ts.execute(model);
		return "buy_ticket_end";
	}
	@RequestMapping("result")
	public String result(Model model) {
		ts = new TicketResultServiceImpl();
		ts.execute(model);
		return "result_ticket";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
