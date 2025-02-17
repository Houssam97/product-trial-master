package com.alten.models;

import com.alten.models.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product extends DomainEntity {

    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private Long shellId;
    private InventoryStatus inventoryStatus;
    private int rating;


}
