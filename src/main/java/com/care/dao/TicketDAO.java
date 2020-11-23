package com.care.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.care.dto.TicketDTO;

public class TicketDAO {
	private JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	private TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public int[] buyTicket(TicketDTO dto) {
		String sql_user = "insert into userticket(id,ticketnum) values(?,?)";
		String sql_system = "insert into systemticket(id,ticketnum) values(?,?)";
		int arr[] = new int[2];
		try {
			// 연관된 데이터베이스 묶기
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					// 안에 있는 쿼리가 정상적으로 실행 되었을 때만 적용이 됨.
					// 에러?가 발생하면 롤백(rollback)
					arr[0] = template.update(sql_user, ps->{
						ps.setString(1, dto.getId());
						ps.setInt(2, dto.getTicketnum());
					});
					arr[1] = template.update(sql_system, ps->{
						ps.setString(1, dto.getId());
						ps.setInt(2, dto.getTicketnum());
					});
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public Map<String, ArrayList> resultTicket(){
		String sql_user = "select * from userticket";
		String sql_system = "select * from systemticket";
		Map<String, ArrayList> map = new HashMap<String, ArrayList>();
		ArrayList<TicketDTO> userticket = null;
		ArrayList<TicketDTO> systemticket = null;
		try {
			userticket = (ArrayList<TicketDTO>)template.query(sql_user
					, new BeanPropertyRowMapper<TicketDTO>(TicketDTO.class));
			systemticket = (ArrayList<TicketDTO>)template.query(sql_system,
					new BeanPropertyRowMapper<TicketDTO>(TicketDTO.class));
			map.put("userticket", userticket);
			map.put("systemticket", systemticket);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}


















