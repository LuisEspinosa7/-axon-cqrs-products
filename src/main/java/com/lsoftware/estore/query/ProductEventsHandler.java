package com.lsoftware.estore.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.lsoftware.estore.core.data.ProductEntity;
import com.lsoftware.estore.core.data.ProductsRepository;
import com.lsoftware.estore.core.events.ProductCreatedEvent;
import com.lsoftware.estore.shared.core.events.ProductReservedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);
	
	private final ProductsRepository productsRepository;
	
	public ProductEventsHandler(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}
	
	
	@ExceptionHandler(resultType = Exception.class)
	public void handle(Exception exception) throws Exception {
		throw exception;
	}
	
	@ExceptionHandler(resultType = IllegalArgumentException.class)
	public void handle(IllegalArgumentException exception) {
		
	}
	
	@EventHandler
	public void on(ProductCreatedEvent event) throws Exception {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);
		
		try {
			productsRepository.save(productEntity);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		//if (true) throw new Exception("Forcing exception in the event handler class");
		
	}
	
	
	@EventHandler
	public void on(ProductReservedEvent productReservedEvent) {
		ProductEntity productEntity = productsRepository.findByProductId(productReservedEvent.getProductId());
		productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
		
		productsRepository.save(productEntity);
		
		LOGGER.info("OrderCreatedEvent handled for orderId: " + productReservedEvent.getOrderId() + 
				" and productId" + productReservedEvent.getProductId());
	}
}
