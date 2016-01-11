package org.lulliloo.ouchallenge.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lulliloo.ouchallenge.ItemPricesApplication;
import org.lulliloo.ouchallenge.model.ItemPriceResponse;
import org.lulliloo.ouchallenge.model.ItemPriceSuggestion;
import org.lulliloo.ouchallenge.util.JsonMarshall;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author <a href="mailto:tom@lulliloo.org">Tom McCann</a>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ItemPricesApplication.class)
public class ItempricesApplicationTests {

    private ItemPricesRestController controller;

    @Before
    public void setUp() throws Exception {
        this.controller = new ItemPricesRestController();
        this.controller.itemPriceDao = Mockito.mock(ItemPriceDaoService.class);
        Mockito.when(this.controller.itemPriceDao.findPriceSuggestionByItemCity("PS4", "Seattle")).
                thenReturn(new ItemPriceSuggestion("PS4", 5, 22, "Seattle"));
        Mockito.when(this.controller.itemPriceDao.findPriceSuggestionByItem("PS4")).
                thenReturn(new ItemPriceSuggestion("PS4", 4, 21, "Not specified"));
    }

    @Test
	public void contextLoads() {
        // servlet context loaded by annotations
        // no code needed for this this test, it will only fail if the annotations get jacked
        // or a dependent library is missing
	}

	@Test
    public void testItemAndCity() {
        String json = this.controller.itemPrice("PS4", "Seattle");
        ItemPriceResponse response = JsonMarshall.toObject(json, ItemPriceResponse.class);
        Assert.assertTrue(response.getStatus() == 200);
        Assert.assertTrue(response.getContent().getPriceSuggestion() == 22);
    }

    @Test
    public void testItemOnly() {
        String json = this.controller.itemPrice("PS4", null);
        ItemPriceResponse response = JsonMarshall.toObject(json, ItemPriceResponse.class);
        Assert.assertTrue(response.getStatus() == 200);
        Assert.assertTrue(response.getContent().getPriceSuggestion() == 21);
        Assert.assertTrue(response.getContent().getCity().equals("Not specified"));
    }

    @Test
    public void testNoArgs() {
        String json = this.controller.itemPrice(null, null);
        ItemPriceResponse response = JsonMarshall.toObject(json, ItemPriceResponse.class);
        Assert.assertTrue(response.getStatus() == 404);
        Assert.assertTrue(response.getContent().getMessage().equals("Not found"));
    }
}
