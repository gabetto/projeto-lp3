package com.JavaRunner.JavaRunner.controller.requests;

import com.JavaRunner.JavaRunner.domain.model.Kit;
import com.JavaRunner.JavaRunner.domain.model.Product;
import com.JavaRunner.JavaRunner.domain.model.Race;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class KitResponse {
    private String id;
    private String type;
    private String name;
    private String description;
    private Race race;
    private Double price;
    private List<Product> products;

    public KitResponse(Kit kit, Double price) {
        this.setDescription(kit.getDescription())
                .setId(kit.getId())
                .setName(kit.getName())
                .setRace(kit.getRace())
                .setType(kit.getType())
                .setPrice(price);
    }
}
