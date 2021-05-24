package com.gd.sakila.vo;

import lombok.Data;

@Data
public class Film {
	private int filmId;
	private String title;
	private String description;
	private String releaseYear;
	private int languageId;
	private String originalLanguageId;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String special_features;
	private String lastUpdate;
}
