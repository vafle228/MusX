package org.musxteam.music.download;

import com.google.gson.Gson;
import org.apache.hc.client5.http.fluent.Executor;
import org.apache.hc.client5.http.fluent.Request;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;

public class YoutubeDownloadService extends DownloadServiceBase {
    final String thirdEndpoint = "https://apiyoutube.cc/m4a/{0}::{1}";
    final String secondEndpoint = "https://apiyoutube.cc/check2.php?v={0}&uhash=baceebebc179d3cdb726f5cbfaa81dfe";
    final String firstEndpoint = "https://apiyoutube.cc/?url=https://www.youtube.com/watch?v={0}&t=0&color=3b6c96";

    @Override
    public String downloadMusic(String id) throws IOException {
        Executor session = Executor.newInstance();

        String a = session.execute(
                Request.get(MessageFormat.format(firstEndpoint, id))
        ).returnContent().asString();

        String b = session.execute(
                Request.get(MessageFormat.format(secondEndpoint, id))
                        .addHeader("Sec-Fetch-Mode", "corss")
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                        .addHeader("Referer", MessageFormat.format(firstEndpoint, id))
        ).returnContent().asString();

        Gson gson = new Gson();
        HashMap<String, String> response = new HashMap<>();
        response = (HashMap<String, String>)gson.fromJson(b, response.getClass());

        return MessageFormat.format(thirdEndpoint, response.get("hash"), response.get("user"));
    }
}
