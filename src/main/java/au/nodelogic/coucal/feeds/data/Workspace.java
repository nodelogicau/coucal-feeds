package au.nodelogic.coucal.feeds.data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2025, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
@Entity
public class Workspace {

    @Id @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "workspace_feeds",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "feed_uri"))
    private List<Feed> feeds = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "workspace_feed_items_read",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "feed_item_uri"))
    private List<FeedItem> feedItemsRead = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "workspace_feed_items_saved",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "feed_item_uri"))
    private List<FeedItem> feedItemsSaved = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "workspace_feed_items_deleted",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "feed_item_uri"))
    private List<FeedItem> feedItemsDeleted = new ArrayList<>();

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public List<FeedItem> getFeedItemsRead() {
        return feedItemsRead;
    }

    public void setFeedItemsRead(List<FeedItem> feedItemsRead) {
        this.feedItemsRead = feedItemsRead;
    }

    public List<FeedItem> getFeedItemsSaved() {
        return feedItemsSaved;
    }

    public void setFeedItemsSaved(List<FeedItem> feedItemsSaved) {
        this.feedItemsSaved = feedItemsSaved;
    }

    public List<FeedItem> getFeedItemsDeleted() {
        return feedItemsDeleted;
    }

    public void setFeedItemsDeleted(List<FeedItem> feedItemsDeleted) {
        this.feedItemsDeleted = feedItemsDeleted;
    }
}
