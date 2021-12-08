/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.dto;

import java.util.List;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

//D:\HarbourAppA\experimental\HarbourMaster\project\src\main\java\model
/**
 *
 * @author jake_
 */
@Entity
@Table(name = "pilots")
public class Pilot {

    private Long id;

    private String uuid = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private Boolean available;

    // Constructor for saving a Pilot without giving an explicit ID.
    public Pilot(String firstName, String lastName, Boolean available) {

        this.firstName = firstName;
        this.lastName = lastName;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    

//
//	@Override
//	public String toString() {
//		String dobString = dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		String allowedToStr = allowedTo.stream()
//			.map(type -> String.valueOf(type))
//			.collect(Collectors.joining(", ", "[", "]"));
//
//		return getClass().getSimpleName() + String.format("[id=%d, allowedTo=%s, firstName=%s, lastName=%s, dateOfBirth=%s]", id, allowedToStr, firstName, lastName, dobString);
//	}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Pilot{" + "id=" + id + ", uuid=" + uuid + ", firstName=" + firstName + ", lastName=" + lastName + ", available=" + available + '}';
    }
    
    
    
}
