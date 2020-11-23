package com.care.service;

import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.care.dao.TicketDAO;
import com.care.dto.TicketDTO;

public class TicketServiceImpl implements TicketService{
	private TicketDAO dao;
	public TicketServiceImpl() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		dao = ctx.getBean("dao", TicketDAO.class); // applicationJDBC.xml파일에서 dao를 이미 선언함
	}
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		TicketDTO dto = (TicketDTO)map.get("ticketDTO");
		int arr[] = new int[2];
		arr = dao.buyTicket(dto);
		model.addAttribute("arr", arr);
	}
}
