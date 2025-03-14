package com.app.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Id;


public class PartId implements Serializable {
	
	private Long partId;
	private Long scrapYardId;
	
	public PartId() {}

	public PartId(Long partId, Long fkScrapYardId) {
		super();
		this.partId = partId;
		this.scrapYardId = fkScrapYardId;
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
		PartId other = (PartId) obj;
		return Objects.equals(partId, other.partId) && Objects.equals(scrapYardId, other.scrapYardId);
	}	
}
