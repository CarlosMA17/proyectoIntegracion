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
	private boolean stock;
	
	public ScrapYardParts() {}
	
	public ScrapYardParts(Part partId, ScrapYard scrapYardId, int wearLevel, float price, boolean stock) {
		super();
		this.partId = partId;
		this.scrapYardId = scrapYardId;
		this.wearLevel = wearLevel;
		this.price = price;
		this.stock = stock;
	}

	public String getPartId() {
		return partId.getName();
	}

	public void setPartId(Part partId) {
		this.partId = partId;
	}

	public String getScrapYardId() {
		return scrapYardId.getName();
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

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}
	
	
}
