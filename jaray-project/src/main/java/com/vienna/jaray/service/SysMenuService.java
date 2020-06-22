package com.vienna.jaray.service;

import com.vienna.jaray.common.ResponseResult;
import com.vienna.jaray.entity.SysMenuEntity;
import com.vienna.jaray.model.CommonParamsModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuService {
    /**
     * 查询左侧菜单信息
     * @return
     */
    public ResponseResult findLeftNav();

    /**
     * 查询菜单列表
     * @return
     */
    public ResponseResult findAll(CommonParamsModel commonParamsModel);

    /**
     * 添加菜单
     * @param sysMenuEntity 菜单对象
     * @return
     */
    public ResponseResult add(@Param("entity")SysMenuEntity sysMenuEntity);

    /**
     * 删除菜单
     * @param ids 菜单ids
     * @return
     */
    public ResponseResult deleteByIds(@Param("ids")String[] ids);

    /**
     * 更新菜单
     * @param sysMenuEntity 菜单对象
     * @return
     */
    public ResponseResult updateById(@Param("entity")SysMenuEntity sysMenuEntity);

    /**
     * 查询菜单
     * @param id 菜单id
     * @return
     */
    public ResponseResult findById(@Param("id")String id);
}
