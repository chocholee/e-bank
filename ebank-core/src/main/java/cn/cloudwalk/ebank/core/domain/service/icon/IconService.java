package cn.cloudwalk.ebank.core.domain.service.icon;

import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.domain.service.function.IFunctionService;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconCommand;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.icon.IIconRepository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
@Service("iconService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IconService implements IIconService {

    @Autowired
    private IIconRepository<IconEntity, String> iconRepository;

    @Autowired
    private IFunctionService functionService;

    @Override
    public Pagination<IconEntity> pagination(IconPaginationCommand command) {
        List<Criterion> criterions = new ArrayList<>();
        if (!StringUtils.isEmpty(command.getName())) {
            criterions.add(Restrictions.like("name", command.getName()));
        }
        if (null != command.getType() && !command.getType().isOnlyQuery()) {
            criterions.add(Restrictions.eq("type", command.getType()));
        }
        return iconRepository.pagination(command.getPage(), command.getPageSize(), criterions);
    }

    @Override
    public IconEntity findById(String id) {
        return iconRepository.getById(id);
    }

    @Override
    public IconEntity save(IconCommand command) {
        IconEntity entity = new IconEntity(
                command.getName(),
                command.getBeforeHoverPath(),
                command.getAfterHoverPath(),
                command.getBeforeHoverPath().substring(command.getBeforeHoverPath().indexOf(".")),
                command.getDescription(),
                command.getType()
        );
        iconRepository.save(entity);
        return entity;
    }

    @Override
    public IconEntity update(IconCommand command) {
        IconEntity entity = iconRepository.getById(command.getId());
        entity.setName(command.getName());
        entity.setBeforeHoverPath(command.getBeforeHoverPath());
        entity.setAfterHoverPath(command.getAfterHoverPath());
        entity.setSuffix(command.getBeforeHoverPath().substring(command.getBeforeHoverPath().indexOf(".")));
        entity.setDescription(command.getDescription());
        entity.setType(command.getType());
        iconRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        IconEntity entity = iconRepository.getById(id);
        iconRepository.delete(entity);
    }
}
