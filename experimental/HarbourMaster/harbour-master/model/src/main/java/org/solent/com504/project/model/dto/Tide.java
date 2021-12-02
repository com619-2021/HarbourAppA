
package org.solent.com504.project.model.dto;

import org.solent.com504.project.model.dto.TideStage;

import java.util.ArrayList;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;

import java.sql.Timestamp;

import javax.persistence.Lob;
import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

//groupB layout for tide object

@Entity
@Table(name = "tides")

public class Tide {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double height;
        private boolean tidestage;

	@Enumerated(EnumType.ORDINAL)
	private DayOfWeek day;

	@Column(name="start", columnDefinition="time")
	private LocalTime start;

	@Column(name="end", columnDefinition="time")
	private LocalTime end;

        
        public Tide(DayOfWeek day, double height, LocalTime start, LocalTime end) {
		this.day = day;
		this.height = height;
		this.start = start;
		this.end = end;
	}

	public Tide(int id, DayOfWeek day, double height, boolean tidestage, LocalTime start, LocalTime end) {
		this.id = id;
		this.day = day;
		this.height = height;
                this.tidestage = tidestage;
		this.start = start;
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
        
        
        
        public Boolean getTideStage() {
		return tidestage;
	}
        
        
        public void setTideStage (Boolean tidestage){
            this.tidestage = tidestage;
            
        }
               

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + String.format("[id=%d, height=%f, day=%s, start=%s, end=%s]", id, height, day.name(), start.toString(), end.toString());
	}
        
        
        
        
        
        
        
        
        
        
        
    
}


