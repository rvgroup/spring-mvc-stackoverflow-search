package model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SOResponse {
	public List<SOQuery> items;
	
	@JsonProperty(value = "has_more")
	public Boolean hasMore;
}