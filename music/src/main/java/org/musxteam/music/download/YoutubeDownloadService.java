package org.musxteam.music.download;

import java.util.HashMap;
import java.io.IOException;
import java.text.MessageFormat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Executor;
import org.musxteam.music.types.MusicInstance;

public class YoutubeDownloadService extends DownloadServiceBase {
    final Executor session = Executor.newInstance();
    final String downloadEndpoint = "https://apiyoutube.cc/m4a/{0}::{1}";
    final String thumbnailEndpoint = "https://i.ytimg.com/vi/{0}/maxresdefault.jpg";
    final String dataEndpoint = "https://apiyoutube.cc/check2.php?v={0}&uhash=baceebebc179d3cdb726f5cbfaa81dfe";
    final String baseEndpoint = "https://apiyoutube.cc/?url=https://www.youtube.com/watch?v={0}&t=0&color=3b6c96";

    @Override
    public MusicInstance downloadMusic(String id) throws IOException {
        return downloadUsingApiYoutubeCC(id);
    }

    private MusicInstance downloadUsingApiYoutubeCC(String id) throws IOException {
        String rawResponse = session.execute(
                Request.get(MessageFormat.format(dataEndpoint, id))
                        .addHeader("Sec-Fetch-Mode", "corss")
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                        .addHeader("Referer", MessageFormat.format(baseEndpoint, id))
        ).returnContent().asString();
        HashMap<String, String> response = jsonToHashMap(rawResponse);

        return new MusicInstance(
                response.get("title"), MessageFormat.format(thumbnailEndpoint, id),
                MessageFormat.format(downloadEndpoint, response.get("hash"), response.get("user"))
        );
    }
    private HashMap<String, String> jsonToHashMap(String json) {
        return new Gson().fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
    }
}
