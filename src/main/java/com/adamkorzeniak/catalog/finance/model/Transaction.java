package com.adamkorzeniak.catalog.finance.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_category_id")
	private Category sourceCategory;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_category_id")
	private Category destinationCategory;
	private int amount;
	private LocalDateTime datetime;
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Category getSourceCategory() {
		return sourceCategory;
	}
	public void setSourceCategory(Category sourceCategory) {
		this.sourceCategory = sourceCategory;
	}
	public Category getDestinationCategory() {
		return destinationCategory;
	}
	public void setDestinationCategory(Category destinationCategory) {
		this.destinationCategory = destinationCategory;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
