package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import helper.HttpHelper;
import model.SOResponse;

@Controller
public class MainController {
	@GetMapping(value = "/")
	protected ModelAndView handleRequestInternalGet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView model = new ModelAndView("Index");
		
		return model;
	}
	
	@PostMapping(value = "/")
	protected @ResponseBody SOResponse handleRequestInternalPost(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String filtertext = request.getParameter("filtertext");
		
		String url = this.getUrl(filtertext);
		String urlResponse = HttpHelper.getUrlResponse(url);
		
		SOResponse respModel = this.getResponseModel(urlResponse);
		
		return respModel;
	}
	
	private SOResponse getResponseModel(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		SOResponse model = mapper.readValue(json, SOResponse.class);
		
		return model;
	}
	
	private String getUrl(String filtertext) {
		////http://api.stackexchange.com/2.2/search?page=1&pagesize=100&order=desc&sort=activity&intitle=java&site=stackoverflow
		return String.format(
				"http://api.stackexchange.com/2.2/search?order=desc&sort=activity&site=stackoverflow&intitle=%s",
				filtertext);
	}
	
	
}