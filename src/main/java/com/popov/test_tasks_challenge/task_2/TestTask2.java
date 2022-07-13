package com.popov.test_tasks_challenge.task_2;


import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.SECONDS;

public class TestTask2 {

    static class PagedResult {

        private int page;
        private int per_page;
        private int total;
        private int total_pages;
        private List<Article> data;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public List<Article> getData() {
            return data;
        }

        public void setData(List<Article> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "PagedResult{" +
                    "page=" + page +
                    ", per_page=" + per_page +
                    ", total=" + total +
                    ", total_pages=" + total_pages +
                    ", data=" + data +
                    '}';
        }
    }

    static class Article {
        private String title;
        private String url;
        private String author;
        private long num_comments;
        private long story_id;
        private String story_title;
        private String story_url;
        private long paren_id;
        private long createdAt;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public long getNum_comments() {
            return num_comments;
        }

        public void setNum_comments(long num_comments) {
            this.num_comments = num_comments;
        }

        public long getStory_id() {
            return story_id;
        }

        public void setStory_id(long story_id) {
            this.story_id = story_id;
        }

        public String getStory_title() {
            return story_title;
        }

        public void setStory_title(String story_title) {
            this.story_title = story_title;
        }

        public String getStory_url() {
            return story_url;
        }

        public void setStory_url(String story_url) {
            this.story_url = story_url;
        }

        public long getParen_id() {
            return paren_id;
        }

        public void setParen_id(long paren_id) {
            this.paren_id = paren_id;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }

        @Override
        public String toString() {
            return "Article{" +
                    "title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", author='" + author + '\'' +
                    ", num_comments=" + num_comments +
                    ", story_id=" + story_id +
                    ", story_title='" + story_title + '\'' +
                    ", story_url='" + story_url + '\'' +
                    ", paren_id=" + paren_id +
                    ", createdAt=" + createdAt +
                    '}';
        }
    }


    public static void main(String[] args) {
        List<String> result = getArticleTitles("epaga");
        System.out.println("Result = " + result);
    }

    public static List<String> getArticleTitles(String author) {
        String baseUrl = "https://jsonmock.hackerrank.com/api/articles";
        List<String> totalResult = doExtract(baseUrl, author, 1);
        return totalResult;
    }

    private static List<String> doExtract(String baseUrl, String author, int page) {

        String resourceUrl = baseUrl + "?author=" + author + "&page=" + page;

        try {
            HttpClient client = HttpClient.newBuilder().build();

            URI uri = new URI(resourceUrl);
            HttpRequest request = getHttpRequest(uri);

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            PagedResult targetObject =
                    new Gson().fromJson(response.body(), PagedResult.class);

            List<String> firstResult = getPageResults(targetObject);

            int total_pages = targetObject.getTotal_pages();

            if (total_pages == 1 && page == 1) return firstResult;

            // More than 1 page!

            List<URI> targets = IntStream.range(1, total_pages)
                    .mapToObj(p -> {
                        try {
                            return new URI(baseUrl + "?author=" + author + "&page=" + p);
                        } catch (URISyntaxException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toList());

            List<CompletableFuture<List<String>>> result = targets.stream()
                    .map(nextUri -> client.sendAsync(getHttpRequest(nextUri), HttpResponse.BodyHandlers.ofString())
                            .thenApply(r -> {
                                PagedResult nextObject =
                                        new Gson().fromJson(response.body(), PagedResult.class);
                                return getPageResults(nextObject);
                            }))
                    .collect(Collectors.toList());

            List<String> finalResult = result.stream().map(cf -> {
                        try {
                            return cf.get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            return finalResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest getHttpRequest(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .timeout(Duration.of(10, SECONDS))
                .build();
        return request;
    }

    private static List<String> getPageResults(PagedResult targetObject) {
        List<Article> nextData = targetObject.getData();
        List<String> nextResult = nextData.stream()
                .filter(d -> !(d.getTitle() == null && d.getStory_title() == null))
                .map(d -> {
                    if (d.getTitle() != null) return d.getTitle();
                    return d.getStory_title();
                }).collect(Collectors.toList());
        return nextResult;
    }

}
