package hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FurnitureOrder implements FurnitureOrderInterface {

    HashMap<Furniture, Integer> items=null;
    /**
     * Initialize a new mapping of Furniture types to order quantities.
     */
    FurnitureOrder( HashMap<Furniture, Integer> items) {
        this.items= items;
    }

    public void addToOrder(final Furniture type, final int furnitureCount) {
        if(type!=null) {
            Furniture[] temp = Furniture.values();
            Optional<Furniture> furnitureOptional = Arrays.stream(Furniture.values()).filter(fur -> fur.label().equals(type.label())).findAny();
            if (furnitureOptional.isPresent()) {
                Furniture fur = furnitureOptional.get();
                Integer count = items.get(fur);
                if (count == null) {
                    count = new Integer(furnitureCount);
                } else {
                    count = new Integer(count.intValue() + furnitureCount);
                }
                items.put(fur, count);
            }
        }
    }

    public HashMap<Furniture, Integer> getOrderedFurniture() {

        return items;
    }

    public float getTotalOrderCost() {
        double cost = items.keySet().stream().mapToDouble(type -> this.getTypeCost(type)).sum();
        return new Double(cost).floatValue();
    }

    public int getTypeCount(Furniture type) {
if(type!=null && items.get(type)!=null) {
    return items.get(type).intValue();
}return 0;
    }

    public float getTypeCost(Furniture type) {
        Integer count = items.get(type);
        if(count!=null)
        {
            float cost = count * type.cost();
            return  new Float(cost).intValue();
        }
        return 0;
    }

    public int getTotalOrderQuantity() {
        // TODO: Complete the method
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }
}