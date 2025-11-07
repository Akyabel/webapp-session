package org.serogr.apiservlet.webapp.cookies.models;

import jakarta.mail.FetchProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemCarro itemCarro){
        if (items.contains(itemCarro)){
           Optional<ItemCarro> optionalItemCarro = items.stream()
                   .filter(item -> item.equals(itemCarro)).findAny();
           if (optionalItemCarro.isPresent()){
               ItemCarro i = optionalItemCarro.get();
               i.setCantidad(i.getCantidad() + 1);
           }
        } else {
            this.items.add(itemCarro);
        }
    }

    public List<ItemCarro> getItems(){
        return items;
    }

    public int getTotal(){
        // Con expresión lambda
        //return items.stream().mapToInt(item -> item.getImporte()).sum();ç
        //Con método de referencia
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }

    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            // productoIds.forEach(productoId -> removeProducto(productoId));
            productoIds.forEach(this::removeProducto);
        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return  items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
