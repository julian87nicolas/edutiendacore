package edu.tienda.core.controllers;

import edu.tienda.core.auth.jwt.JwtService;
import edu.tienda.core.domain.FavList;
import edu.tienda.core.domain.Product;
import edu.tienda.core.persistance.entities.FavListEntity;
import edu.tienda.core.services.product.FavListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/favlist")
public class FavListController {

    @Autowired
    FavListService favListService;

    @Autowired
    JwtService jwtService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FavList favList, HttpServletRequest request) {
        String username = getUsernameFromToken(request);
        favList.setUsername(username);
        favListService.create(favList);
        return ResponseEntity.ok(favList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Integer id, HttpServletRequest request) {
        String username = getUsernameFromToken(request);
        FavListEntity favList = favListService.read(id, username);
        return ResponseEntity.ok(favList);
    }

    @PostMapping("/add/{listId}")
    public void addProductToList(@PathVariable Integer listId, @RequestBody Product product, HttpServletRequest request) {
        String username = getUsernameFromToken(request);
        favListService.addProductToList(username, listId, product.getId());
    }

    @GetMapping()
    public ResponseEntity<?> readAll(HttpServletRequest request) {
        String username = getUsernameFromToken(request);
        List<FavListEntity> favLists = favListService.readAll(username);
        return ResponseEntity.ok(favLists);
    }

    public String getUsernameFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return jwtService.getUserNameFromToken(token);
    }
}
