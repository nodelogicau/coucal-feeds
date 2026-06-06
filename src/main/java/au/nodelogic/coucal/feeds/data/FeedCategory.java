package au.nodelogic.coucal.feeds.data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FeedCategory {

    @Id
    private String uri;

    private String name;

    @ManyToOne
    @JoinColumn(name = "feed_uri")
    private Feed feed;

    @ManyToMany(mappedBy = "categories")
    private List<FeedItem> feedItems = new ArrayList<>();

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public List<FeedItem> getFeedItems() {
        return feedItems;
    }

    public void setFeedItems(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }
}
