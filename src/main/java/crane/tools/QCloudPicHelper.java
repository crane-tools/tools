package crane.tools;

import com.qcloud.PicCloud;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by crane on 16/7/18.
 */
@Component
public class QCloudPicHelper {
    @Value("${qcloud.img.bucket}")
    private String bucket;
    @Value("${qcloud.img.secretid}")
    private String secretid;
    @Value("${qcloud.img.secretkey}")
    private String secretkey;
    @Value("${qcloud.img.appid}")
    private int appid;
    @Value("${qcloud.img.url}")
    private String url;

    private PicCloud picCloud;

    private QCloudPicHelper() {
    }

    @PostConstruct
    private void init() {
        picCloud = new PicCloud(appid, secretid, secretkey, bucket);
    }

    public PicCloud getPicCloud() {
        return this.picCloud;
    }

    public String getVersion() {
        return "V2";
    }

    public String getQCloudPostUrl() {
        return this.url;
    }

    public String getSign(String type, String fileid, long expire) {
        String sign;
        long expired = System.currentTimeMillis() / 1000 + expire;//秒
        if (StringUtils.isBlank(type)) {
            sign = "";
        } else if (QCType.upload.name().equals(type)) {
            sign = picCloud.getSign(expired);
        } else if (QCType.copy.name().equals(type) ||
                QCType.delete.name().equals(type) ||
                QCType.download.name().equals(type)) {
            sign = picCloud.getSignOnce(fileid);
        } else {
            sign = "";
        }
        return sign;
    }

    public String getUploadSign() {
        return this.getSign(QCType.upload.name(), "", 3600);
    }

    public String getNotUploadSign(String fileid) {
        return this.getSign(QCType.copy.name(), fileid, 3600);
    }
}
