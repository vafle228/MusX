package org.musxteam.music.search;

import java.io.IOException;

import org.musxteam.music.search.types.ISearchItem;
import org.musxteam.music.search.types.ISearchItemsContainer;

public abstract class SearchServiceBase {
    private int currentItem = 0;
    private ISearchItemsContainer itemsContainer;
    protected abstract ISearchItemsContainer executeSearchQuery(String query) throws IOException;

    public ISearchItem searchMusic(String query) throws IOException {
        itemsContainer = executeSearchQuery(query); currentItem = 0; return getCurrentItem();
    }

    public ISearchItem getCurrentItem() throws ArrayIndexOutOfBoundsException {
        if (currentItem < 0 || currentItem >= itemsContainer.getSearchItems().length) {
            throw new ArrayIndexOutOfBoundsException("Search item out of range");
        }
        return itemsContainer.getSearchItems()[currentItem];
    }
    public ISearchItem getNextItem() throws ArrayIndexOutOfBoundsException {
        if (currentItem + 1 > itemsContainer.getSearchItems().length)
            throw new ArrayIndexOutOfBoundsException("Search item out of range");
        return itemsContainer.getSearchItems()[++currentItem];
    }
    public  ISearchItem getPrevItem() throws ArrayIndexOutOfBoundsException {
        if (currentItem - 1 < 0)
            throw new ArrayIndexOutOfBoundsException("Search item out of range");
        return itemsContainer.getSearchItems()[--currentItem];
    }
}
