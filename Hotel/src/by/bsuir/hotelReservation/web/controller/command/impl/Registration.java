package by.bsuir.hotelReservation.web.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.bsuir.hotelReservation.domain.User;
import by.bsuir.hotelReservation.service.ServiceFactory;
import by.bsuir.hotelReservation.service.UserService;
import by.bsuir.hotelReservation.service.exception.ServiceException;
import by.bsuir.hotelReservation.web.controller.command.Command;

public class Registration implements Command {
	private static final String LOGIN= "login";
	private static final String PASSWORD = "password";
	private static final String USER = "user";
	private static final String TAKE_ALL_JSP = "WEB-INF/jsp/viewAllRoom.jsp";
	private static final String ROLE= "role";
	private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=UTF-8";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE_FAIL_REGISTRATION= "The user with such login already exists";
	private static final String MESSAGE_TECHNICAL_PROBLEMS= "Sorry,technical problems";
	private static final String REGISTRATION_JSP = "WEB-INF/jsp/registration.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String login=request.getParameter(LOGIN);
		String password=request.getParameter(PASSWORD);
			
		ServiceFactory factory=ServiceFactory.getInstance();
		UserService userService=factory.getUserService();
				
		String page;
		response.setContentType(CONTENT_TYPE_TEXT_HTML);
		User user = null;
		try {
			user = userService.register(login, password);
			if (user!=null){
				String role=String.valueOf(user.getAccessRights());
						
				HttpSession session=request.getSession(true);
						
				session.setAttribute(ROLE, role);
				session.setAttribute(LOGIN,login);
				request.setAttribute(USER , user);
						
				page=TAKE_ALL_JSP;
				RequestDispatcher dispatcher=request.getRequestDispatcher(page);
				
				dispatcher.forward(request, response);
						
			}else{
				request.setAttribute(ERROR_MESSAGE, MESSAGE_FAIL_REGISTRATION);
				page = REGISTRATION_JSP;

				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR_MESSAGE,MESSAGE_TECHNICAL_PROBLEMS);	
			page = REGISTRATION_JSP;

			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
						
	}

}

