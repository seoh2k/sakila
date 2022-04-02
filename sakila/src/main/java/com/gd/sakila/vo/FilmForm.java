package com.gd.sakila.vo;

import java.util.List;

import lombok.Data;

@Data
public class FilmForm { // 폼의 정보들을 vo 타입으로 만들어줌 -> 커맨드 객체
   private Category category;
   private Film film;
   private String lastUpdate;
   private List<String> specialFeatures;
   
   public void setSpecialFeatures(List<String> specialFeatures) {
      if(specialFeatures != null) {
         StringBuffer sb = new StringBuffer();
         for(String sf : specialFeatures) {
            sb.append(sf+",");
         }
         this.film.setSpecialFeatures(sb.toString().substring(0, sb.toString().length()-1));
      }
   }

	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Film getFilm() {
		return film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
	
	public String getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public List<String> getSpecialFeatures() {
		return specialFeatures;
	}
}