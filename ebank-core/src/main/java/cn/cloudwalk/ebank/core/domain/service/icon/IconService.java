package cn.cloudwalk.ebank.core.domain.service.icon;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconCommand;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.function.IFunctionRepository;
import cn.cloudwalk.ebank.core.repository.icon.IIconRepository;
import cn.cloudwalk.ebank.core.support.utils.CustomUploadUtil;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private IFunctionRepository<FunctionEntity, String> functionRepository;

    @Override
    public Pagination<IconEntity> pagination(IconPaginationCommand command) {
        List<Criterion> criterions = new ArrayList<>();
        if (!StringUtils.isEmpty(command.getName())) {
            criterions.add(Restrictions.like("name", command.getName()));
        }
        return iconRepository.pagination(command.getPage(), command.getPageSize(), criterions);
    }

    @Override
    public List<IconEntity> findAll() {
        return iconRepository.findAll();
    }

    @Override
    public IconEntity findById(String id) {
        return iconRepository.getById(id);
    }

    @Override
    public IconEntity save(IconCommand command, String tempDir, String saveDir) throws IOException {
        IconEntity entity = new IconEntity(
                command.getName(),
                command.getBeforeHoverPath(),
                command.getAfterHoverPath(),
                command.getBeforeHoverPath().substring(command.getBeforeHoverPath().indexOf(".")),
                command.getDescription()
        );
        iconRepository.save(entity);

        File beforeSrc = new File(tempDir.concat(File.separator).concat(command.getBeforeHoverPath()));
        File beforeDst = new File(saveDir.concat(File.separator).concat(command.getBeforeHoverPath()));
        CustomUploadUtil.move(beforeSrc, beforeDst);

        File afterSrc = new File(tempDir.concat(File.separator).concat(command.getAfterHoverPath()));
        File afterDst = new File(saveDir.concat(File.separator).concat(command.getAfterHoverPath()));
        CustomUploadUtil.move(afterSrc, afterDst);

        return entity;
    }

    @Override
    public IconEntity update(IconCommand command, String tempDir, String saveDir) throws IOException {
        IconEntity entity = iconRepository.getById(command.getId());
        entity.setName(command.getName());

        if (!entity.getBeforeHoverPath().equals(command.getBeforeHoverPath())) {
            File beforeOrigin = new File(saveDir.concat(File.separator).concat(entity.getBeforeHoverPath()));
            CustomUploadUtil.delete(beforeOrigin);

            entity.setBeforeHoverPath(command.getBeforeHoverPath());

            File beforeSrc = new File(tempDir.concat(File.separator).concat(command.getBeforeHoverPath()));
            File beforeDst = new File(saveDir.concat(File.separator).concat(command.getBeforeHoverPath()));
            CustomUploadUtil.move(beforeSrc, beforeDst);
        }

        if (!entity.getAfterHoverPath().equals(command.getAfterHoverPath())) {
            File afterOrigin = new File(saveDir.concat(File.separator).concat(entity.getAfterHoverPath()));
            CustomUploadUtil.delete(afterOrigin);

            entity.setAfterHoverPath(command.getAfterHoverPath());

            File afterSrc = new File(tempDir.concat(File.separator).concat(command.getAfterHoverPath()));
            File afterDst = new File(saveDir.concat(File.separator).concat(command.getAfterHoverPath()));
            CustomUploadUtil.move(afterSrc, afterDst);
        }

        entity.setSuffix(command.getBeforeHoverPath().substring(command.getBeforeHoverPath().indexOf(".")));
        entity.setDescription(command.getDescription());
        iconRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id, String saveDir) throws IOException {
        // 添加查询条件
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(Restrictions.eq("iconEntity.id", id));

        // 添加fetch mode
        Map<String, FetchMode> fetchModeMap = new HashMap<>();
        fetchModeMap.put("iconEntity", FetchMode.JOIN);

        // 清空icon在function中的外键
        List<FunctionEntity> functionEntities = functionRepository.findAll(criterions, null, fetchModeMap);
        for (FunctionEntity function : functionEntities) {
            function.setIconEntity(null);
            functionRepository.update(function);
        }

        // 删除icon
        IconEntity entity = iconRepository.getById(id);
        iconRepository.delete(entity);

        File beforeDst = new File(saveDir.concat(File.separator).concat(entity.getBeforeHoverPath()));
        File afterDst = new File(saveDir.concat(File.separator).concat(entity.getAfterHoverPath()));
        CustomUploadUtil.delete(beforeDst);
        CustomUploadUtil.delete(afterDst);
    }
}
