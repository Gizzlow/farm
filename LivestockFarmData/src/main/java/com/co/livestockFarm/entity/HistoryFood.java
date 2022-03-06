package com.co.livestockFarm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@JsonInclude(Include.NON_NULL)
public class HistoryFood {
	@Id
	@SequenceGenerator(name = "MATERIALS_ID_GENERATOR", sequenceName = "MATERIALS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIALS_ID_GENERATOR")
	private int id;
	@ManyToOne
	@JoinColumn(name = "foodId")
	private Food foodId;
	private String date;
	private Date expirationDate;
	private int input;
	private int output;
	@Value("${some.key:0}")
	private int balance;
	private String nombreAlmacen;
	private String icaRegistration;
	private String lote;
	private String observation;
}
