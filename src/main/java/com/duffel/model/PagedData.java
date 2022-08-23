package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Paged data wrapper, containing the linkages necessary to navigate forward and back.
 *
 * @param <T> response body type
 */
@EqualsAndHashCode
@Getter
@ToString
public class PagedData<T> {

    /**
     * Metadata for navigation of the pages.
     */
    @JsonProperty("meta")
    private PagedDataMeta meta;

    /**
     * List of data objects in this page.
     */
    @JsonProperty("data")
    private List<T> data;

    /**
     * How many items are returned.
     *
     * @return maximum return count
     */
    public Integer getLimit() {
        return meta.getLimit();
    }

    /**
     * A cursor pointing to the next page of records. For more information on how to paginate through records,
     * see the Pagination section.
     * Example: "g2wAAAACbQAAABBBZXJvbWlzdC1LaGFya2l2bQAAAB="
     *
     * @return next page id
     */
    public String getBefore() {
        return meta.getBefore();
    }

    /**
     * A cursor pointing to the previous page of records. For more information on how to paginate through records,
     * see the Pagination section.
     * Example: "g2wAAAACbQAAABBBZXJvbWlzdC1LaGFya2l2bQAAAB="
     *
     * @return previous page id
     */
    public String getAfter() {
        return meta.getAfter();
    }

    /**
     * Page metadata.
     */
    @EqualsAndHashCode
    @Getter
    @ToString
    public static class PagedDataMeta {
        /**
         * The maximum number of records to return per page. Defaults to 50. May be set to any integer between 1 and
         * 200. For more information on how to paginate through records, see the Pagination section.
         * Example: 1
         * Default value: 50
         */
        @JsonProperty("limit")
        private Integer limit;

        /**
         * A cursor pointing to the next page of records. For more information on how to paginate through records,
         * see the Pagination section.
         * Example: "g2wAAAACbQAAABBBZXJvbWlzdC1LaGFya2l2bQAAAB="
         */
        @JsonProperty("before")
        private String before;

        /**
         * A cursor pointing to the previous page of records. For more information on how to paginate through records,
         * see the Pagination section.
         * Example: "g2wAAAACbQAAABBBZXJvbWlzdC1LaGFya2l2bQAAAB="
         */
        @JsonProperty("after")
        private String after;

    }

}
