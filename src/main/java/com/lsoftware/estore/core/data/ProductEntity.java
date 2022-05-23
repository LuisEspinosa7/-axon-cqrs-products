package com.lsoftware.estore.core.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="products")
@Data
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = 6151775469084985022L;
	
	@Id
	@Column(unique = true)
	private String productId;
	@Column(unique = true)
	private String title;
	private BigDecimal price;
	private Integer quantity;
	
}
