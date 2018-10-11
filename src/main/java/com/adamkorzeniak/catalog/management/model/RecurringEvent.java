package com.adamkorzeniak.catalog.management.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("recurring")
public class RecurringEvent extends Event {

	private LocalDate endDate;

	private String periodAdded;
	private String weekDayList;

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getPeriodAdded() {
		return periodAdded;
	}

	public void setPeriodAdded(String periodAdded) {
		this.periodAdded = periodAdded;
	}

	public String getWeekDayList() {
		return weekDayList;
	}

	public void setWeekDayList(String weekDayList) {
		this.weekDayList = weekDayList;
	}
}
