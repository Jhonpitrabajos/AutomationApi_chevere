package com.api_testing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListResponse {
    
    @JsonProperty("page")
    private Integer page;
    
    @JsonProperty("per_page")
    private Integer perPage;
    
    @JsonProperty("total")
    private Integer total;
    
    @JsonProperty("total_pages")
    private Integer totalPages;
    
    @JsonProperty("data")
    private List<User> data;
    
    @JsonProperty("support")
    private Support support;
    
    // Default constructor
    public UserListResponse() {}
    
    // Getters and Setters
    public Integer getPage() { return page; }
    public void setPage(Integer page) { this.page = page; }
    
    public Integer getPerPage() { return perPage; }
    public void setPerPage(Integer perPage) { this.perPage = perPage; }
    
    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }
    
    public Integer getTotalPages() { return totalPages; }
    public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
    
    public List<User> getData() { return data; }
    public void setData(List<User> data) { this.data = data; }
    
    public Support getSupport() { return support; }
    public void setSupport(Support support) { this.support = support; }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Support {
        @JsonProperty("url")
        private String url;
        
        @JsonProperty("text")
        private String text;
        
        public Support() {}
        
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }
}
