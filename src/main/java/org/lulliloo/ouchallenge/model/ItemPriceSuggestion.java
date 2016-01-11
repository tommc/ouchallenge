package org.lulliloo.ouchallenge.model;

/**
 * This is the content of a price suggestion response
 *
 * @author <a href="mailto:tom@lulliloo.org">Tom McCann</a>
 */
public class ItemPriceSuggestion {
    private String item;
    private Integer itemCount;
    private Integer priceSuggestion;
    private String city;
    private String message;

    private ItemPriceSuggestion() {}

    public ItemPriceSuggestion(String item, Integer item_count, Integer price_suggestion, String city) {
        this.item = item;
        this.itemCount = item_count;
        this.priceSuggestion = price_suggestion;
        this.city = city;
    }

    public String getItem() {
        return item;
    }
    public Integer getItemCount() {
        return itemCount;
    }
    public Integer getPriceSuggestion() {
        return priceSuggestion;
    }
    public String getCity() {
        return city;
    }
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemPriceSuggestion{");
        if (item != null)
            sb.append("item='").append(item).append('\'');
        if (itemCount != null)
            sb.append(", itemCount=").append(itemCount);
        if (priceSuggestion != null)
            sb.append(", priceSuggestion=").append(priceSuggestion);
        if (city != null)
            sb.append(", city='").append(city).append('\'');
        if (message != null)
            sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    // static "not found" suggestion to avoid creating garbage
    public static final ItemPriceSuggestion NOT_FOUND;
    static {
        NOT_FOUND = new ItemPriceSuggestion();
        NOT_FOUND.message = "Not found";
    }
}
