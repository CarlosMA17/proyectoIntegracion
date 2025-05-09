package com.app.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
//@IdClass(ScrapYardPartsId .class)
public class ScrapYardParts implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scrapYardPartId;
	
	@ManyToOne
	@JoinColumn(name="part_id")
	private Part partId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="scrap_yard_id")
	private ScrapYard scrapYardId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id", nullable = false)
	@JsonManagedReference
	private Car car;
	
	private int wearLevel;
	private float price;
	private boolean reserved;
	
	public ScrapYardParts() {}

	public ScrapYardParts(Part partId, ScrapYard scrapYardId, int wearLevel, float price, boolean reserved) {
		super();
		this.partId = partId;
		this.scrapYardId = scrapYardId;
		this.wearLevel = wearLevel;
		this.price = price;
		this.reserved = reserved;
	}



	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	
	public String getcategory() {
		return partId.getCategory().getCategoryName();
	}


	public void setPartId(Part partId) {
		this.partId = partId;
	}

	public String getScrapYardName() {
		return scrapYardId.getName();
	}
	
	public String getScrapYardLocation() {
		return scrapYardId.getLocation();
	}

	public void setScrapYardId(ScrapYard scrapYardId) {
		this.scrapYardId = scrapYardId;
	}

	public int getwearLevel() {
		return wearLevel;
	}

	public void setwearLevel(int wearLevel) {
		this.wearLevel = wearLevel;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getPartName() {
	    return partId.getName();
	}
	
	public Long getScrapYardId() {
	    return scrapYardId.getScrapYardId();
	}
	
}
