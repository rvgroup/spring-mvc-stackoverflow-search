package model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SOQuery {
	public String title;
	
	public String link;
	
	@JsonProperty(value = "is_answered")
	public Boolean isAnswered;
	
	@JsonProperty(value = "answer_count")
	public int answerCount;
	
	@JsonProperty(value = "creation_date")
	public Date creationDate;
	
	public Owner owner;
}
