package org.musxteam.music.search.types;

public record YoutubeSearchIdItemContainer(SearchIdItem[] items) implements ISearchItemsContainer {
    @Override
    public ISearchItem[] getSearchItems() { return items; }

    public record SearchIdItem(String id, Snippet snippet) implements ISearchItem {
        @Override
        public String getItemTitle() { return snippet.title; }
        @Override
        public String getItemVideoId() { return id; }
        @Override
        public String getItemThumbnail() { return "https://i.ytimg.com/vi/" + id + "/mqdefault.jpg"; }
        @Override
        public String getItemChannelTitle() { return snippet.channelTitle; }

        public record Snippet(String title, String channelTitle) { }
    }
}
