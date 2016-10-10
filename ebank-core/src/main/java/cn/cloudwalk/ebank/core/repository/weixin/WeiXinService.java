package cn.cloudwalk.ebank.core.repository.weixin;

import cn.cloudwalk.ebank.core.domain.model.weixin.keyword.WeiXinKeywordEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.newstemplate.WeiXinNewsItemsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.newstemplate.WeiXinNewsTemplateEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.texttemplate.WeiXinTextTemplateEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.expandtemplate.IWeiXinExpandTemplateService;
import cn.cloudwalk.ebank.core.domain.service.weixin.keyword.IWeiXinKeywordService;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.IWeiXinNewsItemsTemplateService;
import cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate.IWeiXinNewsTemplateService;
import cn.cloudwalk.ebank.core.domain.service.weixin.texttemplate.IWeiXinTextTemplateService;
import cn.cloudwalk.ebank.core.support.utils.CustomWeiXinXMLProcessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
@Service("weiXinService")
public class WeiXinService {

    @Autowired
    private IWeiXinKeywordService weiXinKeywordService;

    @Autowired
    private IWeiXinTextTemplateService weiXinTextTemplateService;

    @Autowired
    private IWeiXinNewsTemplateService weiXinNewsTemplateService;

    @Autowired
    private IWeiXinNewsItemsTemplateService weiXinNewsItemsTemplateService;

    @Autowired
    private IWeiXinExpandTemplateService weiXinExpandTemplateService;

    public void core(HttpServletRequest request, HttpServletResponse response) {
        WeiXinMessageAdapter adapter = new WeiXinMessageAdapter();
        adapter.adapt(request, response);
    }

    private final class WeiXinMessageAdapter {
        void adapt(HttpServletRequest request, HttpServletResponse response) {
            try {
                Map<String, Object> data = CustomWeiXinXMLProcessUtil.parseXML(request.getInputStream());
                String msgType = (String) data.get("MsgType");
                if (msgType.equals("event")) {
                    new WeiXinEventMessageProcess().process(data, response.getWriter());
                } else {
                    new WeiXinCommonMessageProcess().process(data, response.getWriter());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    abstract class WeiXinMessageProcess {
        abstract void process(Map<String, Object> data, PrintWriter writer);
    }

    /**
     * 微信普通消息
     */
    private final class WeiXinCommonMessageProcess extends WeiXinMessageProcess {
        @Override
        void process(Map<String, Object> data, PrintWriter writer) {
            if (data.get("MsgType").equals("text")) {
                processText(data, writer);
            }
            if (data.get("MsgType").equals("image")) {
                processImage(data, writer);
            }
            if (data.get("MsgType").equals("voice")) {
                processVoice(data, writer);
            }
            if (data.get("MsgType").equals("video")) {
                processVideo(data, writer);
            }
            if (data.get("MsgType").equals("shortvideo")) {
                processShortVideo(data, writer);
            }
            if (data.get("MsgType").equals("location")) {
                processLocation(data, writer);
            }
            if (data.get("MsgType").equals("link")) {
                processLink(data, writer);
            }
        }

        void processText(Map<String, Object> data, PrintWriter writer) {
            // 创建返回的消息的对象并赋值通用数据
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("ToUserName", data.get("FromUserName"));
            responseMap.put("FromUserName", data.get("ToUserName"));
            responseMap.put("CreateTime", new Date().getTime());

            // 查询关键字表中是否存在用户请求的内容
            // 存在并取出数据返回给用户
            // 不存在则返回欢迎语
            WeiXinKeywordEntity entity = weiXinKeywordService
                    .findByKeyword((String) data.get("ToUserName"), (String) data.get("Content"));
            if (null != entity) {
                switch (entity.getMsgType()) {
                    case TEXT:
                        // 返回文本消息
                        WeiXinTextTemplateEntity templateEntity = weiXinTextTemplateService.findById(entity.getTemplateId());
                        responseMap.put("MsgType", "text");
                        responseMap.put("Content", templateEntity.getContent());
                        break;
                    case NEWS:
                        // 返回图文消息
                        WeiXinNewsTemplateEntity newsTemplateEntity =
                                weiXinNewsTemplateService.findById(entity.getTemplateId());
                        List<WeiXinNewsItemsTemplateEntity> newsItemsTemplateEntities =
                                weiXinNewsItemsTemplateService.findByNewsTemplateId(newsTemplateEntity.getId());

                        responseMap.put("MsgType", "news");
                        responseMap.put("ArticleCount", newsItemsTemplateEntities.size());
                        List<Map<String, String>> articles = new ArrayList<>();
                        for (WeiXinNewsItemsTemplateEntity newsItemsTemplateEntity : newsItemsTemplateEntities) {
                            Map<String, String> articleMap = new HashMap<>();
                            articleMap.put("Title", newsItemsTemplateEntity.getTitle());
                            articleMap.put("Description", newsItemsTemplateEntity.getDescription());
                            articleMap.put("PicUrl", newsItemsTemplateEntity.getPicUrl());
                            articleMap.put("Url", newsItemsTemplateEntity.getUrl());
                            articles.add(articleMap);
                        }
                        responseMap.put("Articles", articles);
                        break;
                    case EXPAND:
                        // 返回扩展消息
                        break;
                }
            } else {
                // TODO 查询欢迎语表中数据
            }

            // 生成xml内容并返回给微信用户
            String xml = CustomWeiXinXMLProcessUtil.toXML(responseMap);
            writer.println(xml);
            writer.flush();
            writer.close();
        }

        void processImage(Map<String, Object> data, PrintWriter writer) {

        }

        void processVoice(Map<String, Object> data, PrintWriter writer) {

        }

        void processVideo(Map<String, Object> data, PrintWriter writer) {

        }

        void processShortVideo(Map<String, Object> data, PrintWriter writer) {

        }

        void processLocation(Map<String, Object> data, PrintWriter writer) {

        }

        void processLink(Map<String, Object> data, PrintWriter writer) {

        }
    }

    /**
     * 微信事件消息
     */
    private final class WeiXinEventMessageProcess extends WeiXinMessageProcess {
        @Override
        void process(Map<String, Object> data, PrintWriter writer) {
            if (data.get("Event").equals("subscribe")) {
                processSubscribe(data, writer);
            }
            if (data.get("Event").equals("unsubscribe")) {
                processUnSubscribe(data, writer);
            }
            if (data.get("Event").equals("SCAN")) {
                processScan(data, writer);
            }
            if (data.get("Event").equals("LOCATION")) {
                processLocation(data, writer);
            }
            if (data.get("Event").equals("CLICK")) {
                processClick(data, writer);
            }
            if (data.get("Event").equals("VIEW")) {
                processView(data, writer);
            }
        }

        void processSubscribe(Map<String, Object> data, PrintWriter writer) {

        }

        void processUnSubscribe(Map<String, Object> data, PrintWriter writer) {

        }

        void processScan(Map<String, Object> data, PrintWriter writer) {

        }

        void processLocation(Map<String, Object> data, PrintWriter writer) {

        }

        void processClick(Map<String, Object> data, PrintWriter writer) {

        }

        void processView(Map<String, Object> data, PrintWriter writer) {

        }
    }
}
