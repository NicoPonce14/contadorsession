package com.nicolas.contador.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class CountController {
	
	private void setContadorSession(HttpSession sesion, int nVeces) {
		sesion.setAttribute("contador", nVeces);
	}
	
	private int getContadorSession(HttpSession sesion) {
		Object sesionVal = sesion.getAttribute("contador");
		if(sesionVal ==null) {
			setContadorSession(sesion,0);
			sesionVal = sesion.getAttribute("contador");
		}
		return (Integer) sesionVal;
	}
	
	@RequestMapping("/")
	public String index(HttpSession sesion) {
		int conteoActual = getContadorSession(sesion);
		setContadorSession(sesion,conteoActual+1);
		return "index.jsp";
	}
	
	@RequestMapping("/counter")
	public String counter(HttpSession sesion,Model m) {
		m.addAttribute("contador",getContadorSession(sesion));
		return "counter.jsp";
	}
	
	@RequestMapping("/reset")
	public String reset(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/counter";
	}
}
