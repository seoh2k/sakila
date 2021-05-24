package com.gd.sakila.vo;

import lombok.Data;

@Data
public class FilmList {
	private int filmId;
	private String title;
	private String categoryName;
	private String description;
	private String releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private int LENGTH;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private String lastUpdate;
	private String actors;
}
