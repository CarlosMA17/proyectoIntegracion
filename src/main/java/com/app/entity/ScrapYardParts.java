package com.app.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
@IdClass(PartId.class)
public class ScrapYardParts implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name="part_id")
	private Part partId;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="scrap_yard_id")
	private ScrapYard scrapYardId;
	
	private int wearLevel;
	private float price;
	
	public ScrapYardParts() {}
	
	public ScrapYardParts(Part partId, ScrapYard scrapYardId, int wearLevel, float price) {
		super();
		this.partId = partId;
		this.scrapYardId = scrapYardId;
		this.wearLevel = wearLevel;
		this.price = price;
	}

	public String getCarBrand() {
		return partId.getCar().getBrand();
	}
	
	public String getCarModel() {
		return partId.getCar().getModel();
	}
	
	public String getCarEngine() {
		return partId.getCar().getEngine();
	}
	
	public String getPartName() {
		return partId.getName();
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
	
}
