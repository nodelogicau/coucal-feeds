# Coucal Feeds

A feed aggregator that uses AI to classify links for smarter browsing

## Introduction

As with many aggregators, Coucal collects links from various sources such as RSS feeds,
social media, and user submissions. However, what sets Coucal apart is its use of AI to
classify and organize these links into meaningful categories, making it easier for users
to find content that interests them.

By leveraging AI, Coucal can analyze the content of each link and determine its relevance
to different topics or themes. This allows users to quickly discover new content without
having to sift through irrelevant links.

The platform also offers features such as personalized recommendations, bookmarking,
and sharing, making it a comprehensive solution for managing and consuming online content.

## Features

- AI-powered classification: Automatically categorizes links into relevant topics.
- Personalized recommendations: Suggests content based on user preferences and browsing history.
- Bookmarking: Allows users to save links for later reading.
- Sharing: Enables easy sharing of interesting links with friends and colleagues.
- User-friendly interface: Intuitive design for easy navigation and content discovery.

## Feature Comparison

The table below compares common features found in traditional social media platforms with those offered by Coucal.

| Feature                        | Social Media Platforms                                                  | Coucal                                                                                                 |
|--------------------------------|-------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| **User Profiles**              | ✅ Users can create profiles with personal information and customization | ✅ Only for preferences. We don't want to capture any PII data.                                         |
| **Content Creation & Sharing** | ✅ Post text, photos, videos, stories, and other media types             | ✅ Text only. Other content types should be linked from external sources.                               |
| **News Feed/Timeline**         | ✅ Chronological or algorithmic feed showing content from connections    | ✅ Algorithmic focused on key themes, source rating, etc. Not by engagement metrics.                    |
| **Following/Friend System**    | ✅ Build networks by connecting with other users                         | ❌ No, subscribing (following) is only by feed source.                                                  |
| **Likes/Reactions**            | ✅ Express engagement through likes, reactions, or upvotes               | ❌ No, reactions are irrelevant when engagement metrics are not tracked.                                |
| **Comments**                   | ✅ Threaded discussions and conversations on posts                       | ❌ No, commentary may be published via a separate feed.                                                 |
| **Direct Messaging**           | ✅ Private one-on-one or group conversations                             | ❌ No.                                                                                                  |
| **Sharing/Reposting**          | ✅ Amplify content to your own network                                   | ❌ No.                                                                                                  |
| **Search Functionality**       | ✅ Find people, content, hashtags, and topics                            | ✅ Search includes all RSS content available on the Web.                                                |
| **Hashtags/Tags**              | ✅ Categorize and discover content by topic                              | ✅ Categorization via AI to identify key themes for algorithmic sorting.                                |
| **Personalized Algorithms**    | ✅ Content recommendations based on interests and behavior               | ✅ Recommended themes based on relationship to current themes.                                          |
| **Trending Topics**            | ✅ Discover what's popular or being discussed widely                     | ✅ Recommended themes based on popularity.                                                              |
| **Privacy Settings**           | ✅ Control visibility and who can see your content                       | ❌ No.                                                                                                  |
| **Blocking & Reporting**       | ✅ Tools to manage harassment and harmful content                        | ✅ Ability to hide content, otherwise irrelevant as there are no comments or direct messaging features. |
| **Content Moderation**         | ✅ Systems to enforce community guidelines                               | ❌ No, key theme management already classifies content.                                                 |
| **Account Verification**       | ✅ Badges for notable or authentic accounts                              | ❌ No, profiles are not public.                                                                         |
| **Notifications**              | ✅ Alerts for activity, mentions, and interactions                       | ❌ No.                                                                                                  |
| **Groups/Communities**         | ✅ Spaces organized around shared interests                              | ✅ Themes provide this functionality.                                                                   |
| **Live Streaming**             | ✅ Real-time video broadcasting                                          | ❌ No.                                                                                                  |
| **Stories/Ephemeral Content**  | ✅ Temporary content that disappears after 24 hours                      | ❌ No.                                                                                                  |
| **Analytics**                  | ✅ Insights and metrics for content performance                          | ❌ No.                                                                                                  |
| **Mobile Apps**                | ✅ Native apps for iOS and Android alongside web access                  | ❌ No.                                                                                                  |

The table below outlines various data points used by social media platforms to tailor content visibility and engagement, along with their applicability to Coucal.

| Data Point                         | Social Media Platforms                                          | Coucal                                                                                          |
|------------------------------------|-----------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| **Likes/Reactions**                | Number of likes, reactions, and other positive signals          | Signals are only captured on Coucal metadata (e.g. key themes, etc.), not the external content. |
| **Comments**                       | Quantity and quality/length of comments                         | Not applicable, as comments are not supported.                                                  |
| **Shares/Retweets/Reposts**        | How often content is shared to other networks                   | Engagement metrics not tracked.                                                                 |
| **Saves/Bookmarks**                | Users saving content for later                                  | Engagement metrics not tracked.                                                                 |
| **Click-Through Rates**            | Clicks on links within posts                                    | Engagement metrics not tracked.                                                                 |
| **Video Watch Time**               | Duration and completion rate of video content                   | Engagement metrics not tracked.                                                                 |
| **Story Interactions**             | Replies and interactions with stories                           | Engagement metrics not tracked.                                                                 |
| **Post Recency**                   | How recently the content was posted                             |                                                                                                 |
| **Topic Freshness**                | Whether the topic or trend is current                           |                                                                                                 |
| **Time Decay**                     | Reduction in visibility as content ages                         |                                                                                                 |
| **Interaction Frequency**          | How often user has interacted with the poster previously        | Engagement metrics not tracked.                                                                 |
| **Direct Messages**                | Private message exchanges between users                         | Direct messages not supported.                                                                  |
| **Profile Visits**                 | Whether user visits the poster's profile                        | Engagement metrics not tracked.                                                                 |
| **Content View Time**              | Time spent viewing content from specific accounts               | Engagement metrics not tracked.                                                                 |
| **Tags/Mentions Together**         | Whether users are frequently tagged or mentioned together       | Engagement metrics not tracked.                                                                 |
| **Content Type Preference**        | User's historical preference for videos vs. photos vs. text     | Engagement metrics not tracked.                                                                 |
| **Native vs. External**            | Preference for platform-native content vs. external links       | Engagement metrics not tracked.                                                                 |
| **Format Preferences**             | Preference for specific formats (Reels, Stories, posts, etc.)   | Engagement metrics not tracked.                                                                 |
| **Topic Engagement History**       | Topics and accounts the user typically engages with             | Engagement metrics not tracked.                                                                 |
| **Similar Content Time**           | Time spent on similar content previously                        | Engagement metrics not tracked.                                                                 |
| **Search History**                 | User's search patterns and interests                            | Interests tracked via themes only.                                                              |
| **Negative Signals**               | Hides, "not interested" clicks, unfollows                       |                                                                                                 |
| **Session Patterns**               | Session length and activity patterns                            | Engagement metrics not tracked.                                                                 |
| **Trending Hashtags**              | Use of trending hashtags or topics                              | Engagement metrics not tracked.                                                                 |
| **Multimedia Richness**            | Presence of images, videos, multiple photos                     |                                                                                                 |
| **Caption/Text Content**           | Caption length and keyword relevance                            |                                                                                                 |
| **Platform-Native Features**       | Use of platform-specific features and tools                     |                                                                                                 |
| **Content Originality**            | Original content vs. reshared/reposted content                  |                                                                                                 |
| **Close Connection Engagement**    | Engagement from the user's close connections                    | Follows not supported.                                                                          |
| **Viral Velocity**                 | How quickly engagement is growing on content                    |                                                                                                 |
| **Influential Account Engagement** | Engagement from verified or influential accounts                | Engagement metrics not tracked.                                                                                                |
| **Geographic Relevance**           | Location-based relevance of content                             |                                                                                                 |
| **Reports/Flags**                  | Content moderation reports or flags                             |                                                                                                 |
| **Unfollows After Viewing**        | Users unfollowing after seeing content                          |                                                                                                 |
| **Dwell Time**                     | How long users spend viewing content (quick scrolls = negative) | Engagement metrics not tracked.                                                                                                |
| **Hide/Show Less Actions**         | Active choices to see less of content type                      |                                                                                                 |
| **Engagement Bait Detection**      | Detection of manipulative engagement tactics                    |                                                                                                 |


## Getting Started

TBD.

## Contributing

TBD.