package com.me.manager;

import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.company.api.QYMediaAPI;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.github.sd4324530.fastweixin.company.api.response.UploadMediaResponse;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by kenya on 2018/1/18.
 */
@Service
public class MediaManager {

    public String uploadMedia(String mediaPathString, MediaType type, QYAPIConfig config) {
        return uploadMedia(new File(mediaPathString), type, config);
    }

    public String uploadMedia(File mediaFile, MediaType type, QYAPIConfig config) {

        QYMediaAPI mediaAPI = new QYMediaAPI(config);
        UploadMediaResponse response1 = mediaAPI.upload(type, mediaFile);
        return response1.getMediaId();
    }
}
