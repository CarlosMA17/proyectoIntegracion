package com.app.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Id;


public class ScrapYardPartsId  implements Serializable {
	
	private Long partId;
	private Long scrapYardId;
	public ScrapYardPartsId () {}

	

	public ScrapYardPartsId (Long partId, Long scrapYardId) {
		super();
		this.partId = partId;
		this.scrapYardId = scrapYardId;
		}


	@Override
	public int hashCode() {
		return Objects.hash(partId, scrapYardId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScrapYardPartsId  other = (ScrapYardPartsId ) obj;
		return Objects.equals(partId, other.partId) && Objects.equals(scrapYardId, other.scrapYardId);
	}	
}
