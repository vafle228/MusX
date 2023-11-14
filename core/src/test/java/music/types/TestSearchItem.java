package music.types;

import org.musxteam.music.search.types.ISearchItem;

public class TestSearchItem implements ISearchItem {
    @Override
    public String getItemTitle() { return "Test title"; }
    @Override
    public String getItemVideoId() { return "Test video id"; }
    @Override
    public String getItemThumbnail() { return "Test thumbnail"; }
    @Override
    public String getItemChannelTitle() { return "Test channel title"; }
}
