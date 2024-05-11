package edu.tienda.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavList {
    Integer id;
    String name;
    String tag;
    String username;
    Date creationDate;
    Date lastUpdateDate;
    List<Product> productList;
}
