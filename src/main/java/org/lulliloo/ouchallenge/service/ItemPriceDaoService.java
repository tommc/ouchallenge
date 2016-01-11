package org.lulliloo.ouchallenge.service;

import org.lulliloo.ouchallenge.model.ItemPriceSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

/**
 * DAO service for fetching price suggestions from the database
 *
 * @author <a href="mailto:tom@lulliloo.org">Tom McCann</a>
 */
@Component("itemPriceDao")
@Repository
public class ItemPriceDaoService {

    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    // by item only
    public ItemPriceSuggestion findPriceSuggestionByItem(String item) {
        Map<String, Object> rs = null;
        try {
            rs = jdbc.queryForMap(SQL_ITEM, item);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            // eat exception (item wasn't found)
        }
        if (rs == null)
            return null;
        else {
            Long count = (Long) rs.get("c");
            Integer price = (Integer) rs.get("list_price");
            return new ItemPriceSuggestion(item, count.intValue(), price, "Not specified");
        }
    }
    private static String SQL_ITEM = "select count(1) c, list_price " +
            "    from \"itemPrices_itemsale\" where title = ? " +
            "    group by list_price " +
            "    order by c desc, list_price desc limit 1;";

    // by item and city
    public ItemPriceSuggestion findPriceSuggestionByItemCity(String item, String city) {
        Map<String, Object> rs = null;
        try {
            rs = jdbc.queryForMap(SQL_ITEM_CITY, item, city);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            // eat exception (item wasn't found)
        }
        if (rs == null)
            return null;
        else {
            Long count = (Long) rs.get("c");
            Integer price = (Integer) rs.get("list_price");
            return new ItemPriceSuggestion(item, count.intValue(), price, city);
        }
    }
    private static String SQL_ITEM_CITY = "select count(1) c, list_price " +
            "    from \"itemPrices_itemsale\" where title = ? and city = ? " +
            "    group by list_price " +
            "    order by c desc, list_price desc limit 1;";
}
