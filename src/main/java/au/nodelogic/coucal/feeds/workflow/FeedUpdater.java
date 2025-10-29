/*
 *  Copyright 2025 Node Logic
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package au.nodelogic.coucal.feeds.workflow;

import au.nodelogic.coucal.feeds.channel.FeedService;
import au.nodelogic.coucal.feeds.data.*;
import com.rometools.rome.io.FeedException;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
//@Transactional
public class FeedUpdater {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedUpdater.class);

    private final FeedService feedService;

    private final FeedRepository feedRepository;

    private final FeedCategoryRepository feedCategoryRepository;

    private final FeedItemRepository feedItemRepository;

    private boolean shuttingDown = false;

    public FeedUpdater(@Autowired FeedService feedService, @Autowired FeedRepository feedRepository,
                       @Autowired FeedCategoryRepository feedCategoryRepository,
                       @Autowired FeedItemRepository feedItemRepository) {
        this.feedService = feedService;
        this.feedRepository = feedRepository;
        this.feedCategoryRepository = feedCategoryRepository;
        this.feedItemRepository = feedItemRepository;
    }

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void refreshFeeds() {
        try {
            List<Feed> feeds = feedRepository.findAll();
            List<FeedCategory> categories = new ArrayList<>();
            List<FeedItem> feedItems = new ArrayList<>();
            feeds.parallelStream().forEach(feed -> {
                if (shuttingDown) {
                    return;
                }
                try {
                    feedService.refreshFeed(feed.getSource(), new FeedConsumer(feed, feedItems, categories));
                } catch (FeedException | IOException e) {
                    LOGGER.error("Failed to refresh feed: {}", feed.getSource(), e);
                }
            });
            feedRepository.saveAll(feeds);
            feedItemRepository.saveAll(feedItems);
            feedCategoryRepository.saveAll(categories);
        } catch (Exception ex) {
            LOGGER.error("Failed to refresh feeds", ex);
        }
    }

    public void discoverFeeds(String url) throws IOException {
        List<String> feedUrls = feedService.resolveFeeds(url);
        List<Feed> feeds = new ArrayList<>();
        List<FeedCategory> categories = new ArrayList<>();
        List<FeedItem> feedItems = new ArrayList<>();
        feedUrls.forEach(feedUrl -> {
            try {
                URL source = URI.create(feedUrl).toURL();
                Feed feed = new Feed();
                feed.setUri(feedUrl);
                feed.setSource(source);
                feeds.add(feed);
                feedService.refreshFeed(source, new FeedConsumer(feed, feedItems, categories));
            } catch (FeedException | IOException e) {
                LOGGER.error("Failed to refresh feed: {}", feedUrl, e);
            }
        });
        feedRepository.saveAll(feeds);
        feedItemRepository.saveAll(feedItems);
        feedCategoryRepository.saveAll(categories);
    }

    @PreDestroy
    public void shutdown() {
        shuttingDown = true;
    }
}
