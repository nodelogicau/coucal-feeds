package au.nodelogic.coucal.feeds.workflow;

import au.nodelogic.coucal.feeds.data.Feed;
import au.nodelogic.coucal.feeds.data.FeedCategory;
import au.nodelogic.coucal.feeds.data.FeedItem;
import com.rometools.rome.feed.synd.SyndFeed;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class FeedConsumer implements Consumer<SyndFeed> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedConsumer.class);

    private final Feed feed;

    private final List<FeedItem> feedItems;

    private final List<FeedCategory> feedCategories;

    private final PolicyFactory htmlSanitizerPolicy;

    public FeedConsumer(Feed feed, List<FeedItem> feedItems, List<FeedCategory> categories) {
        this.feed = feed;
        this.feedItems = feedItems;
        this.feedCategories = categories;
        this.htmlSanitizerPolicy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
    }

    @Override
    public void accept(SyndFeed syndFeed) {
        if (syndFeed.getUri() != null) {
            feed.setUri(syndFeed.getUri());
        }
        feed.setTitle(syndFeed.getTitle());
        feed.setDescription(syndFeed.getDescription());
        feed.setPublishedDate(syndFeed.getPublishedDate());
        try {
            feed.setLink(URI.create(syndFeed.getLink()).toURL());
        } catch (MalformedURLException e) {
            LOGGER.warn("Invalid feed link {}", syndFeed.getLink());
        }
        if (syndFeed.getIcon() != null) {
            try {
                feed.setIcon(URI.create(syndFeed.getLink()).resolve(syndFeed.getIcon().getUrl()).toURL());
            } catch (MalformedURLException e) {
                LOGGER.warn("Invalid icon link {}", syndFeed.getIcon().getUrl());
            }
        }
        syndFeed.getEntries().forEach(entry -> {
            try {
                FeedItem item = new FeedItem();
                //identity field is mandatory..
                item.setUri(Objects.requireNonNull(entry.getUri()));
                item.setTitle(entry.getTitle());
                item.setLink(entry.getLink());
                if (entry.getDescription() != null) {
                    item.setDescription(htmlSanitizerPolicy.sanitize(entry.getDescription().getValue()));
                }
                item.setPublishedDate(entry.getPublishedDate());
                item.setFeed(feed);
                item.setCategories(entry.getCategories().stream().filter(c -> c.getTaxonomyUri() != null)
                        .map(syndCategory -> {
                    FeedCategory category = new FeedCategory();
                    category.setName(syndCategory.getName());
                    category.setUri(syndCategory.getTaxonomyUri());
                    return category;
                }).toList());
                feedCategories.addAll(item.getCategories());
                feedItems.add(item);
            } catch (Exception e) {
                LOGGER.warn("Invalid feed entry {}", entry.getUri());
            }
        });
    }
}
