package cn.cloudwalk.ebank.core.domain.service.weixin.qrcode;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.channel.WeiXinChannelEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual.WeiXinGroupVirtualEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntityType;
import cn.cloudwalk.ebank.core.domain.model.weixin.scene.WeiXinSceneEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.qrcode.command.WeiXinQRCodeCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.qrcode.command.WeiXinQRCodePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.channel.IWeiXinChannelRepository;
import cn.cloudwalk.ebank.core.repository.weixin.group.virtual.IWeiXinGroupVirtualRepository;
import cn.cloudwalk.ebank.core.repository.weixin.group.wechat.IWeiXinGroupWechatRepository;
import cn.cloudwalk.ebank.core.repository.weixin.qrcode.IWeiXinQRCodeRepository;
import cn.cloudwalk.ebank.core.repository.weixin.scene.IWeiXinSceneRepository;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import com.arm4j.weixin.exception.WeiXinRequestException;
import com.arm4j.weixin.request.qrcode.WeiXinQRCodeCreateRequest;
import com.arm4j.weixin.request.qrcode.entity.QRCodeActionEntity;
import com.arm4j.weixin.request.qrcode.entity.QRCodeEntity;
import com.arm4j.weixin.request.qrcode.entity.QRCodeSceneEntity;
import com.arm4j.weixin.request.qrcode.response.QRCodeResponse;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by liwenhe on 16/11/24.
 */
@Service("weiXinQRCodeSerivce")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinQRCodeService implements IWeiXinQRCodeService {

    @Autowired
    private IWeiXinQRCodeRepository<WeiXinQRCodeEntity, String> weiXinQRCodeRepository;

    @Autowired
    private IWeiXinSceneRepository<WeiXinSceneEntity, String> weiXinSceneRepository;

    @Autowired
    private IWeiXinChannelRepository<WeiXinChannelEntity, String> weiXinChannelRepository;

    @Autowired
    private IWeiXinGroupWechatRepository<WeiXinGroupWechatEntity, String> weiXinGroupWechatRepository;

    @Autowired
    private IWeiXinGroupVirtualRepository<WeiXinGroupVirtualEntity, String> weiXinGroupVirtualRepository;

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinQRCodeEntity> pagination(WeiXinQRCodePaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (!StringUtils.isEmpty(command.getName())) {
                criterions.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
            }

            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.desc("createdDate"));

            return weiXinQRCodeRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinQRCodeEntity findById(String id) {
        return weiXinQRCodeRepository.getById(id);
    }

    @Override
    public WeiXinQRCodeEntity findByIdAndFetch(String id) {
        return weiXinQRCodeRepository.findByIdAndFetch(id);
    }

    @Override
    public WeiXinQRCodeEntity save(WeiXinQRCodeCommand command) throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        // 得到场景、渠道、微信分组、虚拟分组数据
        WeiXinSceneEntity scene = weiXinSceneRepository.getById(command.getSceneId());
        WeiXinChannelEntity channel = weiXinChannelRepository.getById(command.getChannelId());
        WeiXinGroupWechatEntity wechat = weiXinGroupWechatRepository.getById(command.getGroupWechatId());
        WeiXinGroupVirtualEntity virtual = null;
        if (!StringUtils.isEmpty(command.getGroupVirtualId())) {
            virtual = weiXinGroupVirtualRepository.getById(command.getGroupVirtualId());
        }

        // 构建二维码参数
        QRCodeSceneEntity qrCodeSceneEntity = new QRCodeSceneEntity();

        QRCodeActionEntity qrCodeActionEntity = new QRCodeActionEntity();
        qrCodeActionEntity.setScene(qrCodeSceneEntity);

        QRCodeEntity qrCodeEntity = new QRCodeEntity();
        qrCodeEntity.setActionName(command.getType().name());
        qrCodeEntity.setActionInfo(qrCodeActionEntity);

        // 生成key
        String key = String.valueOf(new Date().getTime());

        // 临时二维码参数
        if (command.getType() == WeiXinQRCodeEntityType.QR_SCENE) {
            key = key.substring(0, key.length() - 3);
            qrCodeSceneEntity.setSceneId(key);
            qrCodeEntity.setExpireSeconds(command.getExpireSeconds() * 86400);
        }

        // 永久二维码参数
        if (command.getType() == WeiXinQRCodeEntityType.QR_LIMIT_STR_SCENE) {
            key = "QR_".concat(key.substring(0, key.length() - 3));
            qrCodeSceneEntity.setSceneStr(key);
        }

        // 创建二维码
        QRCodeResponse response = WeiXinQRCodeCreateRequest
                .request(weiXinAccountService.getAccessToken(accountEntity.getAppId()), qrCodeEntity);

        // 计算失效日期
        Calendar expireSeconds = null;
        if (command.getType() == WeiXinQRCodeEntityType.QR_SCENE) {
            expireSeconds = Calendar.getInstance();
            expireSeconds.setTime(new Date());
            expireSeconds.add(Calendar.DAY_OF_MONTH, command.getExpireSeconds());
            expireSeconds.set(Calendar.HOUR_OF_DAY, 23);
            expireSeconds.set(Calendar.MINUTE, 59);
            expireSeconds.set(Calendar.SECOND, 59);
        }

        // 新增二维码数据
        WeiXinQRCodeEntity entity = new WeiXinQRCodeEntity(
                command.getName(),
                key,
                response.getTicket(),
                response.getUrl(),
                null != expireSeconds ? expireSeconds.getTime() : null,
                new Date(),
                command.getType(),
                scene,
                channel,
                wechat,
                virtual,
                accountEntity.getId()
        );

        weiXinQRCodeRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinQRCodeEntity update(WeiXinQRCodeCommand command) {
        WeiXinQRCodeEntity entity = weiXinQRCodeRepository.findByIdAndFetch(command.getId());
        entity.setName(command.getName());

        // 判断场景是否改变
        if (!command.getSceneId().equals(entity.getScene().getId())) {
            WeiXinSceneEntity scene = weiXinSceneRepository.getById(command.getSceneId());
            entity.setScene(scene);
        }

        // 判断渠道是否改变
        if (!command.getChannelId().equals(entity.getChannel().getId())) {
            WeiXinChannelEntity channel = weiXinChannelRepository.getById(command.getChannelId());
            entity.setChannel(channel);
        }

        // 判断微信分组是否改变
        if (!command.getGroupWechatId().equals(entity.getGroupWechat().getId())) {
            WeiXinGroupWechatEntity wechat = weiXinGroupWechatRepository.getById(command.getGroupWechatId());
            entity.setGroupWechat(wechat);
        }

        // 判断虚拟分组是否改变
        if (null != entity.getGroupVirtual()
                && !StringUtils.isEmpty(command.getGroupVirtualId())
                && !command.getGroupVirtualId().equals(entity.getGroupVirtual().getId())) {
            WeiXinGroupVirtualEntity virtual = weiXinGroupVirtualRepository.getById(command.getGroupVirtualId());
            entity.setGroupVirtual(virtual);
        } else {
            if (!StringUtils.isEmpty(command.getGroupVirtualId())) {
                WeiXinGroupVirtualEntity virtual = weiXinGroupVirtualRepository.getById(command.getGroupVirtualId());
                entity.setGroupVirtual(virtual);
            } else {
                entity.setGroupVirtual(null);
            }
        }

        weiXinQRCodeRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        WeiXinQRCodeEntity entity = weiXinQRCodeRepository.getById(id);
        weiXinQRCodeRepository.delete(entity);
    }
}
