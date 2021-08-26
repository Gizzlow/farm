package com.co.livestockFarm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Livestock {
	@Id
	private int livestockId;
	private String name;
//	@ManyToOne
//	@JoinColumn(name = "livestockId")
//	private Livestock motherId;
	private String type;
	private boolean active;
	private String observation;
}
