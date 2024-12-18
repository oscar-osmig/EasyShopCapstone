package org.yearup.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart
{
    private Map<Integer, ShoppingCartItem> items = new HashMap<>();

    public Map<Integer, ShoppingCartItem> getItems()
    {
        return items;
    }

    public void setItems(Map<Integer, ShoppingCartItem> items)
    {
        this.items = items;
    }

    // my code
    private int product_id;

    public int getProduct_id() {
        return product_id;
    }


   // write code to get quantity of the items
   public int getQuantityForProduct(int productId) {
       ShoppingCartItem item = items.get(productId);
       if (item != null) {
           return item.getQuantity();  //
       }
       return 0;  // Return 0 if the item is not in the cart
   }

    public boolean contains(int productId)
    {
        product_id = productId;
        return items.containsKey(productId);
    }

    public void add(ShoppingCartItem item)
    {
        items.put(item.getProductId(), item);
    }

    public ShoppingCartItem get(int productId)
    {
        return items.get(productId);
    }

    public BigDecimal getTotal()
    {
        BigDecimal total = items.values()
                                .stream()
                                .map(i -> i.getLineTotal())
                                .reduce( BigDecimal.ZERO, (lineTotal, subTotal) -> subTotal.add(lineTotal));

        return total;
    }

}
