package com.itrainu.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtility {

	public void forward(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		
	}
	
	@SuppressWarnings("rawtypes")
	public static List getList (HttpServletRequest request){
		return (List) request.getAttribute("List");
		
	}
		
	}

