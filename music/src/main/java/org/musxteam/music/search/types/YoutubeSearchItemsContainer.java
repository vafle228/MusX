package org.musxteam.music.search.types;

public record YoutubeSearchItemsContainer(SearchItem[] items) implements ISearchItemsContainer {
    @Override
    public ISearchItem[] getSearchItems() {
        return items;
    }

    public record SearchItem(VideoId id, Snippet snippet) implements ISearchItem {
        @Override
        public String getItemTitle() {
            return snippet.title;
        }
        @Override
        public String getItemVideoId() {
            return id.videoId;
        }
        @Override
        public String getItemThumbnail() {
            return "https://i.ytimg.com/vi/" + id.videoId + "/mqdefault.jpg";
        }
        @Override
        public String getItemChannelTitle() {
            return snippet.channelTitle;
        }

        public record VideoId(String videoId) {}
        public record Snippet(String title, String channelTitle) {}
    }
}
