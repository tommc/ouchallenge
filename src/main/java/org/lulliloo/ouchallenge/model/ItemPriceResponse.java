package org.lulliloo.ouchallenge.model;

/**
 * Wrapper class for a price suggestion.  Contains a status code an ItemPriceSuggestions.
 *
 * @author <a href="mailto:tom@lulliloo.org">Tom McCann</a>
 */
public class ItemPriceResponse {
    private int status;
    private ItemPriceSuggestion content;

    public ItemPriceResponse(int status, ItemPriceSuggestion content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public ItemPriceSuggestion getContent() {
        return content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemPriceResponse{");
        sb.append("status=").append(status);
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }
}
