package by.bsuir.hotelReservation.web.controller.command.impl;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.hotelReservation.web.controller.command.Command;


public class ShowRegistrationForm implements Command {

	private static final String REGISTRATION_FORM_JSP ="WEB-INF/jsp/registration.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			RequestDispatcher dispatcher=request.getRequestDispatcher(REGISTRATION_FORM_JSP);
			dispatcher.forward(request, response);
		}
	}