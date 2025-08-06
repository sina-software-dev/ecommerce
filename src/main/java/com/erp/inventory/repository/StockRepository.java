package com.erp.inventory.repository;

import com.erp.inventory.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    
    Optional<Stock> findByProductId(Long productId);
    
    @Query("SELECT s FROM Stock s WHERE s.isActive = true")
    List<Stock> findAllActiveStock();
    
    @Query("SELECT s FROM Stock s WHERE s.quantityOnHand <= 0 AND s.isActive = true")
    List<Stock> findOutOfStockItems();
    
    @Query("SELECT s FROM Stock s JOIN s.product p WHERE s.quantityOnHand <= p.reorderLevel AND s.isActive = true")
    List<Stock> findLowStockItems();
    
    @Query("SELECT s FROM Stock s WHERE s.location = :location AND s.isActive = true")
    List<Stock> findByLocation(@Param("location") String location);
    
    @Query("SELECT DISTINCT s.location FROM Stock s WHERE s.location IS NOT NULL AND s.isActive = true")
    List<String> findAllLocations();
}