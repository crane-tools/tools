package crane.controllers;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.PicCloud;
import crane.tools.QCType;
import crane.tools.QCloudPicHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by crane on 16/7/18.
 */
@RestController
@RequestMapping("qc")
public class QCloudController {
    @Autowired
    QCloudPicHelper helper;

    @RequestMapping("/getsign")
    public Object getsign(HttpServletRequest request) {
        String type = request.getParameter("type");
        String fileid = request.getParameter("fileid");
        if (StringUtils.isBlank(type))
            type = "";
        String sign = type.equals(QCType.upload.name()) ? helper.getUploadSign() : helper.getNotUploadSign(fileid);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", helper.getQCloudPostUrl());
        jsonObject.put("ver", helper.getVersion());
        jsonObject.put("sign", sign);
        return jsonObject;
    }
}
