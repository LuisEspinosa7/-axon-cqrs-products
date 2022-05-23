package com.lsoftware.estore.core.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productlookup")
public class ProductLookupEntity implements Serializable {

	private static final long serialVersionUID = -3239946153797075252L;

	@Id
	private String productId;
	
	@Column(unique = true)
	private String title;
}
