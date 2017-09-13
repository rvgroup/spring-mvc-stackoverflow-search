package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {
	@JsonProperty(value = "profile_image")
	public String profileImage;

	@JsonProperty(value = "display_name")
	public String displayName;
	
	public String link;
}
