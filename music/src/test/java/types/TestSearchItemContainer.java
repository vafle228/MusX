package types;

import org.musxteam.music.search.types.ISearchItem;
import org.musxteam.music.search.types.ISearchItemsContainer;

public class TestSearchItemContainer implements ISearchItemsContainer {
    TestSearchItem[] items = new TestSearchItem[]{ new TestSearchItem() };

    @Override
    public ISearchItem[] getSearchItems() { return items; }
}
