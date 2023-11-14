package music;

import org.musxteam.music.search.SearchServiceBase;
import org.musxteam.music.search.types.ISearchItemsContainer;
import music.types.TestSearchItemContainer;

import java.io.IOException;

public class TestSearchService extends SearchServiceBase {
    @Override
    protected ISearchItemsContainer executeSearchQuery(String query) throws IOException {
        return new TestSearchItemContainer();
    }
}
