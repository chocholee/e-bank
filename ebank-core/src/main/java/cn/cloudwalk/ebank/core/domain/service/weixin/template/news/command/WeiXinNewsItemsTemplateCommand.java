package cn.cloudwalk.ebank.core.domain.service.weixin.template.news.command;

import cn.cloudwalk.ebank.core.support.command.AbstractCommand;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by liwenhe on 16/11/14.
 */
public class WeiXinNewsItemsTemplateCommand extends AbstractCommand {

    private String                      id;

    @NotBlank(message = "{WeiXinNewsItemsTemplateCommand.title.NotBlank}")
    private String                      title;                  // 图文模板内容项标题

    @NotBlank(message = "{WeiXinNewsItemsTemplateCommand.author.NotBlank}")
    private String                      author;                 // 图文模板内容项作者

    @NotBlank(message = "{WeiXinNewsItemsTemplateCommand.picUrl.NotBlank}")
    private String                      picUrl;                 // 图文模板内容项图片

    @NotBlank(message = "{WeiXinNewsItemsTemplateCommand.description.NotBlank}")
    private String                      description;            // 图文模板内容项描述

    @NotBlank(message = "{WeiXinNewsItemsTemplateCommand.url.NotBlank}")
    private String                      url;                    // 图文模板内容项地址

    @NotNull(message = "{WeiXinNewsItemsTemplateCommand.order.NotNull}")
    private Integer                     order;                  // 图文模板内容项排序

    private Date                        createdDate;            // 创建日期

    private String                      newsTemplateId;         // 图文模板内容项所属模板

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Integer getOrder() {
        return order;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getNewsTemplateId() {
        return newsTemplateId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setNewsTemplateId(String newsTemplateId) {
        this.newsTemplateId = newsTemplateId;
    }
}
