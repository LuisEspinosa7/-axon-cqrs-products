package com.lsoftware.estore.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Component;

import com.lsoftware.estore.core.data.ProductLookupEntity;
import com.lsoftware.estore.core.data.ProductLookupRepository;
import com.lsoftware.estore.core.events.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {
	
	private ProductLookupRepository productLookupRepository;
	
	public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
		this.productLookupRepository = productLookupRepository;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent event) {
		ProductLookupEntity productLookupEntity = new ProductLookupEntity(event.getProductId(),
				event.getTitle());
		productLookupRepository.save(productLookupEntity);
	}
	
	@ResetHandler
	public void reset() {
		productLookupRepository.deleteAll();
	}

}
