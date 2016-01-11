package org.lulliloo.ouchallenge.service;

import org.lulliloo.ouchallenge.model.ItemPriceResponse;
import org.lulliloo.ouchallenge.model.ItemPriceSuggestion;
import org.lulliloo.ouchallenge.util.JsonMarshall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemPricesRestController {
    protected ItemPriceDaoService itemPriceDao;

    @Autowired
    public void setItemPriceDao(ItemPriceDaoService itemPriceDao) {
        this.itemPriceDao = itemPriceDao;
    }

    // for health checks (in case we're running behind a load balancer)
    @RequestMapping(value = "/checkme")
    public String checkMe() {
        return "OK";
    }

    @RequestMapping(value = "/item-price-service", method = RequestMethod.GET)
    public String itemPrice(@RequestParam(value = "item", required=false) String item,
                             @RequestParam(value = "city", required=false) String city) {

        ItemPriceSuggestion priceSuggestion = null;
        if (item != null) {
            if (city != null)
                priceSuggestion = this.itemPriceDao.findPriceSuggestionByItemCity(item, city);
            else {
                priceSuggestion = this.itemPriceDao.findPriceSuggestionByItem(item);
            }
        }
        if (priceSuggestion != null)
            return JsonMarshall.fromObject(new ItemPriceResponse(200, priceSuggestion));
        else
            return JsonMarshall.fromObject(RESPONSE_NOT_FOUND);
    }

    private static final ItemPriceResponse RESPONSE_NOT_FOUND = new ItemPriceResponse(404, ItemPriceSuggestion.NOT_FOUND);
}
