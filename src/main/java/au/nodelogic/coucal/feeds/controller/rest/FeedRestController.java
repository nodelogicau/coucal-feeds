package au.nodelogic.coucal.feeds.controller.rest;

import au.nodelogic.coucal.feeds.data.Feed;
import au.nodelogic.coucal.feeds.data.FeedRepository;
import com.rometools.opml.feed.opml.Opml;
import com.rometools.opml.feed.opml.Outline;
import com.rometools.opml.io.impl.OPML20Generator;
import com.rometools.opml.io.impl.OPML20Parser;
import com.rometools.rome.io.FeedException;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
@RestController
@RequestMapping(value = "/api/feeds", produces =  MediaType.APPLICATION_XML_VALUE)
public class FeedRestController {

    private final FeedRepository feedRepository;

    public FeedRestController(@Autowired FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    @GetMapping("/")
    public ResponseEntity<String> exportFeeds() throws FeedException {
        // Implementation for exporting feeds goes here
        Opml exportOpml = new Opml();
        // Populate exportOpml with feed data from feedRepository
        exportOpml.setOutlines(feedRepository.findAll().stream().map(feed ->
                new Outline(feed.getTitle(), feed.getSource(), feed.getLink())).toList());
        String opmlString = new XMLOutputter().outputString(new OPML20Generator().generate(exportOpml));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML)
                .header("Content-Disposition", "attachment; filename=feeds.xml")
                .body(opmlString);
    }

    @PostMapping("/import")
    public void importFeeds(@RequestAttribute MultipartFile opmlFile) throws FeedException, IOException, JDOMException {
        // Implementation for importing feeds goes here
        OPML20Parser parser = new OPML20Parser();

        Opml importedOpml = (Opml) parser.parse(new SAXBuilder().build(opmlFile.getInputStream()),
                false, Locale.getDefault());

        List<Feed> feeds = new ArrayList<>();
        for (Outline outline : importedOpml.getOutlines()) {
            // Create and save Feed entities based on the outlines
            au.nodelogic.coucal.feeds.data.Feed feed = new au.nodelogic.coucal.feeds.data.Feed();
            feed.setTitle(outline.getText());
            feed.setSource(URI.create(outline.getXmlUrl()).toURL());
            feed.setLink(URI.create(outline.getHtmlUrl()).toURL());
            feeds.add(feed);
        }
        feedRepository.saveAll(feeds);
    }
}
