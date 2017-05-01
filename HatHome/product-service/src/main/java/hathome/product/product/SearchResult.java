package hathome.product.product;

import java.util.List;

/**
 * Created by Acer on 6/3/2560.
 */
public class SearchResult {
    private String keyword;
    private List<Product> results;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
        this.results = results;
    }
}
